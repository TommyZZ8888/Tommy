# spring

### 1、Bean的生命周期

在spring项目中，bean是最重要的存在。

bean的生命周期分为三部分：==**生产**==  >>    ==**使用**==  >>    ==**销毁**==

其中生产部分比较复杂。

生产部分分为四部分： **实例化**  >>  **填充属性**  >>  **初始化**  >>  **销毁**  



普通java创建对象和spring创建bean实例不同。

java创建对象分为四部分：将java文件编译为class文件，等到类需要被初始化时（new或者反射），将class文件通过类加载器加载到jvm，，最后初始化对象供我们使用。



spring所管理的bean不同，除了class对象，还会使用beandefinition的实例描述对象信息。

我们可以在spring管理的bean对象上添加一系列描述：比如@scope，@lazy，@DependOn。

java是描述类的信息，spring是描述对象的信息。

在spring启动时，会通过xml/注解/javaconfig来查询需要被spring管理的bean信息。然后将这些查询到的bean封装成beanDefinition，，最后把这些信息放到BeanDefinitionmap中，到这里只是把定义的元数据加载起来，还没有进行实例化。

这个map中的key是beanname，value是bean。

然后通过**createBean**去遍历**beanDefinitionmap**,执行**BeanFactoryPostProcessor**的bean工厂后置处理器的逻辑。我们可以自定义BeanFactoryPostProcessor来对我们定义好的**bean元数据进行获取或者修改**。



接下来就是实例化对象了，通过**doCreateBean**方法对bean进行实例化，spring一般是通过**反射**来选择合适的构造器对bean进行实例化。

实例化之后将对象放到**三级缓存**中。

这里的实例化只是把bean对象创建出来，还没有进行属性填充的。比如我的对象是A，A又依赖B对象，此时B对象还是null。

接下来通过**populateBean**方法来对bean进行属性填充。

填充之后开始进行初始化，通过**InvokeAwareMethod**方法来对实现了aware相关接口的bean填充相关的资源。比如我们在项目中会抽取出来一个实现了ApplicationContextAware接口的工具类，来通过获取ApplicationContext对象来获取相关的bean。

之后会通过**BeanPostProcesser**后置处理器，该处理器有两个方法，**before和after**。先执行**BeanPostBeforeProcessor**方法，之后执行**InvokeInitMethod**方法，比如**@PostConstruct**，实现了tializatingBean接口的方法。最后执行**beanPostProcessor的after**方法。到这里实例化就结束了。

销毁的时候就看有没有配置相关的destroy方法，执行就完事了



最后总结：

在Spring Bean的生命周期，Spring预留了很多的hook给我们去扩展

Bean实例化之前有BeanFactoryPostProcessor

Bean实例化之后，初始化时，有相关的Aware接口供我们去拿到Context相关信息

环绕着初始化阶段，有BeanPostProcessor（AOP的关键）

在初始化阶段，有各种的init方法供我们去自定义

而循环依赖的解决主要通过三级的缓存

在实例化后，会把自己扔到三级缓存（此时的key是BeanName，Value是ObjectFactory）

在注入属性时，发现需要依赖B，也会走B的实例化过程，B属性注入依赖A，从三级缓存找到A

删掉三级缓存，放到二级缓存



关键源码方法（强烈建议自己去撸一遍）

- `org.springframework.context.support.AbstractApplicationContext#refresh`(入口)
- `org.springframework.context.support.AbstractApplicationContext#finishBeanFactoryInitialization`(初始化单例对象入口)
- `org.springframework.beans.factory.config.ConfigurableListableBeanFactory#preInstantiateSingletons`(初始化单例对象入口)
- `org.springframework.beans.factory.support.AbstractBeanFactory#getBean(java.lang.String)`（万恶之源，获取并创建Bean的入口）
- `org.springframework.beans.factory.support.AbstractBeanFactory#doGetBean`（实际的获取并创建Bean的实现）
- `org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#getSingleton(java.lang.String)`（从缓存中尝试获取）
- `org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#createBean(java.lang.String, org.springframework.beans.factory.support.RootBeanDefinition, java.lang.Object[])`（实例化Bean）
- `org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#doCreateBean`（实例化Bean具体实现）
- `org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#createBeanInstance`（具体实例化过程）
- `org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#addSingletonFactory`（将实例化后的Bean添加到三级缓存）
- `org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#populateBean`（实例化后属性注入）
- `org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#initializeBean(java.lang.String, java.lang.Object, org.springframework.beans.factory.support.RootBeanDefinition)`（初始化入口）

