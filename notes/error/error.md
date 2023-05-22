# error

## spring
##### 1.feign异步调用接口丢失header
解决:1.在主线程中先获取请求头参数
    2.传入子线程中
    3.由子线程将请求头参数设置到上下文中
    4.最后在feign转发处理中拿到子线程设置的上下文请求头数据，转发到下游

请求头上下文：

    public class InheritableThreadLocalHeader {
    private InheritableThreadLocalHeader() {
    }
    private static final InheritableThreadLocal<HashMap<String, String>> HEADER = new InheritableThreadLocal<>();
    
    public static void clear() {
        HEADER.remove();
    }
    
    public static void set(HashMap<String, String> headers) {
        HEADER.set(headers);
    }
    
    public static HashMap<String, String> get() {
        return HEADER.get();
    }}

获取上下文参数：

```java
public static Map<String, String> getHeaderMap() {
    Map<String, String> headerMap = Maps.newLinkedHashMap();
	try {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (requestAttributes == null) {
			return headerMap;
		}
	HttpServletRequest request = requestAttributes.getRequest();
	Enumeration<String> enumeration = request.getHeaderNames();
	while (enumeration.hasMoreElements()) {
		String key = enumeration.nextElement();
		String value = request.getHeader(key);
		headerMap.put(key, value);
	}
	} catch (Exception e) {
	log.error("《RequestContextUtil》 获取请求头参数失败：", e);
}
	return headerMap;
}
```

feign转发处理：

```java
@Slf4j
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {
   @Override
   @SneakyThrows
   public void apply(RequestTemplate requestTemplate) {
      log.debug("========================== ↓↓↓↓↓↓ 《FeignRequestInterceptor》 Start... ↓↓↓↓↓↓ ==========================");
      Map<String, String> threadHeaderNameMap = RequestHeaderHandler.getHeaderMap();
      if (!CollectionUtils.isEmpty(threadHeaderNameMap)) {
          threadHeaderNameMap.forEach((headerName, headerValue) -> {
          log.debug("《FeignRequestInterceptor》 多线程 headerName:【{}】 headerValue:【{}】", headerName, headerValue);
          requestTemplate.header(headerName, headerValue);
           });
         }
      Map<String, String> headerMap = RequestContextUtil.getHeaderMap();
      headerMap.forEach((headerName, headerValue) -> {
      log.debug("《FeignRequestInterceptor》 headerName:【{}】 headerValue:【{}】", headerName, headerValue);
      requestTemplate.header(headerName, headerValue);
    });
       log.debug("========================== ↑↑↑↑↑↑ 《FeignRequestInterceptor》 End... ↑↑↑↑↑↑ ==========================");
     }
}
```


在项目中：
登录拦截器：

```java
public class BaseLoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HashMap<String, String> headers = this.getHeaders(request);
        InheritableThreadLocalHeader.set(headers);
    }

    protected HashMap<String, String> getHeaders(HttpServletRequest request) {
        HashMap<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
}

```


feign转发：

```java
@Configuration
public class FeignConfig implements RequestInterceptor {
@Value("${server.gatewayUrl}")
private String gatewayUrl;

@Bean
public Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
}

//转发header
@Override
public void apply(RequestTemplate requestTemplate) {
    try {
        HashMap<String, String> headers = InheritableThreadLocalHeader.get();
        for (String headerName : headers.keySet()) {
            requestTemplate.header(headerName, headers.get(headerName));
        }
        if (requestTemplate.feignTarget().url().equals(String.format("http://%s", requestTemplate.feignTarget().name()))) {
            requestTemplate.target(gatewayUrl);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
```







## db
##### 1.sql 同时（更新）update和（查询）select同一张表
当要使用本表的数据更新本表时，容易出错：

如下：

update b set aaa=select max(MAX_def_60M) as max from b

[Err] 1064 - You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'select max(MAX_def_60M) as max from b' at line 2

解决办法：再嵌套一层查询

update b set aaa=(select max from (select max(MAX_def_60M) as max from b) as temp) 不能同时读写的原因：mysql读写锁锁定的问题

若事务T对数据对象A加上S锁，则事务T可以读A但不能修改A，其他事务只能再对A加S锁，而不能加X锁，直到T释放A上的S锁。这保证了其他事务可以读A，但在T释放A上的S锁之前不能对A做任何修改。

写锁：

若事务T对数据对象A加上X锁，事务T可以读A也可以修改A，其他事务不能再对A加任何锁，直到T释放A上的锁。这保证了其他事务在T释放A上的锁之前不能再读取和修改A。

加了共享锁的对象，可以继续加共享锁，不能再加排它锁。加了排它锁后，不能再加任何锁。

那么说我在更新一个表的时候，我锁定了一行，这一行我是不能加读锁的了，所以这时我查询这张表，就会出现这种问题。

加一层子查询之后成功的原因（待补充）：

mysql在from子句中遇到子查询时，先执行子查询并将结果放到一个临时表中，我们通常称它为“派生表”；临时表是没有索引、无法加锁的。