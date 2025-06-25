## Spring篇

什么是Spring

```
Spring是个轻量级的框架，他有两大内核分别是IOC、AOP
```

IOC是什么 *

```
依赖注入和控制反转，它是一种思想，创建对象不是直接new出来，而是交给IOC容器，由容器去创建进行管理，这就是控制反转，管理者统一由容器去管理，不是由使用者去管理。比如我们的service和dao层，我们不是直接new出来，而是交给Spring容器，然后通过@Authired注解进行注入，这就是依赖注入。
```

[@Authired](https://github.com/Authired)和[@Resource](https://github.com/Resource)的区别

```
都是依赖注入，@Autowired是Spring定义的注解，而@Resource是Java定义的注解@Autowired是先根据类型（byType）查找，如果存在多个 Bean 再根据名称（byName）进行查找@Resource是先根据类型（byName）查找，如果存在多个 Bean 再根据名称（byType）进行查找
```

AOP是什么 *

```
AOP是面向切面编程，它这个思想就是代理模式，SpringAOP是基于动态代理实现的，默认是jdk动态代理；典型案例就是:记录日志、事务的注解
```

AOP常用注解

```
前置通知 Before后置通知 After返回通知 如果有异常不执行异常通知 出现异常才执行环绕通知
```

如何使用aop自定义日志

```
1.定义切面，哪些方法，哪些类下方法需要进行拦截2.然后写通知前逻辑，获取请求参数3.然后写通知后的逻辑，获取执行结果的状态，继续记录日志4.如果出现异常，如何处理，具体的记录错误日志
```

循环依赖是什么，怎么解决的

```
循环依赖就是多个Bean互相依赖，创建a依赖b，创建b依赖c，创建c依赖a，导致所有对象创建不出来。解决：Spring默认是单例模式，不会报错，Spring是通过三级缓存来解决循环依赖，三级缓存是用3个Map对象，一级缓存就是单例池；
```

Spring 事务原理

```
我们一般在某个方法上增加@Transactional注解，底层使用AOP，如果没有异常，SQL执行完自动提交事务，如果有异常会回滚
```

Spring 事务失效场景

```
1 该类没有被IOC容器进行管理，加载到容器里2 该方法不是public类型3 业务代码有try catch捕获异常
```

Spring 用了哪些设计模式

```
BeanFactory用了工厂模式、AOP用了动态代理模式、SpringMVC中handlerAdaper用来适配器模式、Spring里的监听器用了观察者模式、RestTemplate用来模板方法模式
```

SpringBoot自动配置原理 *

```
自动装配，在之前的SSM项目，每引一个第三方框架，就需要在xml进行构建配置，很麻烦，所有有了SpringBoot自动装配，目的：简化开发在SpringBoot启动类有一个在启动类有@SpringbootApplication注解，该注解下有3个重要注解：1 @SpringbootConfiguration:表示启动类是一个自动配置类；2 @CompontScan:自动扫描组件，默认扫描该类所在包及其子包下所有带有指定注解的类，将它们自动装配到bean容器中，如：@Controller、@Service等注解，加载到容器中；3 @EnableAutoConfiguration，该注解开启自动配置该注解下有：@AutoConfigurationPackage注解，该注解里有@Import注解，引入一个Registrar类，帮我们批量导入一系列默认的自动化配置，把一些基础的包里带有@Bean、@Configuration等注解，扫码到容器里，以备接下来完成自动化配置；@Import，就是导入，这里有一个方法，将spring.factories文件里需要自动装配的类，全部加载到ioc容器中，这些自动配置类还会通过xxxProperties文件里配置来进行属性设值，属性进行绑定，就是进行条件装配。最终把这些类全部创建好，存入到IOC容器里。在springboot项目，基于第三方所有的默认配置被预先定义好，在spring.boot.autoconfigure的包
```

Spring 中的事务传播行为有哪些

```
有7种传播行为1 PROPAGATION_REQUIRED 如果当前存在事务，则加入该事务；如果不存在事务，则创建一个新的事务（默认的）2 PROPAGATION_SUPPORTS 如果当前存在事务，则加入该事务；如果不存在事务，那就没有事务3 PROPAGATION_MANDATORY如果当前存在事务，则加入该事务；如果不存在事务，则抛出异常4 PROPAGATION_REQUIRES_NEW  创建一个新的事务，如果当前存在事务，创建新的事务5 PROPAGATION_NOT_SUPPORTED 以非事务方式执行操作，如果当前存在事务，创建新的事务6 PROPAGATION_NEVER   以非事务方式执行操作，如果当前存在事务，则抛出异常7 PROPAGATION_NESTED  如果当前存在事务，则在嵌套事务内执行；如果不存在事务，则创建一个新的事务。
```

Spring 中的 RestTemplate 是什么？

```
Spring中提供了RestTemplate类，可以方便地进行HTTP请求//创建RestTemplate对象RestTemplate restTemplate = new RestTemplate();//发送GET请求String result = restTemplate.getForObject(url, String.class);//发送POST请求HttpHeaders headers = new HttpHeaders();headers.setContentType(MediaType.APPLICATION_JSON);HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);String result = restTemplate.postForObject(url, requestEntity, String.class);//发送PUT请求HttpHeaders headers = new HttpHeaders();headers.setContentType(MediaType.APPLICATION_JSON);HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);restTemplate.put(url, requestEntity);//发送DELETE请求restTemplate.delete(url);
```

Spring 中的过滤器和拦截器有什么区别

```
依赖不同：过滤器是依赖于Servlet容器的，而拦截器是依赖于SpringMVC框架过滤器进入Servlet容器前进行处理逻辑，拦截器基本容器已经进入了Spring容器中处理
```

SpringBoot常用注解

```
@RestController、@RequestMapping("/path") 、@Autowired、@PathVariable、@Controller@Service@Compont、@Transaction
```

Spring的bean是线程安全的吗？

```
Spring的默认bean作用域是单例的，单例的bean不是线程安全的，controller、service、dao，他们不需要保证线程安全；想解决线程安全可以使用ThreadLocal
```

Spring 有哪些方法进行依赖注入

```
依赖的对象的实例交给Spring进行管理，不是我们自己new出来的对应，一般有构造器注入、set注入、autowire自动注入，这个注解
```

Spring 中的 OAuth2 是什么

```
它是授权协议，就是只有经过授权的用户才能访问资源。
```

CSRF 攻击是什么？如何防止它？

```
CSRF是Web应用程序安全漏洞，它是利用已经登录成功的用户，然后操作其他系统页面，该页面恶意操作，模拟请求目标网站地址恶意操作，比如：修改用户信息的请求。一般我们使用授权框架是Spring Security，它默认是开启CSRF保护的
```

Spring 中的 JWT 是什么

```
全称是JSON Web Token，就是基于json格式对象实现用户的身份验证，单点登录广泛使用JWT；当前端输入账号和密码进行登录，后端把token、该用户返回前端，之后每次请求，前端需要把token值携带传给后端，来验证是否有权限访问。之前我们一般使用session进行认证，但session是有缺点的，session的缺点：1.现在我们都是分布式项目，不同jar包项目的session是不同的，就需要session复制，有一个中间件存session值，比较麻烦2.session机制方式基于cookie，无法跨域
```

Spring 中的异常处理机制是什么

```
底层是利用AOP实现的，统一的异常处理器接口HandlerExceptionResolver，实现这个接口，就可以定义全局异常处理器
```

Spring 和 Spring Boot 是什么关系

```
Spring是一个Java开源框架，而Spring Boot是基于Spring框架的一种快速开发框架。
```

Spring、Spring MVC和SpringBoot有什么区别

```
Spring是一个Java开源框架Spring MVC是Spring框架的一个模块Spring Boot是一个基于Spring框架的快速开发应用程序的框架
```

什么是Spring Boot Starter

```
Starter是模块，一个模块需要哪些依赖库它进行引入，使用者进行引入该Starter是模块就可以实现某种功能。如：引入spring-boot-starter-data-jpa， 就可以实现与数据库的连接，操作数据库引入spring-boot-starter-data-redis 就可以使用Redis的功能
```

如何自定义Spring Boot Starter

```
1 创建maven项目2 将自己的类，当项目启动，就一些配置类加载到IOC容器中，在spring.factories文件中定义3 开启一些自动加载到注解4 被父容器引入，就可以使用该模块的功能
```

## SpringCloud

微服务是什么

```
有很多模块，每一个模块都是一个单独的系统，就是微服务。
```

你知道哪些RPC框架

```
RPC（远程过程调用）Dubbo 和 SpringCloud区别：SpringCloud是微服务架构一站式的解决方案架构、Dubbo主要是服务的调用和管理Dubbo远程调用基于rpc（底层netty），SpringCloud是基于httpDubbo支持的语言有java、go、python，SpringCloud是基于java的
```

SpringCloud由什么组成 *

```
注册中心：Eureka、远程调用：Feign 、负载均衡：Ribbon、断路器：Hystrix 、网关：Zuul、配置中心：Config、消息队列：SpringCloud Stream 、链路跟踪：SpringCloud Sleuth
```

Eureka的工作原理

```
Eureka是注册中心，它有一个服务端和客户端，客户端连接上服务端，并保持心跳（每30秒发送1次），然后我们可以通过Eureka服务端来监控该服务是否正常。然后Eureka默认是有自我保护机制的，默认是90秒内，服务端没有收到客户端的心跳检测，就会进入自我保护机制，进入自我保护机制，Eureka服务端不会马上把该服务删除掉，如果我们关闭自我保护机制，Eureka服务端会立刻把该服务进行下线。
```

Eureka、Zookeeper、Nacos、Consul的区别 *

```
Eureka、Zookeeper都是注册中心，Nacos、Consul是可以做注册中心又可以做配置中心；在CAP理论，Zookeeper和Consul更专注CP、Eureka是AP，Nacos默认是AP，但它可以支持CP；Eureka默认有自我保护机制，默认客户端的服务挂了，服务端不会马上把该服务下线，而Nacos会马上把该服务进行下线。
```

Ribbon的原理

```
Ribbon它是作负载均衡，默认是轮询算法；它的算法：次数%机器数，取余就得出该list的下标，从而得到该服务端ip地址。
```

SpringCloud有几种调用接口方式

```
Feign、RestTemplate
```

Hystrix断路器是什么

```
多个服务相互调用，A服务调用B服务，如果过程中出现熔断限流，如果一致被阻塞，会导致整体系统崩溃；所以有了Hystrix断路器，就是失败的话进入失败的调用方法。
```

什么是服务降级、服务熔断、服务隔离、服务限流 *

```
服务降级：当出现请求超时、资源不足，会触发服务降级，执行fallback方法里的逻辑；服务熔断：当A服务调用B服务，B服务异常，如网络波动，达到阈值自动触发降级；服务隔离：为隔离的服务开启一个独立的线程，这样在高并发情况下，也不会影响该服务，每个服务是互相隔离的，隔离方法：线程池隔离、信号量隔离；服务限流：类似秒杀高并发场景，避免一窝蜂过来导致拥堵，限流方法有：时间窗口（令牌、漏斗算法）、请求总量计数；服务超时：设置最大的响应时间，如果超时，下游服务未返回结果，则端口服务之间的请求连接，来释放资源；降级：A服务调用B服务，发送10个请求，即使每个请求都超时，也会去请求B。熔断：A服务调用B服务，发送10个请求，失败率为50%，如果5个请求失败，此时失败率到了50%，那么后面的5个请求就不会走到B服务。
```

Gateway与Zuul的区别

```
他们都服务网关，Zuul最早点版本是不支持长链接，也不是异步处理请求，基于Servlet的，很容易造成API阻塞，是BIO（同步阻塞）Gateway是阿里巴巴团队开发的，它底层是基于Netty，是AIO（异步非阻塞），后期的Zuul版本改成了AIO，可Gateway适配SpringCloud每个组件，它的使用率就越来越高，默认自带负载均衡、限流配置，而Zuul是不匹配的。
```

集群高并发情况下，如何保证分布式唯一全局id生产？ *

```
1.UUID 缺点：不是有序的，在mysql官网上有主键不建议设置UUID，UUID是字符串是36位的，不是有序递增的数字，导致磁盘读写是随机读写，不是顺序读写，读写速度慢；也容易导致索引的页分列。2.数据库自增主键通过对该表某个字段设定唯一索引，每次新增，如果没有该值会进行新增，有会更新最新的注解id，保证数据库有唯一的该值replace into 表 (字段) values ("a");得到刚 insert 进去记录的主键值，只适用与自增主键；select last_insert_id();缺点：单机的话，会增加数据库压力，高并发的情况下，mysql处理数据能力弱。3.基于Redis生生成全局idRedis是单线程可以保证数据的安全性，Redis处理数据能力高于mysql缺点：一台Redis会有单机故障，如果部署集群，为了自增主键部署集群，得不偿失。4.雪花算法生成唯一id，他字符类型是long，long是64位的。第一位是0，然后41位是时间戳（该时间戳范围可以用69年，从1970年开始，可以用到2039年）然后是5位的机器id和5位的服务id 最后12位是序列号（表示同一机器同一毫秒生产的序号）优点：每秒可生成百万个不重复 id、不依赖第三方库或者中间件、算法简单在内存中生成，效率高。缺点：依赖时间戳，如果系统更改系统时间，会出现回拨现象出现id重复；解决，每次生生成的需要对比当前服务器时钟是否被回拨。5.其他生产分布式id百度开源的生成分布式id工具：UidGenerator美团开源的生成分布式id工具：Leaf案例：// cn.hutool.core.lang.snowflake;@Componentpublic class GeneratorSnowflake extends BaseController {    private long workerId = 0;        // 当前机房，值是0号机房，0-31    private long datacenterId = 1; // 当前机器，1号机器    private Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);    @PostConstruct    public void init() {        try {            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());            log.info("当前机器的workerId:{}", workerId);        } catch (Exception e) {            workerId = NetUtil.getLocalhostStr().hashCode();            log.info("当前机器 workId:{}", workerId);        }    }    public synchronized long snowflakeId() {        return snowflake.nextId();    }    public synchronized long snowflakeId(long workerId, long datacenterId) {        snowflake = IdUtil.createSnowflake(workerId, datacenterId);        return snowflake.nextId();    }    public static void main(String[] args) {        System.out.println(new GeneratorSnowflake().snowflakeId());    }}
```

SpringCloud主要解决什么问题

```
解决服务之间的通信、负载平衡，方便服务集中管理；常用组件有注册中心、配置中心、远程调用。服务熔断、网关
```

CAP理论是什么

```
C：一致性，这里指的强一致性，也就是数据更新完，访问任何节点看到的数据完全一致A：可用性，就是没有发生故障在在规定时间内返回合正确结果P：容灾性，当网络不稳定时节点之间无法通信，造成分区，这时要保证系统可以继续正常服务。提高容灾性的办法就是把数据分配到每一个节点当中，所以P是分布式系统必须实现的，然后需要在C和A中取舍然后有了，BASE理论：BA - 基本可用，分布式系统出现故障，保障核心可用S  - 软状态，在一定时间，出现不一致状态E  - 最终一致性，无法保证强一致性，但在软状态结束后，最终达到数据一致性
```

为什么不能同时保证一致性和可用性呢

```
举个例子，请求一组数据，A服务提供一部数据，B服务提供另一部数据；然后再封装返回给客户端。当A服务挂了，如果保证数据一致性，必须等A服务启动，这时需要时间等待，违背了可用性。当A服务挂了，如果保证数据可用性，返回默认的旧数据，违背了数据一致性问题。
```

熔断限流是什么

```
SprngCloud中用Hystrix组件来进行降级、熔断、限流熔断是对于消费者来讲，当提供者请求时间过长，为了不影响性能对该链接进行熔断；限流是对于提供者来讲，为了防止某个消费者流量太大，导致其它更重要的消费者请求无法及时处理。限流可用通过拒绝服务、服务降级、消息队列延时处理、限流算法来实现
```

常用限流算法

```
计数器算法：使用redis的过期机制实现漏桶算法：一般使用消息队列来实现，请求任务放到队列中，当队列满的时候开始拒绝请求令牌桶算法：预先往队列里存放一些token，所有请求都必须拿到token才能访问系统，当队列里的token都拿完，就开始拒绝请求
```

## Spring Cloud Alibaba

Spring Cloud Alibaba是什么

```
它是基于和符合Spring Cloud标准的阿里的微服务解决方案
```

Nacos中的保护阈值的作用是什么？ *

```
客户端会每隔5秒发送一次心跳检测，服务端如果没有收到客户端的心跳，会把该实例标记不健康的服务，如果30秒没有收到，就会删除该实例。如果保护阈值设置10，总共有10个服务，其中5个服务挂了，不设置保护阈值，来10个请求，进行负载均衡，它平均分配到这5台健康的服务，如果高并发下，需要10个服务去分担压力，现在是5台，可能会导致整体服务都挂了。如果设置了保护阈值，这样就是把全部请求分配给全部的10台服务，会出现一部分请求成功和一部分失败，不会造成整体失败。
```

Nacos的就近访问是什么意思？

```
Nacos是可以设置集群的，就是服务A调用服务B，会看A服务属于哪个集群，会请求同一集群的服务B，就是就近访问。
```

如何理解Nacos中的命名空间？

```
默认是pulibc，命名空间之间是独立的，还有组的概念，我理解的命名空间相当于java的包名，组就是类名。我们通过不同的命名空间和组指定不同环境都配置和注册。例子：618时期的服务A、平常时期的服务A、过年时间的服务A是不同的级别访问请求，对应适合的配置文件。
```

什么是Sentinel

```
它是分布式保护服务框架，可以实现服务的降级、熔断、流控管理。可配置流控、熔断、热点配置。
```

流控规则有什么？*

```
类型分为：QPS和线程数，设置阈值，达到该值就进行流控处理；流控模式：直接，关联，链路（多个请求都请求到同一服务上）流控效果：快速失败，预热（它有预热时长，当流量大量来的时候，慢慢的把流量放进来，慢慢的把阀值增长到设置的阀值），排队等待（均匀等待，有超时时间设置）
```

QPS和线程数的区别

```
QPS：每秒钟的请求数量，当1秒达到设置的阈值，就进行限流。线程数:服务端的处理请求的线程超过阈值的时候，就进行限流。
```

服务降级规则（熔断）有哪些策略 *

```
1.RT（平均响应时间）：平均响应时间超出阈值，并且时间窗口也满足条件，才会触发熔断。（秒级）2.异常比例：平均响应时间超出阈值，异常比例达到多少比例就会触发（秒级)3.异常次数：异常数（分钟统计）超过阈值时（分钟)
```

热点规则

```
热点数据就是经常访问的数据，比如某些参数是某个值，是热点数据，进行限流操作，其他参数不进行限流。
```

Sentinel的限流和Gateway的限流有什么差别

```
Sentinel限流算法：默认限流模式是基于滑动时间窗口算法排队等待的限流模式则基于漏桶算法而热点参数限流则是基于令牌桶算法Gateway的限流算法是令牌桶算法
```

Sentinel与Hystrix线程隔离区别

```
Sentinel可以有QPS限流，基于计数；Hystrix是根据线程池，根据线程来限流的。
```

Seata的执行流程、生命周期

```
它分为：TC 事务协调者、TM 事务管理器、RM 资源管理器首先就是TC，就是开启事务的，它进行开启事务，调用TM，生成全局事务id；全局事务id会在每次传输中携带；然后TC发送SQL语句，给RM执行SQL语句，产生了分支事务，交给TM；TM向TC发送全局事务id、分支事务id，此时根据不同的模式，判断是否提交该SQL的事务；然后执行其他服务的SQL语句，都是分支事务；都执行完，TM会进行闭环处理，也是根据根据不同的模式，判断处理，是否回滚，回滚执行回滚的SQL语句，如不需要回滚，会删除分支事务，解锁全局事务。
```

Seata的有哪些几种模式

```
1 AT（弱一致）也是默认的，它是二阶段提交，性能适中。    第一阶段，执行SQL脚本之前，会记录该SQL的前后数据的快照记录，SQL执行完，就进行提交事务； 第二阶段，如果都执行完，不需要回滚，就删除快照记录数据，如果需要回滚，根据快照记录数据进行回滚数据。2 XA（强一致）它是二阶段提交，是数据强一致，性能最差。    第一阶段，执行SQL脚本之前，并不会提交事务； 第二阶段，如果都执行完，不需要回滚，就提交事务，需要回滚，会进行回滚，完全交给数据库处理。3 TCC（弱一致），性能比AT模式强，需要手动写代码处理，不依赖数据，但我看官网在高版本中也依赖数据库了。    第一阶段，执行SQL脚本，进入Try方法里，会根据自定义的逻辑对数据进行资源锁定，为冻结数据，然后提交事务；    第二阶段，如果都执行完，不需要回滚，就删除冻结数据，如果需要回滚，会进入Cancel方法，手动进行写回滚逻辑。    它还需要考虑方法的幂等性，空回滚问题；4 Saga（弱一致），它性能最好，但数据容易出脏数据。    第一阶段，执行SQL脚本，提交事务；    第二阶段，如果都执行完，不需要回滚，不处理，需要回滚，手动进行写回滚逻辑。
```

Seata如何确保分布式事务的一致性？

```
Seata使用两阶段提交，默认的模式，在第一阶段，就会把该SQL语句进行事务提交，在第二阶段，来判断是否回滚，全部的分支事务都正常，全局事务也进行提交，删除该数据的快照记录，如果其中一个分支事务有问题，全局事务也全部根据快照记录进行回滚。
```

Seata的分布式事务如何处理跨多个微服务的情况？

```
Seata可以处理跨多个微服务的分布式事务。每个微服务都有一个分支事务参与到全局事务中。微服务之间的通信通常通过RPC调用来实现，Seata可以在RPC调用中传播全局事务上下文，从而实现跨服务的事务一致性。
```

Seata的事务日志存储在哪里

```
存储在全局事务协调器（TC）的数据库中
```

Seata如何处理分布式事务的超时和补偿

```
如果全局事务长时间没有完成，Seata可以自动回滚事务，对于TCC模式和Saga模式，我们要手动写补偿逻辑。
```

客户端负载与服务端负载有什么区别

```
客户端负载：调用微服务接口，在注册中心上获取服务列表缓存到JVM本地，在本地实现RPC的服务调用；服务端负载：Nginx，客户端把请求交给Nginx，它负载转发，由服务端实现；
```