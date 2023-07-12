**jvm配置**

```java
-server
-Xmx6g
-Xms 6g
-XX:NewRatio=1
-xx:+UseConcMarkSweepGC
-xx:MetaspaceSize=512m
-xX:MaxMetaspaceSize=512m
xx:+PrintGCDetails
-xx:+PrintGCDatestamps
-xloggc:/export/Logs/gc.log
xx:+PrintTenuringDistribution
```



![image-20230711091013467](..\notes\img\jvm\堆栈.png)





![image-20230711084055316](..\notes\img\jvm\栈帧.png)