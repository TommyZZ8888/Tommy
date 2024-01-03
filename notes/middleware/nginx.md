## Nginx

主要用于**http服务器**    **反向代理**    **负载均衡**



### **wbe服务器**

```nginx
events {
    //全局
    worker_connections  1024;
}


http {
    include       mime.types;
    default_type  application/octet-stream;

    sendfile        on;

    server {
        listen       8081;
        server_name  localhost;

        //默认类型
        default_type text/html;

        //最底层的匹配 可以匹配任何url
        location / {
           echo "hello nginx";
        }

        // = 开头表示精确匹配
		location = /a {
		   echo " =/a";
		}
		
        //^~ 开头表示uri以某个常规字符串开头，理解为匹配 url路径即可。nginx不对url做编码，因此请求为/static/20%/aa，可以被规则^~ /static/ /aa匹配到（注意是空格）。
		location ^~ /a {
			echo " =^~ /a";
		}
        
        location ^~ /a/b {
			echo " =^~ /a/b";
		}
		
        //~ 开头表示区分大小写的正则匹配 以/a-z开头的路径
		location ~ ^/[a-z] {
			echo " ~/[a-z]";
		}
		
		location ~ ^/\w {
			echo " ~/\w";
		}
		
		
    }

}

```

**匹配规则**

- =  ~ ^~ 普通文本四个优先级较高的先匹配
- 同优先级的，匹配程度较高的先匹配
- 匹配程度一样的，则写在前面的先匹配



##### 语法规则： location [=|~|~*|^~] /uri/ { … }

= 开头表示精确匹配

^~ 开头表示uri以某个常规字符串开头，理解为匹配 url路径即可。nginx不对url做编码，因此请求为/static/20%/aa，可以被规则^~ /static/ /aa匹配到（注意是空格）。

~ 开头表示区分大小写的正则匹配

~* 开头表示不区分大小写的正则匹配

!~和!~*分别为区分大小写不匹配及不区分大小写不匹配 的正则

/ 通用匹配，任何请求都会匹配到。

多个location配置的情况下匹配顺序为（参考资料而来，还未实际验证，试试就知道了，不必拘泥，仅供参考）：

首先匹配 =，其次匹配^~, 其次是按文件中顺序的正则匹配，最后是交给 / 通用匹配。当有匹配成功时候，停止匹配，按当前匹配规则处理请求。

例子，有如下匹配规则：

**1**

```nginx
location = / {精确匹配，必须是127.0.0.1/

#规则A

}

location = /login {精确匹配，必须是127.0.0.1/login

#规则B

}

location ^~ /static/ {非精确匹配，并且不区分大小写，比如127.0.0.1/static/js.

#规则C

}

location ~ \.(gif|jpg|png|js|css)$ {区分大小写，以gif,jpg,js结尾

#规则D

}

location ~* \.png$ {不区分大小写，匹配.png结尾的

#规则E

}

location !~ \.xhtml$ {区分大小写，匹配不已.xhtml结尾的

#规则F

}

location !~* \.xhtml$ {

#规则G

}

location / {什么都可以

#规则H

}
```

那么产生的效果如下：

访问根目录/， 比如http://localhost/ 将匹配规则A

访问 http://localhost/login 将匹配规则B，http://localhost/register 则匹配规则H

访问 http://localhost/static/a.html 将匹配规则C

访问 http://localhost/a.gif, http://localhost/b.jpg 将匹配规则D和规则E，但是规则D顺序优先，规则E不起作用， 而 http://localhost/static/c.png 则优先匹配到 规则C

访问 http://localhost/a.PNG 则匹配规则E， 而不会匹配规则D，因为规则E不区分大小写。

访问 http://localhost/a.xhtml 不会匹配规则F和规则G，http://localhost/a.XHTML不会匹配规则G，因为不区分大小写。规则F，规则G属于排除法，符合匹配规则但是不会匹配到，所以想想看实际应用中哪里会用到。

访问 http://localhost/category/id/1111 则最终匹配到规则H，因为以上规则都不匹配，这个时候应该是nginx转发请求给后端应用服务器，比如FastCGI（php），tomcat（jsp），nginx作为方向代理服务器存在。

###### 所以实际使用中，个人觉得至少有三个匹配规则定义，如下：

```nginx
#这里是直接转发给后端应用服务器了，也可以是一个静态首页

# 第一个必选规则

location = / {

proxy_pass http://tomcat:8080/index

}

# 第二个必选规则是处理静态文件请求，这是nginx作为http服务器的强项

# 有两种配置模式，目录匹配或后缀匹配,任选其一或搭配使用

location ^~ /static/ {

root /webroot/static/;

}

location ~* \.(gif|jpg|jpeg|png|css|js|ico)$ {

root /webroot/res/;

}

#第三个规则就是通用规则，用来转发动态请求到后端应用服务器

#非静态文件请求就默认是动态请求，自己根据实际把握

#毕竟目前的一些框架的流行，带.php,.jsp后缀的情况很少了

location / {

proxy_pass http://tomcat:8080/

}

#直接匹配网站根，通过域名访问网站首页比较频繁，使用这个会加速处理，官网如是说。

#这里是直接转发给后端应用服务器了，也可以是一个静态首页

# 第一个必选规则

location = / {

proxy_pass http://tomcat:8080/index

}

# 第二个必选规则是处理静态文件请求，这是nginx作为http服务器的强项

# 有两种配置模式，目录匹配或后缀匹配,任选其一或搭配使用

location ^~ /static/ {

root /webroot/static/;

}

location ~* \.(gif|jpg|jpeg|png|css|js|ico)$ {

root /webroot/res/;

}

#第三个规则就是通用规则，用来转发动态请求到后端应用服务器

#非静态文件请求就默认是动态请求，自己根据实际把握

#毕竟目前的一些框架的流行，带.php,.jsp后缀的情况很少了

location / {

proxy_pass http://tomcat:8080/

}
```



### 反向代理 负载均衡

```nginx
events {
    worker_connections  1024;
}


http {
    include       mime.types;
    default_type  application/octet-stream;

    sendfile        on;

    server {
        listen       8081;
        server_name  localhost;

        default_type text/html;

        location / {
           echo "hello nginx!";
        }
		
        location /a/ {
           proxy_pass https://www.baidu.com/;
        }

		location /b {
           proxy_pass https://www.baidu.com/;
        }

    }
}
```

**反向代理小结**

```
        location /a {
           proxy_pass https://ip;
        }

		//注意 /b后的反斜杠 和 ip后的反斜杠
		location /b/ {
           proxy_pass https://ip/;
        }
      上述配置会导致
      /a/x -> https://ip/a/x
      /b/x -> https://ip/x
        
```



```
events {
    worker_connections  1024;
}


http {
    include       mime.types;
    default_type  application/octet-stream;

    sendfile        on;
	#keepalive_timeout  65;
	
	upstream group1{
		server www.baidu.com weight=1;
		server cn.bing.com weight=10;
	}

    server {
        listen       8081;
        server_name  localhost;

        default_type text/html;
		
		location / {
			proxy_pass https://group1;
		}

    }
}
```

