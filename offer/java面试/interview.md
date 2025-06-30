# 常见面试题

![image-20220604120532421](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220604120532421.png)



高难度面试题：

#### 1、请你谈谈网站是如何进行访问的？

1.输入一个域名，回车

2.检查本机的C:\Windows\System32\drivers\etc\hosts配置文件下有没有这个域名映射；

​	1.有，直接返回对应的ip地址，在这个地址中，有我们需要访问的web程序，可以直接访问

127.0.0.1   www.qinjiang.com

​	2.没有就去DNS找，找到就返回，找不到就返回找不到



#### 2、当你在浏览器中地址栏输入地址并回车的一瞬间到页面能够展示回来，经历了什么？

1.域名解析

2.发起TCP的三次握手

3.建立起TCP连接后发起Http请求

4.服务器响应Http请求，浏览器得到Html代码

5.浏览器解析HTML代码，并获取其中的资源（CSS，HTML，JS图片）

6.浏览器对页面进行渲染呈现



### 3.throw和throws的区别

- throws 和 throw的区别
  - throws方法声明上，方法后面                    throw用在方法内
  - throws后面跟异常类名，调用者处理         throw后面跟异常对象，方法内部处理
  - throws告诉调用者有可能发生该异常         throw如果代码一旦执行，必抛出异常

### 4.final  finally  finalize 的区别

1、final修饰符（关键字）。被final修饰的类，就意味着不能再派生出新的子类，不能作为父类而被子类继承。因此一个类不能既被abstract声明，又被final声明。将变量或方法声明为final，可以保证他们在使用的过程中不被修改。被声明为final的变量必须在声明时给出变量的初始值，而在以后的引用中只能读取。被final声明的方法也同样只能使用，即不能方法重写。

{最终的意思,修饰类不能被继承,方法不能重写,变量只能赋值一次}

2、finally是在异常处理时提供finally块来执行任何清除操作。不管有没有异常被抛出、捕获，finally块都会被执行。try块中的内容是在无异常时执行到结束。catch块中的内容，是在try块内容发生catch所声明的异常时，跳转到catch块中执行。finally块则是无论异常是否发生，都会执行finally块的内容，所以在代码逻辑中有需要无论发生什么都必须执行的代码，就可以放在finally块中。

{处理异常的关键字,finally块中的代码肯定会执行,除非jvm虚拟机停止}

3、finalize是方法名。java技术允许使用finalize（）方法在垃圾收集器将对象从内存中清除出去之前做必要的清理工作。这个方法是由垃圾收集器在确定这个对象没有被引用时对这个对象调用的。它是在object类中定义的，因此所有的类都继承了它。子类覆盖finalize（）方法以整理系统资源或者被执行其他清理工作。finalize（）方法是在垃圾收集器删除对象之前对这个对象调用的。 

{垃圾回收方法}

### 5.ArrayList   LinkedList  Vector 区别

1. ArrayList ，Vector底层都是数组，查询快增删慢，
   前者线程不安全，不同步，效率高，

   后者线程安全，同步，效率低

   2.LinkedList  底层是链表，查询慢增删快

### 6.转发VS重定向

相同点:

页面都会实现跳转;

不同点:

请求转发的时候，url不会产生变化;    307

重定向时候，url地址栏会发生变化;    302

### 7.重定向和转发的区别？

- 请求转发url不会产生变化，重定向会发生变化
- 请求转发可以携带参数，重定向不能携带参数
- 请求转发只能发送一个请求，重定向至少发送两次请求



### 了解java内存模型，GC，线程安全，线程池

**栈堆方法区**

解释内存中的栈(stack)、堆(heap)和方法区(method area)的用法。
答：通常我们定义一个基本数据类型的变量，一个对象的引用，还有就是函数调用的现场保存都使用JVM中的栈空间；而通过new关键字和构造器创建的对象则放在堆空间，堆是垃圾收集器管理的主要区域，由于现在的垃圾收集器都采用分代收集算法，所以堆空间还可以细分为新生代和老生代，再具体一点可以分为Eden、Survivor（又可分为From Survivor和To Survivor）、Tenured；方法区和堆都是各个线程共享的内存区域，用于存储已经被JVM加载的类信息、常量、静态变量、JIT编译器编译后的代码等数据；程序中的字面量（literal）如直接书写的100、"hello"和常量都是放在常量池中，常量池是方法区的一部分，。栈空间操作起来最快但是栈很小，通常大量的对象都是放在堆空间，栈和堆的大小都可以通过JVM的启动参数来进行调整，栈空间用光了会引发StackOverflowError，而堆和常量池空间不足则会引发OutOfMemoryError。

### HashMap和ArrayList


相同点
一、都是集合类：
1、首先HashMap和ArrayList都是类，也就是都是由class修饰的类而不是方法或者属性，这是其一。
2、其次HashMap和ArrayList都是集合，java中常见的集合有list、map、set等，所以这两个都是集合而不是数组或其它，这是其二。
不同点
一、数据结构不同。
HashMap
1、首先我们从源码来分析HashMap的常用的一些方法的底层是怎么实现的。首先来写一个main方法，方法中实例化一个HashMap。



    public class JDK18 {
    	public static void main(String[] args) {
    		Map<String,String> map = new HashMap<String,String>();
    		map.put("1", "2");
    	}
    }

在这里可能有小伙伴问为什么前面用 Map<String,String> map后面用 new HashMap<String,String>();为什么不直接写一个Map<String,String> map = new Map<String,String>();呢，为什么非要用HashMap呢，因为Map是一种集合类型，他是以一个key，value键值对方式进行存储的，但是这钟键以什么样的算法来生成数据存储到我们的空间中是不同的，所以由于算法的不同，map也有不同的实现方式，比如HashMap、TreeMap、ConrrentHashMap等，而且Map只是一个接口，里面只有接口方法而没有实现，而这些HashMap继承并实现了Map的接口，所以我们需要new一个具体的Map类才可以使用。下面我们按住ctrl点击put进入。
![image-20220602070739300](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220602070739300.png)
我们可以看到源码，如果你点击不能够看到源码证明你还没有配置，你可以根据网上的教程配置一下源码就可以看源码了。这里我们可以看到我们进入的是Map.class类当中，前面我们说过Map类只是一个接口类，里面只定义了接口并没有实现，所以我们复制一下put方法去HashMap.class类中搜索这个方法。
![image-20220602070759980](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220602070759980.png)
这里我们找到了这个方法，但是他还return了一个putVal方法，我们一会要点进putVal这个方法来查看具体的算法，这个时候我们看到putVal方法的第一个参数是将我们输入的key值做了一个hash()操作，这个时候我们点击这个hash方法里面查看一下。
![image-20220602070826820](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220602070826820.png)
点进去我们可以看到如下的一个三元运算
![image-20220602070843918](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220602070843918.png)
这里如果传入的key是null，就返回0，这个先不关注，先看有key值的时候是怎么计算的，有key值的话首先对key进行一个key.hashCode()方法的调用，我们点击去这个方法可以看到
![image-20220602070900360](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220602070900360.png)
他是一个用native修饰的方法，在这里说一下用native修饰的方法是底层用c语言封装的一个方法，我们在这里无法看到，但是我们可以用String new一个字符串然后调用hashCode()方法输出一下，比如String str = ‘a’;，那么他的hashCode()方法输出来的是97，这里是不是跟ASSIC很像，这个hashCode()是一个怎样的算法我们后期再讨论，这里我们先明白首先把我们的key值调用了一个hashCode()方法的运算，然后再^上一个h的无符号右移16位。
![image-20220602070918073](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220602070918073.png)
这里的无符号右移我们java基础中都学过例如h的二进制为下面这样
00000000 10101010 01010101 10101010
当我们无符号右移16位后就变成了这样
00000000 00000000 00000000 10101010
我们从左往右移动了16位，空出来的位置都用0来填充，最后将右移后的二进制与h的二进制来进行^操作，这样就是得到的key值，为什么要进行一个这样的异或操作呢，是为了让结果更加散列，这样的话可以减少Hash碰撞的几率。
ArrayList
我们接下来看一下ArrayList的源码，我们首先写一个简单的实例
![image-20220602070946876](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220602070946876.png)



    List<String> list = new ArrayList<String>();
            list.add("1");

然后我们点击去看add方法
![image-20220602070958138](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220602070958138.png)
我们可以看到我们传入的参数是一个参数，而不是HashMap的key，value形式的两个参数，这是其中一点不同，而ArrayList插入的顺序是默认++排队插入的，图
![image-20220602071018156](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220602071018156.png)
这里将传入的参数e插入到数组elementData【size++】的位置，而HashMap插入的位置是根据key算出的hashCode来决定插入的位置的，所以ArrayList是有序的，而HashMap是无序的。
总结
HashMap和ArrayList都是集合类，但是他们的存储结构有所不同，存储的方式也有所不同，假如我们有一个表单页面要显示所有的订单，我们可以使用ArrayList存储然后循环显示在页面上。假如我们要存储一个人的基本信息，可以用HashMap存储 key=“name”，value=“张三”，key=“password”，value=”123456“，这种的参数，所以我们在开发过程中根据实际情况来决定使用哪一种。



### HashMap

**1、为什么用HashMap？**

- HashMap是一个散列桶（数组和链表），它存储的内容是键值对(key-value)映射
- HashMap采用了数组和链表的数据结构，能在查询和修改方便继承了数组的线性查找和链表的寻址修改
- HashMap是非synchronized，所以HashMap很快
- HashMap可以接受null键和值，而Hashtable则不能（原因就是equlas()方法需要对象，因为HashMap是后出的API经过处理才可以）

**2、HashMap的工作原理是什么？**

- HashMap是基于hashing的原理，我们使用put(key,  value)存储对象到HashMap中，使用get(key)从HashMap中获取对象。当我们给put()方法传递键和值时，我们先对键调用hashCode()方法，计算并返回的hashCode是用于找到Map数组的bucket位置来储存Node 对象。这里关键点在于指出，HashMap是在bucket中储存键对象和值对象，作为Map.Node 。

　

 

- 以下是HashMap初始化 ，简单模拟数据结构

```
Node[] table=new Node[16] 散列桶初始化，table
class Node {
 hash;//hash值
 key;//键
　value;//值
　node next;//用于指向链表的下一层（产生冲突，用拉链法）
}
```

- 以下是具体的put过程（JDK1.8版）

1、对Key求[Hash](https://so.csdn.net/so/search?q=Hash&spm=1001.2101.3001.7020)值，然后再计算下标

2、如果没有碰撞，直接放入桶中（碰撞的意思是计算得到的Hash值相同，需要放到同一个bucket中）

3、如果碰撞了，以[链表](https://so.csdn.net/so/search?q=链表&spm=1001.2101.3001.7020)的方式链接到后面

4、如果链表长度超过阀值( TREEIFY THRESHOLD==8)，就把链表转成红黑树，链表长度低于6，就把红黑树转回链表

5、如果节点已经存在就替换旧值

6、如果桶满了(容量16*加载因子0.75)，就需要 resize（扩容2倍后重排）

- 以下是具体get过程(考虑特殊情况如果两个键的hashcode相同，你如何获取值对象？)

当我们调用get()方法，HashMap会使用键对象的hashcode找到bucket位置，找到bucket位置之后，会调用keys.equals()方法去找到链表中正确的节点，最终找到要找的值对象。



 

**3、有什么方法可以减少碰撞？**

- 扰动函数可以减少碰撞，原理是如果两个不相等的对象返回不同的hashcode的话，那么碰撞的几率就会小些，这就意味着存链表结构减小，这样取值的话就不会频繁调用equal方法，这样就能提高HashMap的性能。（扰动即Hash方法内部的算法实现，目的是让不同对象返回不同hashcode。）
- 使用不可变的、声明作final的对象，并且采用合适的equals()和hashCode()方法的话，将会减少碰撞的发生。不可变性使得能够缓存不同键的hashcode，这将提高整个获取对象的速度，使用String，Interger这样的wrapper类作为键是非常好的选择。为什么String,  Interger这样的wrapper类适合作为键？因为String是final的，而且已经重写了equals()和hashCode()方法了。不可变性是必要的，因为为了要计算hashCode()，就要防止键值改变，如果键值在放入时和获取时返回不同的hashcode的话，那么就不能从HashMap中找到你想要的对象。

 

**4、HashMap中hash函数怎么是是实现的?**

我们可以看到在hashmap中要找到某个元素，需要根据key的hash值来求得对应数组中的位置。如何计算这个位置就是hash算法。前面说过hashmap的[数据结构](https://so.csdn.net/so/search?q=数据结构&spm=1001.2101.3001.7020)是数组和链表的结合，所以我们当然希望这个hashmap里面的元素位置尽量的分布均匀些，尽量使得每个位置上的元素数量只有一个，那么当我们用hash算法求得这个位置的时候，马上就可以知道对应位置的元素就是我们要的，而不用再去遍历链表。  所以我们首先想到的就是把hashcode对数组长度取模运算，这样一来，元素的分布相对来说是比较均匀的。但是，“模”运算的消耗还是比较大的，能不能找一种更快速，消耗更小的方式，我们来看看JDK1.8的源码是怎么做的（被楼主修饰了一下）

```
static final int hash(Object key) {
 if (key == null){
 return 0;
 }
 int h;
 h=key.hashCode()；返回散列值也就是hashcode
 // ^ ：按位异或
 // >>>:无符号右移，忽略符号位，空位都以0补齐
 //其中n是数组的长度，即Map的数组部分初始化长度
 return (n-1)&(h ^ (h >>> 16));
}
```

 

简单来说就是

1、高16bt不变，低16bit和高16bit做了一个异或(得到的HASHCODE转化为32位的二进制，前16位和后16位低16bit和高16bit做了一个异或)

2、(n·1)&hash=->得到下标

**5、拉链法导致的链表过深问题为什么不用二叉查找树代替，而选择红黑树？为什么不一直使用红黑树？**

之所以选择红黑树是为了解决二叉查找树的缺陷，二叉查找树在特殊情况下会变成一条[线性](https://so.csdn.net/so/search?q=线性&spm=1001.2101.3001.7020)结构（这就跟原来使用链表结构一样了，造成很深的问题），遍历查找会非常慢。而红黑树在插入新数据后可能需要通过左旋，右旋、变色这些操作来保持平衡，引入红黑树就是为了查找数据快，解决链表查询深度的问题，我们知道红黑树属于平衡二叉树，但是为了保持“平衡”是需要付出代价的，但是该代价所损耗的资源要比遍历线性链表要少，所以当长度大于8的时候，会使用红黑树，如果链表长度很短的话，根本不需要引入红黑树，引入反而会慢。

**6、说说你对红黑树的见解？**

 

1、每个节点非红即黑

2、根节点总是黑色的

3、如果节点是红色的，则它的子节点必须是黑色的（反之不一定）

4、每个叶子节点都是黑色的空节点（NIL节点）

5、从根节点到叶节点或空子节点的每条路径，必须包含相同数目的黑色节点（即相同的黑色高度）

**7、解决hash 碰撞还有那些办法？**

开放定址法。

当冲突发生时，使用某种探查技术在[散列](https://so.csdn.net/so/search?q=散列&spm=1001.2101.3001.7020)表中形成一个探查(测)序列。沿此序列逐个单元地查找，直到找到给定的地址。

按照形成探查序列的方法不同，可将开放定址法区分为线性探查法、二次探查法、双重散列法等。

下面给一个线性探查法的例子

问题：已知一组关键字为(26，36，41，38，44，15，68，12，06，51)，用除余法构造散列函数，用线性探查法解决冲突构造这组关键字的散列表。

解答：为了减少冲突，通常令装填因子α由除余法因子是13的散列函数计算出的上述关键字序列的散列地址为(0，10，2，12，5，2，3，12，6，12)。

前5个关键字插入时，其相应的地址均为开放地址，故将它们直接插入T[0]，T[10)，T[2]，T[12]和T[5]中。

当插入第6个关键字15时，其散列地址2(即h(15)=15％13=2)已被关键字41(15和41互为同义词)占用。故探查h1=(2+1)％13=3，此地址开放，所以将15放入T[3]中。

当插入第7个关键字68时，其散列地址3已被非同义词15先占用，故将其插入到T[4]中。

当插入第8个关键字12时，散列地址12已被同义词38占用，故探查hl=(12+1)％13=0，而T[0]亦被26占用，再探查h2=(12+2)％13=1，此地址开放，可将12插入其中。

类似地，第9个关键字06直接插入T[6]中；而最后一个关键字51插人时，因探查的地址12，0，1，…，6均非空，故51插入T[7]中。

**8、如果HashMap的大小超过了负载因子(load factor)定义的容量，怎么办？**

默认的负载因子大小为0.75，也就是说，当一个map填满了75%的bucket时候，和其它集合类(如ArrayList等)一样，将会创建原来HashMap大小的两倍的bucket数组，来重新调整map的大小，并将原来的对象放入新的bucket数组中。这个过程叫作rehashing，因为它调用hash方法找到新的bucket位置。这个值只可能在两个地方，一个是原下标的位置，另一种是在下标为<原下标+原容量>的位置

**9、重新调整HashMap大小存在什么问题吗？**

- 当重新调整HashMap大小的时候，确实存在条件竞争，因为如果两个线程都发现HashMap需要重新调整大小了，它们会同时试着调整大小。在调整大小的过程中，存储在链表中的元素的次序会反过来，因为移动到新的bucket位置的时候，HashMap并不会将元素放在链表的尾部，而是放在头部，这是为了避免尾部遍历(tail traversing)。如果条件竞争发生了，那么就死循环了。(多线程的环境下不使用HashMap）
- 为什么多线程会导致死循环，它是怎么发生的？

HashMap的容量是有限的。当经过多次元素插入，使得HashMap达到一定饱和度时，Key映射位置发生冲突的几率会逐渐提高。这时候，HashMap需要扩展它的长度，也就是进行Resize。1.扩容：创建一个新的Entry空数组，长度是原数组的2倍。2.ReHash：遍历原Entry数组，把所有的Entry重新Hash到新数组。

（这个过程比较烧脑，暂不作流程图演示，有兴趣去看看我的另一篇博文"HashMap扩容全过程"）

达摩：哎呦，小老弟不错嘛~~意料之外呀

小鲁班：嘿嘿，优秀吧，中场休息一波，我先喝口水

达摩：不仅仅是这些哦，面试官还会问你相关的集合类对比，比如：

**10、HashTable**

- 数组 + 链表方式存储
- 默认容量： 11(质数 为宜)
- put:
- 索引计算 : （key.hashCode() & 0x7FFFFFFF）% table.length
- 若在链表中找到了，则替换旧值，若未找到则继续
- 当总元素个数超过容量*加载因子时，扩容为原来 2 倍并重新散列。
- 将新元素加到链表头部
- 对修改 Hashtable 内部共享数据的方法添加了 synchronized，保证线程安全。

**11、HashMap ，HashTable 区别**

- 默认容量不同。扩容不同
- 线程安全性，HashTable 安全
- 效率不同 HashTable 要慢因为加锁

**12、ConcurrentHashMap 原理**

1、最大特点是引入了 CAS（借助 Unsafe 来实现【native code】）

- CAS有3个操作数，内存值V，旧的预期值A，要修改的新值B。当且仅当预期值A和内存值V相同时，将内存值V修改为B，否则什么都不做。
- Unsafe 借助 CPU 指令 cmpxchg 来实现
- 使用实例：

>  1、对 sizeCtl 的控制都是用 CAS 来实现的1、sizeCtl ：默认为0，用来控制 table 的初始化和扩容操作。

- -1 代表table正在初始化
- N 表示有 -N-1 个线程正在进行扩容操作
- 如果table未初始化，表示table需要初始化的大小。
- 如果table初始化完成，表示table的容量，默认是table大小的0.75倍，居然用这个公式算0.75（n - (n >>> 2)）。

 

4、CAS 会出现的问题：ABA

 

- 对变量增加一个版本号，每次修改，版本号加 1，比较的时候比较版本号。

 

**13、我们可以使用CocurrentHashMap来代替Hashtable吗？**

- 我们知道Hashtable是synchronized的，但是ConcurrentHashMap同步性能更好，因为它仅仅根据同步级别对map的一部分进行上锁。ConcurrentHashMap当然可以代替HashTable，但是HashTable提供更强的线程安全性。它们都可以用于多线程的环境，但是当Hashtable的大小增加到一定的时候，性能会急剧下降，因为迭代时需要被锁定很长的时间。因为ConcurrentHashMap引入了分割(segmentation)，不论它变得多么大，仅仅需要锁定map的某个部分，而其它的线程不需要等到迭代完成才能访问map。简而言之，在迭代的过程中，ConcurrentHashMap仅仅锁定map的某个部分，而Hashtable则会锁定整个map。

 

 

 

##### HashMap和HashSet的区别

java最基本的两种数据结构：数组和链表的区别：

数组易于快速读取（通过for循环），不便存储（数组长度有限制）；链表易于存储，不易于快速读取。

哈希表的出现是为了解决链表访问不快速的弱点，哈希表也称散列表。

HashSet是通过HasMap来实现的，HashMap的输入参数有Key、Value两个组成，在实现HashSet的时候，保持HashMap的Value为常量，相当于在HashMap中只对Key对象进行处理。

HashMap的底层是一个数组结构，数组中的每一项对应了一个链表，这种结构称“链表散列”的数据结构，即数组和链表的结合体；也叫散列表、哈希表。

**一、HahMap存储对象的过程如下**

1、对HahMap的Key调用hashCode()方法，返回int值，即对应的hashCode；

2、把此hashCode作为哈希表的索引，查找哈希表的相应位置，若当前位置内容为NULL，则把hashMap的Key、Value包装成Entry数组，放入当前位置；

3、若当前位置内容不为空，则继续查找当前索引处存放的链表，利用equals方法，找到Key相同的Entry数组，则用当前Value去替换旧的Value；

4、若未找到与当前Key值相同的对象，则把当前位置的链表后移（Entry数组持有一个指向下一个元素的引用），把新的Entry数组放到链表表头；

**二、HashSet存储对象的过程**

往HashSet添加元素的时候，HashSet会先调用元素的hashCode方法得到元素的哈希值 ，

然后通过元素 的哈希值经过移位等运算，就可以算出该元素在哈希表中 的存储位置。

情况1： 如果算出元素存储的位置目前没有任何元素存储，那么该元素可以直接存储到该位置上。

情况2： 如果算出该元素的存储位置目前已经存在有其他的元素了，那么会调用该元素的equals方法与该位置的元素再比较一次

，如果equals返回的是true，那么该元素与这个位置上的元素就视为重复元素，不允许添加，如果equals方法返回的是false，那么该元素运行添加。

**三、HashSet和HashMap的区别**

![HashMap和HashSet的区别](http://p99.pstatp.com/large/pgc-image/e82f6ed0119c4f1a80beaee30b6363c8)



## 面试80题

所有的Java面试题已经上传github，答案也上传了一部分~

> https://github.com/whx123/JavaHome/tree/master/Java%E9%9D%A2%E8%AF%95%E9%A2%98%E9%9B%86%E7%BB%93%E5%8F%B7

## Java 基础

### 1. equals与==的区别

![图片](..\..\notes\img\offer\650.png)**==**

- 如果是基本类型，==表示判断它们值是否相等；
- 如果是引用对象，==表示判断两个对象指向的内存地址是否相同。

**equals**

- 如果是字符串，表示判断字符串内容是否相同；

- 如果是object对象的方法，比较的也是引用的内存地址值；

- 如果自己的类重写equals方法，可以自定义两个对象是否相等。

  

  **为什么重写equal方法，必须重写hashcode方法：**

  如果只重写了 equals 方法，那么默认情况下，Set 进行去重操作时，会先判断两个对象的 hashCode 是否相同，此时因为没有重写 hashCode 方法，所以会直接执行 Object 中的 hashCode 方法，而 Object 中的 hashCode 方法对比的是两个不同引用地址的对象，所以结果是 false，那么 equals 方法就不用执行了，直接返回的结果就是 false：两个对象不是相等的，于是就在 Set 集合中插入了两个相同的对象。
  ​

  但是，如果在重写 equals 方法时，也重写了 hashCode 方法，那么在执行判断时会去执行重写的 hashCode 方法，此时对比的是两个对象的所有属性的 hashCode 是否相同，于是调用 hashCode 返回的结果就是 true，再去调用 equals 方法，发现两个对象确实是相等的，于是就返回 true 了，因此 Set 集合就不会存储两个一模一样的数据了，于是整个程序的执行就正常了。

### 2. final, finally, finalize 的区别

- final 用于修饰属性,方法和类, 分别表示属性不能被重新赋值, 方法不可被覆盖, 类不可被继承.
- finally 是异常处理语句结构的一部分，一般以ty-catch-finally出现，finally代码块表示总是被执行.
- finalize 是Object类的一个方法，该方法一般由垃圾回收器来调用，当我们调用System.gc() 方法的时候，由垃圾回收器调用finalize()方法，回收垃圾，JVM并不保证此方法总被调用.

### 3. 重载和重写的区别

- 重写必须继承，重载不用。
- 重载表示同一个类中可以有多个名称相同的方法，但这些方法的参数列表各不相同（即参数个数或类型不同）
- 重写表示子类中的方法与父类中的某个方法的名称和参数完全相同啦，通过子类实例对象调用这个方法时，将调用子类中的定义方法，这相当于把父类中定义的那个完全相同的方法给覆盖了，这是面向对象编程的多态性的一种表现。
- 重写的方法修饰符大于等于父类的方法，即访问权限只能比父类的更大，不能更小，而重载和修饰符无关。
- 重写覆盖的方法中，只能比父类抛出更少的异常，或者是抛出父类抛出的异常的子异常，因为不能坑爹啊哈哈~

### 4. 两个对象的hashCode()相同，则 equals()是否也一定为 true？

两个对象equals相等，则它们的hashcode必须相等，如果两个对象的hashCode()相同，则equals()不一定为true。

**hashCode 的常规协定：**

- 在 Java 应用程序执行期间，在对同一对象多次调用 hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行 equals 比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。
- 两个对象的equals()相等，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。
- 两个对象的equals()不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，为不相等的对象生成不同整数结果可以提高哈希表的性能。

### 5. 抽象类和接口有什么区别

- 抽象类要被子类继承，接口要被子类实现。
- 抽象类可以有构造方法，接口中不能有构造方法。
- 抽象类中可以有普通成员变量，接口中没有普通成员变量，它的变量只能是公共的静态的常量
- 一个类可以实现多个接口，但是只能继承一个父类，这个父类可以是抽象类。
- 接口只能做方法声明，抽象类中可以作方法声明，也可以做方法实现。
- 抽象级别（从高到低）：接口>抽象类>实现类。
- 抽象类主要是用来抽象类别，接口主要是用来抽象方法功能。
- 抽象类的关键字是abstract，接口的关键字是interface

### 6. BIO、NIO、AIO 有什么区别？

这个答案来自互联网哈，个人觉得是最好理解的~

> - BIO：线程发起 IO 请求，不管内核是否准备好 IO 操作，从发起请求起，线程一直阻塞，直到操作完成。
> - NIO：线程发起 IO 请求，立即返回；内核在做好 IO 操作的准备之后，通过调用注册的回调函数通知线程做 IO 操作，线程开始阻塞，直到操作完成。
> - AIO：线程发起 IO 请求，立即返回；内存做好 IO 操作的准备之后，做 IO 操作，直到操作完成或者失败，通过调用注册的回调函数通知线程做 IO 操作完成或者失败。

BIO 是一个连接一个线程。,NIO 是一个请求一个线程。,AIO 是一个有效请求一个线程。

> - BIO：同步并阻塞，服务器实现模式为一个连接一个线程，即客户端有连接请求时服务器端就需要启动一个线程进行处理，如果这个连接不做任何事情会造成不必要的线程开销，当然可以通过线程池机制改善。
> - NIO：同步非阻塞，服务器实现模式为一个请求一个线程，即客户端发送的连接请求都会注册到多路复用器上，多路复用器轮询到连接有I/O请求时才启动一个线程进行处理。
> - AIO：异步非阻塞，服务器实现模式为一个有效请求一个线程，客户端的 IO 请求都是由 OS 先完成了再通知服务器应用去启动线程进行处理。

### 7. String，Stringbuffer，StringBuilder的区别

**String：**

- String类是一个不可变的类，一旦创建就不可以修改。
- String是final类，不能被继承
- String实现了equals()方法和hashCode()方法

**StringBuffer：**

- 继承自AbstractStringBuilder，是可变类。
- StringBuffer是线程安全的
- 可以通过append方法动态构造数据。

**StringBuilder：**

- 继承自AbstractStringBuilder，是可变类。
- StringBuilder是非线性安全的。
- 执行效率比StringBuffer高。

### 8. JAVA中的几种基本数据类型是什么，各自占用多少字节呢

| 基本类型 | 位数 | 字节 |
| :------- | :--- | :--- |
| int      | 32   | 4    |
| short    | 16   | 2    |
| long     | 64   | 8    |
| byte     | 8    | 1    |
| char     | 16   | 2    |
| float    | 32   | 4    |
| double   | 64   | 8    |
| boolean  | ？   | ？   |

对于boolean，官方文档未明确定义，它依赖于 JVM 厂商的具体实现。逻辑上理解是占用 1位，但是实际中会考虑计算机高效存储因素

感兴趣的小伙伴，可以去看官网

### 9. Comparator与Comparable有什么区别？

在牛客网看到这道题的答案，觉得写的最好~

> 链接：https://www.nowcoder.com/questionTerminal/99f7d1f4f8374e419a6d6924d35d9530 来源：牛客网
>
> - Comparable & Comparator 都是用来实现集合中元素的比较、排序的，只是 Comparable 是在集合内部定义的方法实现的排序，Comparator 是在集合外部实现的排序，所以，如想实现排序，就需要在集合外定义 Comparator 接口的方法或在集合内实现 Comparable 接口的方法。
> - Comparator位于包java.util下，而Comparable位于包 java.lang下。
> - Comparable 是一个对象本身就已经支持自比较所需要实现的接口（如 String、Integer 自己就可以完成比较大小操作，已经实现了Comparable接口） 自定义的类要在加入list容器中后能够排序，可以实现Comparable接口，在用Collections类的sort方法排序时，如果不指定Comparator，那么就以自然顺序排序， 这里的自然顺序就是实现Comparable接口设定的排序方式。
> - 而 Comparator 是一个专用的比较器，当这个对象不支持自比较或者自比较函数不能满足你的要求时，你可以写一个比较器来完成两个对象之间大小的比较。
> - 可以说一个是自已完成比较，一个是外部程序实现比较的差别而已。用 Comparator 是策略模式（strategy design pattern），就是不改变对象自身，而用一个策略对象（strategy object）来改变它的行为。比如：你想对整数采用绝对值大小来排序，Integer 是不符合要求的，你不需要去修改 Integer 类（实际上你也不能这么做）去改变它的排序行为，只要使用一个实现了 Comparator 接口的对象来实现控制它的排序就行了。

### 10. String类能被继承吗，为什么。

首先，String是一个final修饰的类，final修饰的类不可以被继承。

```
public final class String    implements java.io.Serializable, Comparable<String>, CharSequence {
```

**String类为什么不能被继承呢？**

有两个原因：

- 效率性，String 类作为最常用的类之一，禁止被继承和重写，可以提高效率。
- 安全性，String 类中有很多调用底层的本地方法，调用了操作系统的 API，如果方法可以重写，可能被植入恶意代码，破坏程序。

### 11. 说说Java中多态的实现原理

- 多态机制包括静态多态（编译时多态）和动态多态（运行时多态）
- 静态多态比如说重载，动态多态一般指在运行时才能确定调用哪个方法。
- 我们通常所说的多态一般指运行时多态，也就是编译时不确定究竟调用哪个具体方法，一直等到运行时才能确定。
- 多态实现方式：子类继承父类（extends）和类实现接口（implements）
- 多态核心之处就在于对父类方法的改写或对接口方法的实现，以取得在运行时不同的执行效果。
- Java 里对象方法的调用是依靠类信息里的方法表实现的，对象方法引用调用和接口方法引用调用的大致思想是一样的。当调用对象的某个方法时，JVM查找该对象类的方法表以确定该方法的直接引用地址，有了地址后才真正调用该方法。

举个例子吧，假设有个Fruit父类，一个taste味道方法，两个子类Apple和Pear，如下：

```
abstract class Fruit {    abstract String taste() ;}
class Apple extends Fruit {    @Override    String taste() {        return "酸酸的";    }}
class Pear extends Fruit {    @Override    String taste() {        return "甜甜的";    }}public class Test {    public static void main(String[] args) {        Fruit f = new Apple();        System.out.println(f.taste());    }}
```

程序运行，当调用对象Fruit f的方法taste时，JVM查找Fruit对象类的方法表以确定taste方法的直接引用地址，到底来自Apple还是Pear，确定后才真正调用对应子类的taste方法，

### 12. Java泛型和类型擦除

这个面试题，可以看我这篇文章哈~

[Java程序员必备基础：泛型解析](http://mp.weixin.qq.com/s?__biz=MzIwOTE2MzU4NA==&mid=2247483865&idx=1&sn=5b0b5f2d669f5d5ebb2c07789e01fcf6&chksm=977945f6a00ecce050167608fc85e3d53b7eb998a41e9d438044ec23e649e732642ea8585416&scene=21#wechat_redirect)

### 13. int和Integer 有什么区别，还有Integer缓存的实现

这里考察3个知识点吧：

- int 是基本数据类型，interger 是 int 的封装类
- int 默认值为 0 ，而interger 默认值为 null， Interger使用需要判空处理
- Integer的缓存机制：为了节省内存和提高性能，Integer类在内部通过使用相同的对象引用实现缓存和重用，Integer类默认在-128 ~ 127 之间，可以通过 -XX:AutoBoxCacheMax进行修改，且这种机制仅在自动装箱的时候有用，在使用构造器创建Integer对象时无用。

看个Integer的缓存的例子，加深一下印象哈：

```
Integer a = 10;Integer b = 10;
Integer c = 129;Integer d = 129;System.out.println(a == b);System.out.println(c == d);输出结果：truefalse
```

### 14. 说说反射的用途及实现原理，Java获取反射的三种方法

Java反射的原理:java类的执行需要经历以下过程，

编译:.java文件编译后生成.class字节码文件
加载：类加载器负责根据一个类的全限定名来读取此类的二进制字节流到JVM内部，并存储在运行时内存区的方法区，然后将其转换为一个与目标类型对应的java.lang.Class对象实例
连接：细分三步
  验证：格式（class文件规范） 语义（final类是否有子类） 操作
  准备：静态变量赋初值和内存空间，final修饰的内存空间直接赋原值，此处不是用户指定的初值。
  解析：符号引用转化为直接引用，分配地址
初始化:有父类先初始化父类，然后初始化自己；将static修饰代码执行一遍，如果是静态变量，则用用户指定值覆盖原有初值；如果是代码块，则执行一遍操作。

Java的反射就是利用上面第二步加载到jvm中的.class文件来进行操作的。.class文件中包含java类的所有信息，当你不知道某个类具体信息时，可以使用反射获取class，然后进行各种操作。

Java反射就是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意方法和属性；并且能改变它的属性。总结说：反射就是把java类中的各种成分映射成一个个的Java对象，并且可以进行操作。


获取class的三种方式

先定义一个实体类Person：

    Package reflex;
    public class Person {
        //私有属性
        private String name = "Tom";
     
        //公有属性
        public int age = 18;
     
        //构造方法
        public Person() {   
     
        }
     
        //私有方法
        private void say(){
            System.out.println("private say()...");
        }
     
        //公有方法
        public void work(){
            System.out.println("public work()...");
        }
    }

获取class方法 （类 对象 Class）

//1、对象调用 getClass() 方法来获取,通常应用在：比如你传过来一个 Object
//  类型的对象，而我不知道你具体是什么类，用这种方法
　　Person p1 = new Person();
　　Class c1 = p1.getClass();   

//2、类名.class 的方式得到,该方法最为安全可靠，程序性能更高
//  这说明任何一个类都有一个隐含的静态成员变量 class
　　Class c2 = Person.class;

//3、通过 Class 对象的 forName() 静态方法来获取，用的最多，
//   但可能抛出 ClassNotFoundException 异常
　　Class c3 = Class.forName("reflex.Person");

需要注意的是：一个类在 JVM 中只会有一个 Class 实例,即我们对上面获取的 c1,c2,c3进行 equals 比较，发现都是true。代码如下：

            Class class1 = Person.class;
            Person person = new Person();
            Class class2= person.getClass();
            if(class1.equals(class2)){
                System.out.println("class1.equals(class2)");
            }

Class具有的部分方法如下：     

     getName()：获得类的完整名字。
　　getFields()：获得类的public类型的属性。
　　getDeclaredFields()：获得类的所有属性。包括private 声明的和继承类
　　getMethods()：获得类的public类型的方法。
　　getDeclaredMethods()：获得类的所有方法。包括private 声明的和继承类
　　getMethod(String name, Class[] parameterTypes)：获得类的特定方法，name参数指定方法的名字，parameterTypes 参数指定方法的参数类型。
　　getConstructors()：获得类的public类型的构造方法。
　　getConstructor(Class[] parameterTypes)：获得类的特定构造方法，parameterTypes 参数指定构造方法的参数类型。
　　newInstance()：通过类的不带参数的构造方法创建这个类的一个对象。
Class能实现的功能

1判断对象属于哪个类

        Person person = new Person();
        Class class2= person.getClass();
        System.out.println("class2："+class2);
输出：class2：class reflect.Person


2获取类信息
        Class class1 = Person.class;
        Method[] methods = class1.getMethods();
        Method[] declaredMethods = class1.getDeclaredMethods();
        Field[] declaredFields = class1.getDeclaredFields();

3构建对象

        Person person = new Person();
        Class class2= person.getClass();
        Object o = class2.newInstance();
         //强转前先用instanceof判断
        if(o instanceof Person){
            ((Person) o).work();
        }

4动态执行方法
        Class class1 = Person.class;
        Method work = class1.getDeclaredMethod("work");
        Person person = new Person();
        work.invoke(person);

5动态操作属性
       Class class1 = Person.class;
        Person person = new Person();
        Field field = class1.getDeclaredField("age");
        //age默认值是18
        field.set(person,22);
        System.out.println(person.age);

6动态代理

可以参考：https://blog.csdn.net/h2604396739/article/details/83096696
jdk源码对反射的使用实例

LongAdder中，运用反射获取某属性的偏移值，方便Unsafe类直接获取某属性的值

// Unsafe mechanics Unsafe相关的初始化
private static final sun.misc.Unsafe UNSAFE;
private static final long valueOffset;
static {
    try {
        UNSAFE = sun.misc.Unsafe.getUnsafe();
        Class<?> ak = Cell.class;
        // 获取类中属性的偏移值
        valueOffset = UNSAFE.objectFieldOffset (ak.getDeclaredField("value"));
    } catch (Exception e) {
        throw new Error(e);
    }
}

final boolean cas(long cmp, long val) {
    return UNSAFE.compareAndSwapLong(this, valueOffset, cmp, val);
}
什么时候应该使用反射?

1）反射构建出无法直接访问的类:例如可以把完整的包+类名称放到properties中，java中获取，然后就可以根据这个配置获取class了,然后你就可以干很多事
Class.forName("name");
2）调用不可访问的方法
如你想让单例变得不单例，可参考：https://blog.csdn.net/h2604396739/article/details/83825148
3）简化代码
参考：https://blog.csdn.net/h2604396739/article/details/83825148

这道面试题，看我这篇文章哈：[谈谈Java反射：从入门到实践，再到原理](http://mp.weixin.qq.com/s?__biz=MzIwOTE2MzU4NA==&mid=2247483838&idx=1&sn=09555821d0d32d258627404dceb21dfb&chksm=97794591a00ecc87a5e921fd4e00dd5e8b501aebb05174d69c93d8ae1dd3cb9c31926e11737e&scene=21#wechat_redirect)

Java获取反射的**三种方法**：

- 第一种，使用 Class.forName 静态方法。
- 第二种，使用类的.class 方法
- 第三种，使用实例对象的 getClass() 方法。

### 15. 面向对象的特征

面向对象的三大特征：

- 封装
- 继承
- 多态

### 16. &和&&的区别

- 按位与, a&b 表示把a和b都转换成二进制数，再进行与的运算；
- &和&&都是逻辑运算符号，&&又叫短路运算符
- 逻辑与，a&& b ，a&b 都表示当且仅当两个操作数均为 true时，其结果才为 true，否则为false。
- 逻辑与跟短路与的差别是非常巨大的，虽然二者都要求运算符左右两端的布尔值都是true，整个表达式的值才是true。但是，&&之所以称为短路运算，是因为如果&&左边的表达式的值是false，右边的表达式会被直接短路掉，不会进行运算。

### 17. Java中IO流分为几种?

- Java中的流分为两种：一种是字节流，另一种是字符流。
- IO流分别由四个抽象类来表示（两输入两输出）:InputStream，OutputStream，Reader，Writer。
- 1.Java的字节流

  InputStream是所有字节输入流的祖先，而OutputStream是所有字节输出流的祖先。

  2.Java的字符流

  Reader是所有读取字符串输入流的祖先，而writer是所有输出字符串的祖先。
    InputStream，OutputStream,Reader,writer都是抽象类。所以不能直接new。
    字节流是最基本的，所有的InputStream和OutputStream的子类都是,主要用在处理二进制数据，它是按字节来处理的。但实际中很多的数据是文本，又提出了字符流的概念，它是按虚拟机的encode来处理，也就是要进行字符集的转化。这两个之间通过 InputStreamReader,OutputStreamWriter来关联，实际上是通过byte[]和String来关联。
    在实际开发中出现的汉字问题实际上都是在字符流和字节流之间转化不统一而造成的。在从字节流转化为字符流时，实际上就是byte[]转化为String时，public String(byte bytes[], String charsetName)
  有一个关键的参数字符集编码，通常我们都省略了，那系统就用操作系统的lang。而在字符流转化为字节流时，实际上是String转化为byte[]时，byte[] String.getBytes(String charsetName)也是一样的道理。至于java.io中还出现了许多其他的流，按主要是为了提高性能和使用方便，如BufferedInputStream,PipedInputStream等。
  。

### 18. 讲讲类的实例化顺序，比如父类静态数据，构造函数，子类静态数据，构造函数。

直接看个例子吧：

```
public class Parent {    
{        System.out.println("父类非静态代码块");    }    
static {        
System.out.println("父类静态块");    
}   
public Parent() {        
System.out.println("父类构造器");    
}
}public class Son extends Parent {    
public Son() {        
System.out.println("子类构造器");   
}   
static {       
System.out.println("子类静态代码块");    
}   
{        System.out.println("子类非静态代码块");    }
}public class Test {   
public static void main(String[] args) {        
Son son = new Son();    
}
}
```

运行结果：

```
父类静态块子类静态代码块父类非静态代码块父类构造器子类非静态代码块子类构造器
```

所以，**类实例化顺序为：**父类静态代码块/静态域->子类静态代码块/静态域 -> 父类非静态代码块 -> 父类构造器 -> 子类非静态代码块 -> 子类构造器

### 19. Java创建对象有几种方式

Java创建对象有5种方式

- 用new语句创建对象。
- 使用反射，使用Class.newInstance()创建对象/调用类对象的构造方法——Constructor
- 调用对象的clone()方法。
- 运用反序列化手段，调用java.io.ObjectInputStream对象的readObject()方法.
- 使用Unsafe

### 20. 如何将GB2312编码的字符串转换为ISO-8859-1编码的字符串呢？

```
public class Test {    
public static void main(String[] args) throws UnsupportedEncodingException {        
String str = "捡田螺的小男孩";       
String strIso = new String(str.getBytes("GB2312"), "ISO-8859-1");        
System.out.println(strIso);  
}
}
```

### 21. 守护线程是什么？用什么方法实现守护线程

- 守护线程是运行在后台的一种特殊进程。
- 它独立于控制终端并且周期性地执行某种任务或等待处理某些发生的事件。
- 在 Java 中垃圾回收线程就是特殊的守护线程。

### 22. notify()和 notifyAll()有什么区别？

- notify是唤醒一个处于该对象wait的线程，而notifyAll是唤醒所有处于该对象wait的线程。
- 但是唤醒不等于就能执行了，需要得到锁对象才能有权利继续执行，而锁只有一把，所以多个线程被唤醒时需要争取该锁。

#### 23. Java语言是如何处理异常的，关键字throws、throw、try、catch、finally怎么使用？

这道面试题，可以看我这篇文章哈：[Java程序员必备：异常的十个关键知识点](http://mp.weixin.qq.com/s?__biz=MzIwOTE2MzU4NA==&mid=2247483820&idx=1&sn=312fef59c2e24b213213f78ec697dc4a&chksm=97794583a00ecc95deeda60a5229047d7e39c77d4da89be76ce5e8e72afcfda6efb7287ccaf4&scene=21#wechat_redirect)

### 24. 谈谈Java的异常层次结构

![图片](..\..\notes\img\offer\651.png)

从前从前，有位老人，他的名字叫**Throwable**，他生了两个儿子，大儿子叫**Error**,二儿子叫**Exception**。

**Error**

表示编译时或者系统错误，如虚拟机相关的错误，OutOfMemoryError等，error是无法处理的。

**Exception**

代码异常，Java程序员关心的基类型通常是Exception。它能被程序本身可以处理，这也是它跟Error的区别。

它可以分为RuntimeException（运行时异常）和CheckedException（可检查的异常）。

**常见的RuntimeException异常：**

```
- NullPointerException 空指针异常- ArithmeticException 出现异常的运算条件时，抛出此异常- IndexOutOfBoundsException 数组索引越界异常- ClassNotFoundException 找不到类异常- IllegalArgumentException(非法参数异常)
```

**常见的 Checked Exception 异常：**

```
- IOException (操作输入流和输出流时可能出现的异常)- ClassCastException(类型转换异常类)
```

- Checked Exception就是编译器要求你必须处置的异常。
- 与之相反的是，Unchecked Exceptions，它指编译器不要求强制处置的异常，它包括Error和RuntimeException 以及他们的子类。

### 25. 静态内部类与非静态内部类有什么区别

这道面试题，可以看我这篇文章哈：Java程序员必备基础：内部类解析

- 静态内部类可以有静态成员(方法，属性)，而非静态内部类则不能有静态成员(方法，属性)。
- 静态内部类只能够访问外部类的静态成员和静态方法,而非静态内部类则可以访问外部类的所有成员(方法，属性)。
- 实例化静态内部类与非静态内部类的方式不同
- 调用内部静态类的方法或静态变量,可以通过类名直接调用

### 26. String s与new String与有什么区别

```
String str ="whx";String newStr =new String ("whx");
```

**String str ="whx"**

先在常量池中查找有没有"whx" 这个对象,如果有，就让str指向那个"whx".如果没有，在常量池中新建一个“whx”对象，并让str指向在常量池中新建的对象"whx"。

**String newStr =new String ("whx");**

是在堆中建立的对象"whx" ,在栈中创建堆中"whx" 对象的内存地址。

如图所示：

![图片](..\..\notes\img\offer\652.png)

网上这篇文章讲的挺好的：String和New String()的区别

### 27. 反射中，Class.forName和ClassLoader的区别

Class.forName和ClassLoader都可以对类进行加载。它们区别在哪里呢？**ClassLoader**负责加载 Java 类的字节代码到 Java 虚拟机中。Class.forName其实是调用了ClassLoader，如下：

![图片](..\..\notes\img\offer\640.png)这里面，forName0的第二个参数为true，表示对加载的类进行初始化化。其实还可以调用 `Class<?>forName(Stringname,booleaninitialize,ClassLoaderloader)`方法实现一样的功能，它的源码如下：

![图片](..\..\notes\img\offer\641.png)

所以，Class.forName和ClassLoader的区别，就是在类加载的时候，class.forName有参数控制是否对类进行初始化。

### 28. JDK动态代理与cglib实现的区别

- java动态代理是利用反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理。
- cglib动态代理是利用asm开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。
- JDK动态代理只能对实现了接口的类生成代理，而不能针对类
- cglib是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法。因为是继承，所以该类或方法最好不要声明成final

### 29. error和exception的区别，CheckedException，RuntimeException的区别。

![图片](..\..\notes\img\offer\642.png)

**Error:** 表示编译时或者系统错误，如虚拟机相关的错误，OutOfMemoryError等，error是无法处理的。

**Exception:** 代码异常，Java程序员关心的基类型通常是Exception。它能被程序本身可以处理，这也是它跟Error的区别。

它可以分为RuntimeException（运行时异常）和CheckedException（可检查的异常）。常见的RuntimeException异常：

```
- NullPointerException 空指针异常- ArithmeticException 出现异常的运算条件时，抛出此异常- IndexOutOfBoundsException 数组索引越界异常- ClassNotFoundException 找不到类异常- IllegalArgumentException(非法参数异常)
```

常见的 Checked Exception 异常：

```
- IOException (操作输入流和输出流时可能出现的异常)- ClassCastException(类型转换异常类)
```

有兴趣可以看我之前写得这篇文章：[Java程序员必备：异常的十个关键知识点](http://mp.weixin.qq.com/s?__biz=MzIwOTE2MzU4NA==&mid=2247483820&idx=1&sn=312fef59c2e24b213213f78ec697dc4a&chksm=97794583a00ecc95deeda60a5229047d7e39c77d4da89be76ce5e8e72afcfda6efb7287ccaf4&scene=21#wechat_redirect)

### 30. 深拷贝和浅拷贝区别

**浅拷贝**

复制了对象的引用地址，两个对象指向同一个内存地址，所以修改其中任意的值，另一个值都会随之变化。![图片](..\..\notes\img\offer\643.png)

**深拷贝**

将对象及值复制过来，两个对象修改其中任意的值另一个值不会改变

![图片](..\..\notes\img\offer\644.png)

### 31. JDK 和 JRE 有什么区别？

- JDK：Java Development Kit 的简称，Java 开发工具包，提供了 Java 的开发环境和运行环境。
- JRE：Java Runtime Environment 的简称，Java 运行环境，为 Java 的运行提供了所需环境。

### 32. String 类的常用方法都有那些呢？

- indexOf()：返回指定字符的索引。
- charAt()：返回指定索引处的字符。
- replace()：字符串替换。
- trim()：去除字符串两端空白。
- split()：分割字符串，返回一个分割后的字符串数组。
- getBytes()：返回字符串的 byte 类型数组。
- length()：返回字符串长度。
- toLowerCase()：将字符串转成小写字母。
- toUpperCase()：将字符串转成大写字符。
- substring()：截取字符串。
- equals()：字符串比较。

### 33. 谈谈自定义注解的场景及实现

- 之前我这边有这么一个业务场景，用Redis控制接口调用频率，有使用过自定义注解。
- 通过 AOP（动态代理机制）给方法添加切面，通过反射来获取方法包含的注解，如果包含自定义关键字注解，就通过Redis进行校验拦截请求。

有关于注解，建议大家看一下java编程思想的注解篇章哈~

### 34. 说说你熟悉的设计模式有哪些？

设计模式分为三大类：

- 创建型模式：工厂方法模式、抽象工厂模式、单例模式、建造者模式、原型模式（5种）
- 结构型模式：适配器模式、装饰者模式、代理模式、外观模式、桥接模式、组合模式、享元模式。（7种）
- 行为型模式：策略模式、模板方法模式、观察者模式、迭代子模式、责任链模式、命令模式、备忘录模式、状态模式、访问者模式、中介者模式、解释器模式。（11种）

最好平时积累一下，单例模式（7种实现方式），工厂模式，模板方法设计模式，策略模式，装饰者模式、代理模式这几种怎么写吧~

### 35. 抽象工厂和工厂方法模式的区别？

![图片](https://mmbiz.qpic.cn/mmbiz_png/sMmr4XOCBzFqTyibxYauPWFYQ6d1hDR8XdAASqmPvbm9BLcKcWWCstnxL8AJ6CialdbmZOiclzIaxjDLJJdWZwGiaA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)可以看一下这篇文章介绍：抽象工厂模式-与-工厂方法模式区别

### 36. 什么是值传递和引用传递？

- 值传递是对基本型变量而言的,传递的是该变量的一个副本，改变副本不影响原变量.
- 引用传递一般是对于对象型变量而言的,传递的是该对象地址的一个副本, 并不是原对象本身 。所以对引用对象进行操作会同时改变原对象.

### 37. 可以在static环境中访问非static变量吗？

> static变量在Java中是属于类的，它在所有的实例中的值是一样的。当类被Java虚拟机载入的时候，会对static变量进行初始化。因为静态的成员属于类，随着类的加载而加载到静态方法区内存，当类加载时，此时不一定有实例创建，没有实例，就不可以访问非静态的成员。类的加载先于实例的创建，因此静态环境中，不可以访问非静态！

### 38. Java支持多继承么,为什么？

不支持多继承，原因:

- 安全性的考虑，如果子类继承的多个父类里面有相同的方法或者属性，子类将不知道具体要继承哪个。
- Java提供了接口和内部类以达到实现多继承功能，弥补单继承的缺陷。

### 39. 用最有效率的方法计算2乘以8？

```
2 << 3
```

- 将一个数左移n位，就相当于这个数乘以了2的n次方。
- 那么，一个数乘以8只要将其左移3位即可。
- 而cpu直接支持位移运算，且效率最高。

### 40. 构造器是否可被重写？

构造器是不能被继承的，因为每个类的类名都不相同，而构造器名称与类名相同，所以谈不上继承。又由于构造器不能被继承，所以相应的就不能被重写了。

### 41. char型变量中能不能存贮一个中文汉字，为什么？

在Java中，char类型占2个字节，而且Java默认采用Unicode编码，一个Unicode码是16位，所以一个Unicode码占两个字节，Java中无论汉子还是英文字母都是用Unicode编码来表示的。所以，在Java中，char类型变量可以存储一个中文汉字。

```
char ch = '啦';System.out.println("char:" + ch);
```

### 42. 如何实现对象克隆？

- 实现 Cloneable 接口，重写 clone() 方法。
- Object 的 clone() 方法是浅拷贝，即如果类中属性有自定义引用类型，只拷贝引用，不拷贝引用指向的对象。
- 对象的属性的Class 也实现 Cloneable 接口，在克隆对象时也手动克隆属性，完成深拷贝
- 结合序列化(JDK java.io.Serializable 接口、JSON格式、XML格式等)，完成深拷贝

### 43. object中定义了哪些方法？

- getClass(); 获取类结构信息
- hashCode() 获取哈希码
- equals(Object) 默认比较对象的地址值是否相等，子类可以重写比较规则
- clone() 用于对象克隆
- toString() 把对象转变成字符串
- notify() 多线程中唤醒功能
- notifyAll() 多线程中唤醒所有等待线程的功能
- wait() 让持有对象锁的线程进入等待
- wait(long timeout) 让持有对象锁的线程进入等待，设置超时毫秒数时间
- wait(long timeout, int nanos) 让持有对象锁的线程进入等待，设置超时纳秒数时间
- finalize() 垃圾回收前执行的方法

### 44. hashCode的作用是什么？

> - hashCode的存在主要是用于查找的快捷性，如Hashtable，HashMap等，hashCode是用来在散列存储结构中确定对象的存储地址的；
> - 如果两个对象相同，就是适用于equals(java.lang.Object) 方法，那么这两个对象的hashCode一定要相同；
> - 如果对象的equals方法被重写，那么对象的hashCode也尽量重写，并且产生hashCode使用的对象，一定要和equals方法中使用的一致，否则就会违反上面提到的第2点；
> - 两个对象的hashCode相同，并不一定表示两个对象就相同，也就是不一定适用于equals(java.lang.Object) 方法，只能够说明这两个对象在散列存储结构中.

### 45. for-each与常规for循环的效率对比

关于这个问题,《Effective Java》给我们做的解答：

> for-each能够让代码更加清晰，并且减少了出错的机会。下面的惯用代码适用于集合与数组类型：
>
> ```
> for (Element e : elements) {     doSomething(e); }
> ```
>
> 使用for-each循环与常规的for循环相比，并不存在性能损失，即使对数组进行迭代也是如此。实际上，在有些场合下它还能带来微小的性能提升，因为它只计算一次数组索引的上限。

### 46. 写出几种单例模式实现，懒汉模式和饿汉模式区别

7种：

- 第一种（懒汉，线程不安全）
- 第二种（懒汉，线程安全）
- 第三种（饿汉）
- 第四种（饿汉，变种）
- 第五种（静态内部类）
- 第六种（枚举）：
- 第七种（双重校验锁）

可以看这篇文章：单例模式的七种写法

### 47. 请列出 5 个运行时异常。

```
- NullPointerException 空指针异常- ArithmeticException 出现异常的运算条件时，抛出此异常- IndexOutOfBoundsException 数组索引越界异常- ClassNotFoundException 找不到类异常- IllegalArgumentException(非法参数异常)
```

### 48. 2个不相等的对象有可能具有相同的 hashcode吗？

有可能哈~

**hashCode 的常规协定：**

- 在 Java 应用程序执行期间，在对同一对象多次调用 hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行 equals 比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。
- 两个对象的equals()相等，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。
- 两个对象的equals()不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，为不相等的对象生成不同整数结果可以提高哈希表的性能。

### 49. 访问修饰符public,private,protected,以及default的区别？

![图片](..\..\notes\img\offer\645.png)

### 50. 谈谈final在java中的作用？

- final 修饰的类叫最终类，该类不能被继承。
- final 修饰的方法不能被重写。
- final 修饰的变量叫常量，常量必须初始化，初始化之后值就不能被修改。

### 51. java中的Math.round(-1.5) 等于多少呢？

![图片](..\..\notes\img\offer\646.png)

JDK 中的 java.lang.Math 类:

- round() ：返回四舍五入，负 .5 小数返回较大整数，如 -1.5 返回 -1。
- ceil() ：返回小数所在两整数间的较大值，如 -1.5 返回 -1.0。
- floor() ：返回小数所在两整数间的较小值，如 -1.5 返回 -2.0。

### 52. String属于基础的数据类型吗？

String 不属于基础类型，基础类型有 8 种：byte、boolean、char、short、int、float、long、double，而 String 属于对象。

### 53. 如何将字符串反转呢？

- 使用 StringBuilder 或 StringBuffer 的 reverse 方法，本质都调用了它们的父类 AbstractStringBuilder 的 reverse 方法实现。（JDK1.8）
- 使用chatAt函数，倒过来输出；

![图片](..\..\notes\img\offer\647.png)

### 54. 描述动态代理的几种实现方式，它们分别有什么优缺点

- JDK动态代理
- CGLIB动态代理
- JDK原声动态代理时java原声支持的、不需要任何外部依赖、但是它只能基于接口进行代理
- CGLIB通过继承的方式进行代理、无论目标对象没有没实现接口都可以代理，但是无法处理final的情况

### 55. 在自己的代码中，如果创建一个java.lang.String类，这个类是否可以被类加载器加载？为什么。

不可以。因为JDK处于安全性的考虑，基于双亲委派模型，优先加载JDK的String类，如果java.lang.String已经加载，便不会再次被加载。

### 56. 谈谈你对java.lang.Object对象中hashCode和equals方法的理解。在什么场景下需要重新实现这两个方法。

> - 在我们的业务系统中判断对象时有时候需要的不是一种严格意义上的相等，而是一种业务上的对象相等。在这种情况下，原生的equals方法就不能满足我们的需求了，所以这个时候我们需要重写equals方法，来满足我们的业务系统上的需求。
> - 那么为什么在重写equals方法的时候需要重写hashCode方法呢？如果只重写了equals方法而没有重写hashCode方法的话，则会违反约定的第二条：相等的对象必须具有相等的散列码.所以hashCode和equals方法都需要重写

### 57. 在jdk1.5中，引入了泛型，泛型的存在是用来解决什么问题。

```
Java 泛型（generics）是 JDK 5 中引入的一个新特性，其本质是参数化类型，解决不确定具体对象类型的问题。
```

这个面试题，可以看我这篇文章哈~Java程序员必备基础：泛型解析

### 58. 什么是序列化，怎么序列化，反序列呢？

- 序列化：把Java对象转换为字节序列的过程
- 反序列：把字节序列恢复为Java对象的过程 ![图片](..\..\notes\img\offer\648.png)

可以看我这篇文章哈~ [Java程序员必备：序列化全方位解析](http://mp.weixin.qq.com/s?__biz=MzIwOTE2MzU4NA==&mid=2247484021&idx=1&sn=d7760254bd36f3a39dc8705ad40b469f&chksm=9779465aa00ecf4cfb5513a0ac41a31f318df2565b3e4ac739e62abfedad21d5a1e483f63f63&scene=21#wechat_redirect)

### 59. java8的新特性。

- Lambda 表达式：Lambda允许把函数作为一个方法的参数
- Stream API ：新添加的Stream API（java.util.stream） 把真正的函数式编程风格引入到Java中
- 方法引用：方法引用提供了非常有用的语法，可以直接引用已有Java类或对象（实例）的方法或构造器。
- 默认方法：默认方法就是一个在接口里面有了一个实现的方法。
- Optional 类 ：Optional 类已经成为 Java 8 类库的一部分，用来解决空指针异常。
- Date Time API ：加强对日期与时间的处理。

### 60. 匿名内部类是什么？如何访问在其外面定义的变量呢？

匿名内部类就是没有名字的内部类，日常开发中使用的比较多。

```
public class Outer {
    private void test(final int i) {        new Service() {            public void method() {                for (int j = 0; j < i; j++) {                    System.out.println("匿名内部类" );                }            }        }.method();    } } //匿名内部类必须继承或实现一个已有的接口 interface Service{    void method();}
```

匿名内部类还有以下特点：

- 匿名内部类没有名字的
- 匿名内部类必须继承一个抽象类或者实现一个接口。
- 匿名内部类不能定义任何静态成员和静态方法。
- 当所在的方法的形参需要被匿名内部类使用时，必须声明为 final。
- 匿名内部类不能是抽象的，它必须要实现继承的类或者实现的接口的所有抽象方法。
- 匿名内部类不能访问外部类方法中的局部变量，除非该变量被声明为final类型

可以看我这篇文章哈~[Java程序员必备基础：内部类解析](http://mp.weixin.qq.com/s?__biz=MzIwOTE2MzU4NA==&mid=2247483860&idx=1&sn=1f605d8eba138c25b663f63ff99656e1&chksm=977945fba00ecced3fbde14fabe5bb5feb33fa875b64483f06082a27356175e0763625b93846&scene=21#wechat_redirect)

### 61. break和continue有什么区别？

- break可以使流程跳出switch语句体，也可以在循环结构终止本层循环体，从而提前结束本层循环。
- continue的作用是跳过本次循环体中余下尚未执行的语句，立即进行下一次的循环条件判定，可以理解为仅结束本次循环

### 62. String s = "Hello";s = s + " world!";这两行代码执行后，原始的 String 对象中的内容是否会改变？

没有。因为 String 被设计成不可变(immutable)类，所以它的所有对象都是不可变对象。

### 63. String s="a"+"b"+"c"+"d";创建了几个对象？

1个而已啦。

> Java 编译器对字符串常量直接相加的表达式进行优化，不等到运行期去进行加法运算，在编译时就去掉了加号，直接将其编译成一个这些常量相连的结果。所以 "a"+"b"+"c"+"d" 相当于直接定义一个 "abcd" 的字符串。

### 64. try-catch-finally-return执行顺序

try-catch-finally-return 执行描述:

- 如果不发生异常，不会执行catch部分。
- 不管有没有发生异常，finally都会执行到。
- 即使try和catch中有return时，finally仍然会执行
- finally是在return后面的表达式运算完后再执行的。（此时并没有返回运算后的值，而是先把要返回的值保存起来，若finally中无return，则不管finally中的代码怎么样，返回的值都不会改变，仍然是之前保存的值），该情况下函数返回值是在finally执行前确定的)
- finally部分就不要return了，要不然，就回不去try或者catch的return了。

看一个例子

```go
 public static void main(String[] args) throws IOException {        System.out.println("result：" + test());    }
    private static int test() {        int temp = 1;        try {            System.out.println("start execute try,temp is:"+temp);            return ++temp;        } catch (Exception e) {            System.out.println("start execute catch temp is: "+temp);            return ++temp;        } finally {            System.out.println("start execute finally,temp is:" + temp);            ++temp;        }    }
```

运行结果：

```
start execute try,temp is:1start execute finally,temp is:2result:2
```

分析：

- 先执行try部分，输出日志，执行 `++temp`表达式，temp变为2,这个值被保存起来。
- 因为没有发生异常，所以catch代码块跳过。
- 执行finally代码块，输出日志，执行 `++temp`表达式.
- 返回try部分保存的值2.

### 65. Java 7新的 try-with-resources语句，平时有使用吗

try-with-resources，是Java7提供的一个新功能，它用于自动资源管理。

- 资源是指在程序用完了之后必须要关闭的对象。
- try-with-resources保证了每个声明了的资源在语句结束的时候会被关闭
- 什么样的对象才能当做资源使用呢？只要实现了java.lang.AutoCloseable接口或者java.io.Closeable接口的对象，都OK。

在 `try-with-resources`出现之前

```
try{    //open resources like File, Database connection, Sockets etc} catch (FileNotFoundException e) {    // Exception handling like FileNotFoundException, IOException etc}finally{    // close resources}
```

Java7， `try-with-resources`出现之后，使用资源实现

```
try(// open resources here){    // use resources} catch (FileNotFoundException e) {    // exception handling}// resources are closed as soon as try-catch block is executed.
```

Java7使用资源demo

```
public class Java7TryResourceTest {    public static void main(String[] args) {        try (BufferedReader br = new BufferedReader(new FileReader(                "C:/jaywei.txt"))) {            System.out.println(br.readLine());        } catch (IOException e) {            e.printStackTrace();        }    }}
```

使用了 `try-with-resources`的好处

- 代码更加优雅，行数更少。
- 资源自动管理，不用担心内存泄漏问题。

### 66. 简述一下面向对象的”六原则一法则”。

- 单一职责原则:一个类只做它该做的事情。
- 开闭原则：软件实体应当对扩展开放，对修改关闭。
- 依赖倒转原则：面向接口编程。
- 接口隔离原则：接口要小而专，绝不能大而全。
- 合成聚合复用原则：优先使用聚合或合成关系复用代码。
- 迪米特法则：迪米特法则又叫最少知识原则，一个对象应当对其他对象有尽可能少的了解。

### 67. switch是否能作用在byte 上，是否能作用在long 上，是否能作用在String上？

- switch可作用于char byte short int
- switch可作用于char byte short int对应的包装类
- switch不可作用于long double float boolean，以及他们的包装类

### 68. 数组有没有length()方法？String有没有length()方法？

- 数组没有length()方法，而是length；
- String有length()方法

### 69. 是否可以从一个静态（static）方法内部发出对非静态（non-static）方法的调用？

不可以。

- 非static方法是要与对象实例息息相关的，必须在创建一个对象后，才可以在该对象上进行非static方法调用，而static方法跟类相关的，不需要创建对象，可以由类直接调用。
- 当一个static方法被调用时，可能还没有创建任何实例对象，如果从一个static方法中发出对非static方法的调用，那个非static方法是关联到哪个对象上的呢？这个逻辑是不成立的
- 因此，一个static方法内部不可以发出对非static方法的调用。

### 70. String s = new String("jay");创建了几个字符串对象？

一个或两个

> - 第一次调用 new String("jay"); 时，会在堆内存中创建一个字符串对象，同时在字符串常量池中创建一个对象 "jay"
> - 第二次调用 new String("jay"); 时，只会在堆内存中创建一个字符串对象，指向之前在字符串常量池中创建的 "jay"

可以看老王这篇文章，很清晰~[别再问我 new 字符串创建了几个对象了！我来证明给你看！](https://mp.weixin.qq.com/s?__biz=MzIwOTE2MzU4NA==&mid=2247484162&idx=1&sn=ff743ff5c975a373036749490042a868&chksm=9779472da00ece3b6186d8ce8e79cd1503311327ad4b38f569ccd29a9d15dec065ff201480d2&token=815554431&lang=zh_CN&scene=21#wechat_redirect)

### 71. this和super关键字的作用

this：

- 对象内部指代自身的引用
- 解决成员变量和局部变量同名问题
- 可以调用成员变量，不能调用局部变量
- 可以调用成员方法
- 在普通方法中可以省略 this
- 在静态方法当中不允许出现 this 关键字

super：

- 调用父类 的成员或者方法
- 调用父类的构造函数

### 72. 我们能将int强制转换为 byte类型的变量吗？如果该值大于byte 类型的范围，将会出现什么现象？

可以，我们可以做强制转换，但是在Java中，int是32位，byte是8位，如果强制做转化，int类型的高24位将会被丢弃。

```
public class Test {    public static void main(String[] args)  {        int a =129;        byte b = (byte) a;        System.out.println(b);        int c =10;        byte d = (byte) c;        System.out.println(d);
    }}输出：-12710
```

### 73. float f=3.4;正确吗？

不正确，精度不准确,应该用强制类型转换![图片](https://mmbiz.qpic.cn/mmbiz_png/sMmr4XOCBzFqTyibxYauPWFYQ6d1hDR8Xd2SzGhIpJRmibTApv2UPKibGnofy2mrva5hEg4zh3fsPfyAtURWUzFqQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

### 74. 接口可否继承接口？抽象类是否可实现接口？抽象类是否可继承实体类？

都可以的

### 75. Reader和InputStream区别？

- InputStream是表示字节输入流的所有类的超类
- Reader是用于读取字符流的抽象类

### 76. 列举出JAVA中6个比较常用的包

- java.lang;
- java.util;
- java.io;
- java.sql;
- java.awt;
- java.net;

### 77. JDK 7有哪些新特性

- Try-with-resource语句
- NIO2 文件处理Files
- switch可以支持字符串判断条件
- 泛型推导
- 多异常统一处理

### 78. 同步和异步有什么区别？

- 同步，可以理解为在执行完一个函数或方法之后，一直等待系统返回值或消息，这时程序是出于阻塞的，只有接收到返回的值或消息后才往下执行其他的命令。
- 异步，执行完函数或方法后，不必阻塞性地等待返回值或消息，只需要向系统委托一个异步过程，那么当系统接收到返回值或消息时，系统会自动触发委托的异步过程，从而完成一个完整的流程。
- 同步，就是实时处理（如打电话）
- 异步，就是分时处理（如收发短信）

### 79. 实际开发中，Java一般使用什么数据类型来代表价格？

java中使用BigDecimal来表示价格是比较好的。

可以看这篇文章，写得非常好[老大说：谁要再用double定义商品金额，就自己收拾东西走](https://mp.weixin.qq.com/s?__biz=MzU4ODI1MjA3NQ==&mid=2247485507&idx=1&sn=5c65f4a62ceab57a23bfe810a161035b&chksm=fddede87caa957918b6c131440c2c7aba5b21d72b296588776c6b85b6f8934bb4b6bfda97858&token=815554431&lang=zh_CN&scene=21#wechat_redirect)

### 80. 64 位 JVM 中，int 的长度是多数？

int数据类型占4个字节 32位，跟JVM位数没关系的





### ==81.springcloud==

### 1.1 什么是微服务？

微服务(Microservice Architecture) 是近几年流行的一种架构思想，关于它的概念很难一言以蔽之。

究竟什么是微服务呢？我们在此引用ThoughtWorks 公司的首席科学家 Martin Fowler 于2014年提出的一段话：

原文：https://martinfowler.com/articles/microservices.html

汉化：https://www.cnblogs.com/liuning8023/p/4493156.html

- 就目前而言，对于微服务，业界并没有一个统一的，标准的定义。
- 但通常而言，微服务架构是一种架构模式，或者说是一种架构风格，**它体长将单一的应用程序划分成一组小的服务**，每个服务运行在其独立的自己的进程内，服务之间互相协调，互相配置，为用户提供最终价值，服务之间采用轻量级的通信机制(**HTTP**)互相沟通，每个服务都围绕着具体的业务进行构建，并且能狗被独立的部署到生产环境中，另外，应尽量避免统一的，集中式的服务管理机制，对具体的一个服务而言，应该根据业务上下文，选择合适的语言，工具(**Maven**)对其进行构建，可以有一个非常轻量级的集中式管理来协调这些服务，可以使用不同的语言来编写服务，也可以使用不同的数据存储。

> 再来从技术维度角度理解下：

- 微服务化的核心就是将传统的一站式应用，根据业务拆分成一个一个的服务，彻底地去耦合，每一个微服务提供单个业务功能的服务，一个服务做一件事情，从技术角度看就是一种小而独立的处理过程，类似进程的概念，能够自行单独启动或销毁，拥有自己独立的数据库。



###  1.2 微服务之间是如何独立通讯的？

```
同步：RPC ,REST等。
  异步：消息队列，要考虑消息的可靠传输、高性能，以及编程模型的变化等。
```

1.远程过程调用（Remote Procedure Invocation）：
       也就是我们常说的服务的注册与发现
       直接通过远程过程调用来访问别的service。
       优点：
       简单，常见,因为没有中间件代理，系统更简单
       缺点：
       只支持请求/响应的模式，不支持别的，比如通知、请求/异步响应、发布/订阅、发布/异步响应
       降低了可用性，因为客户端和服务端在请求过程中必须都是可用的
2.消息：
       使用异步消息来做服务间通信。服务间通过消息管道来交换消息，从而通信。
       优点:
       把客户端和服务端解耦，更松耦合
       提高可用性，因为消息中间件缓存了消息，直到消费者可以消费
       支持很多通信机制比如通知、请求/异步响应、发布/订阅、发布/异步响应
       缺点:
       消息中间件有额外的复杂

###  1.3 SpringCloud 和 Dubbo有那些区别？

**最大区别：Spring Cloud 抛弃了Dubbo的RPC通信，采用的是基于HTTP的REST方式**

严格来说，这两种方式各有优劣。虽然从一定程度上来说，后者牺牲了服务调用的性能，但也避免了上面提到的原生RPC带来的问题。而且REST相比RPC更为灵活，服务提供方和调用方的依赖只依靠一纸契约，不存在代码级别的强依赖，这个优点在当下强调快速演化的微服务环境下，显得更加合适。

**品牌机和组装机的区别**

**社区支持与更新力度的区别**

**总结：**二者解决的问题域不一样：Dubbo的定位是一款RPC框架，而SpringCloud的目标是微服务架构下的一站式解决方案。

###  1.4 SpringBoot 和 SpringCloud，请谈谈你对他们的理解

- SpringBoot专注于开苏方便的开发单个个体微服务；
- SpringCloud是关注全局的微服务协调整理治理框架，它将SpringBoot开发的一个个单体微服务，整合并管理起来，为各个微服务之间提供：配置管理、服务发现、断路器、路由、为代理、事件总栈、全局锁、决策竞选、分布式会话等等集成服务；
- SpringBoot可以离开SpringCloud独立使用，开发项目，但SpringCloud离不开SpringBoot，属于依赖关系；
- SpringBoot专注于快速、方便的开发单个个体微服务，SpringCloud关注全局的服务治理框架；

###  1.5 什么是服务熔断？什么是服务降级？

**熔断机制是赌赢雪崩效应的一种微服务链路保护机制**。

当扇出链路的某个微服务不可用或者响应时间太长时，会进行服务的降级，**进而熔断该节点微服务的调用，快速返回错误的响应信息**。检测到该节点微服务调用响应正常后恢复调用链路。在SpringCloud框架里熔断机制通过Hystrix实现。Hystrix会监控微服务间调用的状况，当失败的调用到一定阀值缺省是**5秒内20次调用失败，就会启动熔断机制**。熔断机制的注解是：`@HystrixCommand`。

服务熔断解决如下问题：

- 当所依赖的对象不稳定时，能够起到快速失败的目的；
- 快速失败后，能够根据一定的算法动态试探所依赖对象是否恢复。

服务降级是指 当服务器压力剧增的情况下，根据实际业务情况及流量，对一些服务和页面有策略的不处理，或换种简单的方式处理，从而释放服务器资源以保证核心业务正常运作或高效运作。说白了，**就是尽可能的把系统资源让给优先级高的服务**。

资源有限，而请求是无限的。如果在并发高峰期，不做服务降级处理，一方面肯定会影响整体服务的性能，严重的话可能会导致宕机某些重要的服务不可用。所以，一般在高峰期，为了保证核心功能服务的可用性，都要对某些服务降级处理。比如当双11活动时，把交易无关的服务统统降级，如查看蚂蚁深林，查看历史订单等等。

服务降级主要用于什么场景呢？当整个微服务架构整体的负载超出了预设的上限阈值或即将到来的流量预计将会超过预设的阈值时，为了保证重要或基本的服务能正常运行，可以将一些 不重要 或 不紧急 的服务或任务进行服务的 延迟使用 或 暂停使用。

降级的方式可以根据业务来，可以延迟服务，比如延迟给用户增加积分，只是放到一个缓存中，等服务平稳之后再执行 ；或者在粒度范围内关闭服务，比如关闭相关文章的推荐。

###  1.6 微服务的优缺点分别是什么？说下你在项目开发中遇到的坑

> 优点

- 单一职责原则；
- 每个服务足够内聚，足够小，代码容易理解，这样能聚焦一个指定的业务功能或业务需求；
- 开发简单，开发效率高，一个服务可能就是专一的只干一件事；
- 微服务能够被小团队单独开发，这个团队只需2-5个开发人员组成；
- 微服务是松耦合的，是有功能意义的服务，无论是在开发阶段或部署阶段都是独立的；
- 微服务能使用不同的语言开发；
- 易于和第三方集成，微服务允许容易且灵活的方式集成自动部署，通过持续集成工具，如jenkins，Hudson，bamboo；
- 微服务易于被一个开发人员理解，修改和维护，这样小团队能够更关注自己的工作成果，无需通过合作才能体现价值；
- 微服务允许利用和融合最新技术；
- **微服务只是业务逻辑的代码，不会和HTML，CSS，或其他的界面混合;**
- **每个微服务都有自己的存储能力，可以有自己的数据库，也可以有统一的数据库；**

> 缺点

- 开发人员要处理分布式系统的复杂性；
- 多服务运维难度，随着服务的增加，运维的压力也在增大；
- 系统部署依赖问题；
- 服务间通信成本问题；
- 数据一致性问题；
- 系统集成测试问题；
- 性能和监控问题；

###  1.7 你所知道的微服务技术栈有哪些？列举一二

 维度(SpringCloud)
     服务开发
       SpringBoot
       Spring
       SpringMVC
     服务配置与管理
       Netfilx公司的Archaiusm,阿里的Diamond
     服务注册与发现
       Eureka,ZooKeeper
     服务调用
       Rest,RPC,gRPC
     服务熔断器
       Hystrix
     服务负载均衡
       Ribbon,Nginx
     服务接口调用
       Feign
     消息队列
       Kafka,RabbitMq,ActiveMq
     服务配置中心管理
       SpringCloudConfing
     服务路由(API网关)
       Zuul
     事件消息总线
       SpringCloud Bus

###  1.8 Eureka和Zookeeper都可以提供服务注册与发现的功能，请说说两者的区别



zookeeper与dubbo区别

### zookeeper与eurake区别

著名的CAP理论指出，一个分布式系统不可能同时满足C (一致性) 、A (可用性) 、P (容错性)，由于分区容错性P再分布式系统中是必须要保证的，因此我们只能再A和C之间进行权衡。

- Zookeeper 保证的是 CP —> 满足一致性，分区容错的系统，通常性能不是特别高
- Eureka 保证的是 AP —> 满足可用性，分区容错的系统，通常可能对一致性要求低一些

**Zookeeper保证的是CP**

当向注册中心查询服务列表时，我们可以容忍注册中心返回的是几分钟以前的注册信息，但不能接收服务直接down掉不可用。也就是说，**服务注册功能对可用性的要求要高于一致性**。但zookeeper会出现这样一种情况，当master节点因为网络故障与其他节点失去联系时，剩余节点会重新进行leader选举。问题在于，选举leader的时间太长，30-120s，且选举期间整个zookeeper集群是不可用的，这就导致在选举期间注册服务瘫痪。在云部署的环境下，因为网络问题使得zookeeper集群失去master节点是较大概率发生的事件，虽然服务最终能够恢复，但是，漫长的选举时间导致注册长期不可用，是不可容忍的。

**Eureka保证的是AP**

Eureka看明白了这一点，因此在设计时就优先保证可用性。**Eureka各个节点都是平等的**，几个节点挂掉不会影响正常节点的工作，剩余的节点依然可以提供注册和查询服务。而Eureka的客户端在向某个Eureka注册时，如果发现连接失败，则会自动切换至其他节点，只要有一台Eureka还在，就能保住注册服务的可用性，只不过查到的信息可能不是最新的，除此之外，Eureka还有之中自我保护机制，如果在15分钟内超过85%的节点都没有正常的心跳，那么Eureka就认为客户端与注册中心出现了网络故障，此时会出现以下几种情况：

- Eureka不在从注册列表中移除因为长时间没收到心跳而应该过期的服务
- Eureka仍然能够接受新服务的注册和查询请求，但是不会被同步到其他节点上 (即保证当前节点依然可用)
- 当网络稳定时，当前实例新的注册信息会被同步到其他节点中

因此，Eureka可以很好的应对因网络故障导致部分节点失去联系的情况，而不会像zookeeper那样使整个注册服务瘫痪

springcloud与dubbo区别

http与rpc区别



### 1.SpringBoot和SpringCloud的区别？

1. SpringBoot专注于快速方便的开发单个个体微服务
2. SpringCloud是关注全局的微服务协调整理治理框架，它将SpringBoot开发的一个个单体微服务整合并管理起来，为各个微服务之间提供，配置管理、服务发现、断路器、路由、微代理、事件总线、全局锁、决策竞选、分布式会话等等集成服务,SpringBoot可以离开SpringCloud独立使用开发项目， 但是SpringCloud离不开SpringBoot  ，属于依赖的关系,SpringBoot专注于快速、方便的开发单个微服务个体，SpringCloud关注全局的服务治理框架。

### 2.使用 Spring Boot 开发分布式微服务时，我们面临以下问题

1. 与分布式系统相关的复杂性-这种开销包括网络问题，延迟开销，带宽问题，安全问题
2. 服务发现-服务发现工具管理群集中的流程和服务如何查找和互相交谈。它涉及一个服务目录，在该目录中注册服务，然后能够查找并连接到该目录中的服务
3. 冗余-分布式系统中的冗余问题。
4. 负载平衡 —负载平衡改善跨多个计算资源的工作负荷，诸如计算机，计算机集群，网络链路，中央处理单元，或磁盘驱动器的分布。
5. 性能-问题 由于各种运营开销导致的性能问题。
6. 部署复杂性-Devops 技能的要求。

### 3.服务注册和发现是什么意思？Spring Cloud 如何实现？

1. 当我们开始一个项目时，我们通常在属性文件中进行所有的配置。随着越来越多的服务开发和部署，添加和修改这些属性变得更加复杂.有些服务可能会下降，而某些位置可能会发生变化。手动更改属性可能会产生问题。

- 简单的理解就是：通过服务注册机制将启动服务的信息上传至服务注册表，服务发现机制通过服务注册表实时获取可用服务的信息
- 服务注册有自注册和第三方注册
  - 自注册：顾名思义，就是服务提供方在启动服务时自己把提供服务的IP和端口发送到注册中心，并通过心跳方式维持健康状态；服务下线时，自己把相应的数据删除。典型的像使用eureka客户端发布微服务
  - 第三方注册是指，存在一个第三方的系统负责在服务启动或停止时向注册中心增加或删除服务数据。典型的用法是devops系统或容器调度系统主动调注册中心接口注册服务
- 服务发现的意思：真正发起服务调用前，调用方需要从注册中心拿到相应服务可用的IP和端口列表，即服务发现

1. Eureka 服务注册和发现可以在这种情况下提供帮助。由于所有服务都在 Eureka 服务器上注册并通过调用 Eureka 服务器完成查找，因此无需处理服务地点的任何更改和处理

### 4.springcloud有哪些组件？springcloud核心组件及其作用? ======+1

1. springcloud组件：

- Eureka : 服务注册与发现
- Ribbon : 负载均衡
- Hystrix : 服务保护与熔断机制
- Feign : 声明式接口
- Zuul/Gateway : 网关
- Integration/Stream : MQ接口绑定
- Bus : 事件监听
- Sleuth+Zipkin : 分布式链路追踪
- Config : 配置中心
- 可替换组件：注册中心、配置中心：Zookeeper、Consul

1. springcloud核心组件及其作用：

- Eureka：这个服务启动时,Eureka会将服务注册到EurekaService,并且EurakeClient还可以返回过来从EurekaService拉去注册表,从而知道服务在哪里
- Feign: 基于fegin的动态代理机制,根据注解和选择机器,拼接Url地址,发起请求
- Ribbon: 服务间发起请求的时候,基于Ribbon服务做到负载均衡,从一个服务的对台机器中选择一台
- Hystrix: 发起的请求是通过Hystrix的线程池来走,不同的服走不同的线程池,实现了不同的服务调度隔离,避免服务雪崩的问题
- zull: 如果前端后端移动端调用后台系统,统一走zull网关进入,有zull网关转发请求给对应的服务

### 5.Spring Cloud 和dubbo区别?======被问+1

1. 服务调用方式 dubbo是RPC springcloud Rest Api
2. 注册中心,dubbo 是zookeeper springcloud是eureka，也可以是zookeeper
3. 服务网关,dubbo本身没有实现，只能通过其他第三方技术整合，springcloud有Zuul路由网关，作为路由服务器，进行消费者的请求分发,springcloud支持断路器，与git完美集成配置文件支持版本控制，事物总线实现配置文件的更新与服务自动装配等等一系列的微服务架构要素

### 6.什么是负载均衡以及负载均衡有哪几种策略？负载均衡的意义什么？

1. 负载均衡简单的说就是将用户的请求平摊的分配到多个服务上，从而达到系统的HA (高用)
2. 负载均衡的策略：

- 轮询策略：轮询选择服务器（Rabbon默认）
- 随机策略：随机选择一个服务器
- 重试策略：根据轮询的方式重试
- 权重策略：据响应时间去分配一个weight ，weight越低，被选择的可能性就越低
- 最低并发策略：选择最小请求数
- 可用过滤策略：过滤掉连接失败的服务节点，并且过滤掉高并发的服务节点，然后从健康的服务节点中，使用轮询策略选出一个节点返回
- 区域权衡策略：根据服务器的zone区域和可用性来轮询选择
- 当然，我们可以自定义策略：自定义方法代码

```
// 第一步：在你的启动类上级新建一个文件夹，切记，不能跟启动类同级，因为启动类里的ComponentScan注解会扫描它所在包或者子包下的所有bean注入到容器。自定义的这个配置类就会被所有的Ribbon客户端所共享，也就是我们达不到特殊化指定的目的了// 第二步@Configurationpublic class MyRule {    @Bean    public IRule myRule(){        // 默认是轮询        // return new RoundRobinRule();        // 切换为随机        // return new RandomRule();        // 自定义        return new MyRandomRule();    }}// 第三步：自定义策略（这是我从网上copy的，当个了解） public class MyRandomRule extends AbstractLoadBalancerRule{ // total = 0 // 当total==5以后，我们指针才能往下走， // index = 0 // 当前对外提供服务的服务器地址， // total需要重新置为零，但是已经达到过一个5次，我们的index = 1 // 分析：我们5次，但是微服务只有8001 8002 8003 三台，OK？ private int total = 0;    // 总共被调用的次数，目前要求每台被调用5次 private int currentIndex = 0; // 当前提供服务的机器号public Server choose(ILoadBalancer lb, Object key){  if (lb == null) {   return null;  }  Server server = null;  while (server == null) {   if (Thread.interrupted()) {    return null;   }   List<Server> upList = lb.getReachableServers();   List<Server> allList = lb.getAllServers();   int serverCount = allList.size();   if (serverCount == 0) {    return null;   } if(total < 5)            {             server = upList.get(currentIndex);             total++;            }else {              total = 0;             currentIndex++;             if(currentIndex >= upList.size())             {               currentIndex = 0;             }            }  if (server == null) {    Thread.yield();    continue;   } if (server.isAlive()) {    return (server);   }     server = null;   Thread.yield();   }   return server;   }@Override public Server choose(Object key) {  return choose(getLoadBalancer(), key); }@Override public void initWithNiwsConfig(IClientConfig clientConfig) { } }
```

1. 在计算中，负载均衡可以改善跨计算机，计算机集群，网络链接，中央处理单元或磁盘驱动器等多种计算资源的工作负载分布。负载均衡旨在优化资源使用，最大化吞吐量，最小化响应时间并避免任何单一资源的过载。使用多个组件进行负载均衡而不是单个组件可能会通过冗余来提高可靠性和可用性。负载均衡通常涉及专用软件或硬件，例如多层交换机或域名系统服务器进程

### 7.什么是 Hystrix？它如何实现容错？

1. Hystrix是一个应用于处理分布式系统的延迟和容错的开源库，在分布式系统里，许多依赖不可避免的会调用失败，比如超时，异常等。Hystrix 能够保证在一个依赖出问题的情况下，不会导致整个体系服务失败，避免级联故障，以提高分布式系统的弹性
2. 如何实现容错！[可以查看文章，后面的问题也可以查看](https://thinkwon.blog.csdn.net/article/details/104397367)

PS：因为目前面试遇到的问题基本没有springcloud方面的，所以该文章主要是收集上面文章的内容，目的就是统一，方便我背面试题！后续面试到的题我会加上去的！比如第四题和第五题被问到过，第五题这个上面的文章有，第四题没有，但是熟springcloud的都知道这个问题不背也肯定会

### 8.什么是 Hystrix 断路器？

1. Hystrix“断路器”本身是一种开关装置，当某个服务单元发生故障之后，通过断路器的故障监控 (类似熔断保险丝)  ，向调用方返回一个服务预期的，可处理的备选响应 (FallBack)  ，而不是长时间的等待或者抛出调用方法无法处理的异常，这样就可以保证了服务调用方的线程不会被长时间。不必要的占用，从而避免了故障在分布式系统中的蔓延，乃至雪崩。

### 9.什么是服务熔断？

1. 熔断机制是赌赢雪崩效应的一种微服务链路保护机制。
2. 当扇出链路的某个微服务不可用或者响应时间太长时，会进行服务的降级，进而熔断该节点微服务的调用，快速返回错误的响应信息。检测到该节点微服务调用响应正常后恢复调用链路。在SpringCloud框架里熔断机制通过Hystrix实现。Hystrix会监控微服务间调用的状况，当失败的调用到一定阀值缺省是5秒内20次调用失败，就会启动熔断机制。熔断机制的注解是：@ HystrixCommand

### 10.什么是服务降级？服务降级主要用于什么场景呢？

1. 服务降级是指 当服务器压力剧增的情况下，根据实际业务情况及流量，对一些服务和页面有策略的不处理，或换种简单的方式处理，从而释放服务器资源以保证核心业务正常运作或高效运作。说白了，就是尽可能的把系统资源让给优先级高的服务
2. 当整个微服务架构整体的负载超出了预设的上限阈值或即将到来的流量预计将会超过预设的阈值时，为了保证重要或基本的服务能正常运行，可以将一些 不重要 或 不紧急 的服务或任务进行服务的 延迟使用 或 暂停使用

### 11.服务熔断和降级的区别？

1. 服务熔断—》服务端：某个服务超时或异常，引起熔断~，类似于保险丝(自我熔断)
2. 服务降级—-》客户端：从整体网站请求负载考虑，当某个服务熔断或者关闭之后，服务将不再被调用，此时在客户端，我们可以准备一个 FallBackFactory ，返回一个默认的值(缺省值)。会导致整体的服务下降，但是好歹能用，比直接挂掉强
3. 触发原因不太一样，服务熔断一般是某个服务（下游服务）故障引起，而服务降级一般是从整体负荷考虑；管理目标的层次不太一样，熔断其实是一个框架级的处理，每个微服务都需要（无层级之分），而降级一般需要对业务有层级之分（比如降级一般是从最外围服务开始）
4. 实现方式不太一样，服务降级具有代码侵入性(由控制器完成/或自动降级)，熔断一般称为自我熔断

#### 12.什么是 Spring Cloud Bus？我们需要它吗？

#### 13.Spring Cloud断路器的作用

### 14.什么是SpringCloud config分布式配置中心？它能干嘛？

1. spring cloud config 为微服务架构中的微服务提供集中化的外部支持，配置服务器为各个不同微服务应用的所有环节提供了一个中心化的外部配置
2. spring cloud config 分为服务端和客户端两部分

- 服务端也称为 分布式配置中心，它是一个独立的微服务应用，用来连接配置服务器并为客户端提供获取配置信息，加密，解密信息等访问接口
- 客户端则是通过指定的配置中心来管理应用资源，以及与业务相关的配置内容，并在启动的时候从配置中心获取和加载配置信息；配置服务器默认采用git来存储配置信息，这样就有助于对环境配置进行版本管理。并且可用通过git客户端工具来方便的管理和访问配置内容

1. 它能干嘛：

- 集中式管理配置文件
- 不同环境，不同配置，动态化的配置更新，分环境部署，比如 /dev /test /prod /beta /release
- 运行期间动态调整配置，不再需要在每个服务部署的机器上编写配置文件，服务会向配置中心统一拉取配置自己的信息
- 当配置发生变动时，服务不需要重启，即可感知到配置的变化，并应用新的配置
- 将配置信息以REST接口的形式暴露

#### 15.什么是Spring Cloud Gateway?

#### 16.分布式事务如何处理，怎么保证事务的一致性？

### 17.Eureka对比和Zookeeper区别?

1. 首先了解一下CAP原则：

- C：强一致性：在分布式系统中的所有数据备份，在同一时刻是否同样的值
- A: 可用性：在一个分布式系统的集群中一部分节点故障后，该集群是否还能够正常响应客户端的读写请求
- P: 分区容错性：以实际效果而言，分区相当于对通信的时限要求。系统如果不能在时限内达成数据一致性，就意味着发生了分区的情况，必须就当前操作在C和A之间做出选择

1. CAP理论的核心：

- 一个分布式系统不可能同时很好的满足一致性，可用性和分区容错性这三个需求
- 根据CAP原理，将NoSQL数据库分成了满足CA原则，满足CP原则和满足AP原则三大类
  - CA：单点集群，满足一致性，可用性的系统，通常可扩展性较差
  - CP：满足一致性，分区容错的系统，通常性能不是特别高
  - AP：满足可用性，分区容错的系统，通常可能对一致性要求低一些

1. 区别：ZooKeeper 基于 CP，不能保证高可用，Eureka 基于AP，能保证高可用。Eureka可以很好的应对因网络故障导致部分节点失去联系的情况，而不会像zookeeper那样使整个注册服务瘫痪

PS：ZooKeeper-》CP：

- 当向注册中心查询服务列表时，我们可以容忍注册中心返回的是几分钟以前的注册信息，但不能接收服务直接down掉不可用。也就是说，服务注册功能对可用性的要求要高于一致性。但zookeeper会出现这样一种情况，当master节点因为网络故障与其他节点失去联系时，剩余节点会重新进行leader选举。问题在于，选举leader的时间太长，30-120s，且选举期间整个zookeeper集群是不可用的，这就导致在选举期间注册服务瘫痪。在云部署的环境下，因为网络问题使得zookeeper集群失去master节点是较大概率发生的事件，虽然服务最终能够恢复，但是，漫长的选举时间导致注册长期不可用，是不可容忍的。

PS：Eureka-》AP：

- Eureka看明白了这一点，因此在设计时就优先保证可用性。Eureka各个节点都是平等的，几个节点挂掉不会影响正常节点的工作，剩余的节点依然可以提供注册和查询服务。而Eureka的客户端在向某个Eureka注册时，如果发现连接失败，则会自动切换至其他节点，只要有一台Eureka还在，就能保住注册服务的可用性，只不过查到的信息可能不是最新的，除此之外，Eureka还有之中自我保护机制，如果在15分钟内超过85%的节点都没有正常的心跳，那么Eureka就认为客户端与注册中心出现了网络故障，此时会出现以下几种情况：
  - Eureka不在从注册列表中移除因为长时间没收到心跳而应该过期的服务
  - Eureka仍然能够接受新服务的注册和查询请求，但是不会被同步到其他节点上 (即保证当前节点依然可用)
  - 当网络稳定时，当前实例新的注册信息会被同步到其他节点中

#### 18.执行流程
1.请求同意通过api网关（zuul/gateway）来访问内部服务
2.网关接收到请求后，从注册中心（eureka/consul/nacos）获取可用服务
3.由ribbon进行负载均衡后，分发到后端具体实例
4.微服务之间通过feign/dubbo进行通信处理业务
5.hystrix/Sentinel 负责处理服务超时熔断
6.skywalking/turbine监控服务间的调用和熔断相关指标，用于后续的监控与分析



### ==82.Mybatis==

### mybatis是什么

mybatis是一款优秀的持久层框架，可以把数据进行持久化。mybatis支持定制化SQL、存储过程，结果集映射。总的来说mybatis就是对JDBC的封装。

### 我们为什么要学习Mybatis？

因为mybatis使用的人多，传统的jdbc代码太复杂，mybatis框架简化了流程，自动化。

### 持久化是什么？持久层是什么？

持久化是指数据从瞬时状态转化到持久状态。内存是断电即失的，持久化是指把数据存储到数据库中，IO文件持久化等。
持久层是完成持久化工作的代码块。

### mapper代理开发模式要求

- 映射配置文件的命名空间必须是对应接口的全限定类名。
- 接口中方法名称要和映射配置文件中statement对象的id一致。
- 接口中方法的参数类型要和映射配置文件中statement对象中的parameterType类型一致。
- 接口中方法的返回值类型要和映射配置文件中statement对象中的resultType类型一致。

### “#{}”和“${}”的区别

#### 1.#{}是预编译处理。

Mybatis在处理#{}时，会把sql中的#{}替换为问号占位符（?），调用PreparedStatement的set方法来赋值。使用#{}可以有效的防止SQL注入，提高系统安全性。

#### 2.${}是字符串替换。

Mybatis在处理${}时，就是把${}直接替换成变量的值。不能防止sql注入。

#### 3.建议

- 能用#{}的地方就用#{}，不用或少用${}
- ${}方式一般用于传入数据库对象
- 表名作参数时，必须用 ${}。如：select * from ${tableName}
- order by时，必须用 ${}。如：select * from t_user order by ${columnName}
- 变量替换后，#{}对应的变量会自动加上单引号；变量替换后，${}对应的变量不会加上单引号（需要自己加上）。

### 什么情况下用resultMap（resultMap和resultType的区别）?

当封装的对象属性（实体类）不能与查询到的字段名一一对应，需要定义自定义对象属性和查询到的字段名一一对应。
当封装的对象属性（实体类）可以和查询到的字段名一一对应，应用resultType接收查询结果。

### 动态sql语句用到了哪些标签？

1. where标签,
2. if标签(test属性),
3. foreach(collection属性，open属性，close属性，item属性，separator属性)
4. sql标签(id属性)：抽取重复的sql代码片段；用include标签的refid属性对应sql标签的id属性来引入sql

9种动态sql标签

- trim
- where
- set
- foreach
- if
- choose
- when
- otherwise
- bind

### 如何开启二级缓存？

在核心配置文件中，配置cacheEabled的值为true(默认为true)。
映射配置文件中的<select>标签中设置useCache=”true”代表当前statement对象使用二级缓存。



### 怎么开启延迟加载？相关属性有哪些？属性的作用？

fetchType=”Lazy”,开启当前的延迟加载，lazyLoadingEnabled默认为false,可以设置为true开启全局延迟加载。
select：填写我们要调用的select映射的id
column：填写我们要传递给select映射的参数。



### 注解配置mybatis用哪个注解？它有什么属性？封装结果集用到哪个注解？

[@results](https://github.com/results),属性id和value,封装结果集用到[@result



### mybatis获取插入主键值（两种方式）

1. 主键支持自增长时：useGenratedKeys和keyProperty属性

2. 主键不支持自增长时：selectKey标签获取最大的主键值

   

### mybatis执行流程

程序执行时，通过Resources对象获取全局的配置文件，inputstream流。
实例化SqlSessionFactoryBuilder构造器。
SqlSessionFactoryBuilder执行build(inputstream)方法，通过XMLConfigBuilder对象解析配置文件流。
解析的配置信息传给Configuration对象（包含所有配置信息）。
执行build(Configuration config)方法得到SqlSessionFactory对象。
SqlSessionFactory对象实例化，加上transactional事务管理，创建Executor执行器对象。
创建SqlSession，通过SqlSession实现增删改查。
增删改查执行成功时，则提交事务，关闭。
增删改查执行失败时，则回滚事务。
![img](..\..\notes\img\offer\649.png)
程序执行时，SqlSessionFactoryBuider读取mybatis的配置文件流，构建SqlSessionFactory对象。
通过SqlSessionFactory对象生产SqlSession对象。
SqlSession第一种方式是先获取mapper，通过mapper再去执行方法。
SqlSession第二种方式是直接读取方法名，执行方法。



### 相关对象的生命周期和作用域

1.SqlSessionFactoryBuider作用域。

一旦创建了SqlSessionFactory，就不再需要它了；
因此SqlSessionFactoryBuilder实例的最佳作用域是方法作用域（局部方法变量）

2.SqlSessionFactory作用域。

SqlSessionFactory可以想象成连接池。
SqlSessionFactory一旦被创建就应该在应用的运行期间一直存在，没有任何理由丢弃它或重新创建另一个实例。
因此SqlSessionFactory的最佳作用域是应用作用域。有很多方法可以做到，最简单的就是使用单例模式或者静态单例模式。

3.SqlSession作用域。

可以视为连接到连接池的一个请求。
SqlSession的实例不是线程安全的，因此是不能被共享的。
所以它的最佳的作用域是请求或方法作用域。
用完之后关闭，否则资源会被占用。
为了确保每次都能执行关闭操作，你应该把这个关闭操作放到finally块中。



### mybatis的缓存

mybatis默认定义了两级缓存：一级缓存和二级缓存。
默认情况下只有一级缓存开启（SqlSession级别的缓存）；
二级缓存需要手动配置开启（基于namespace级别的缓存）。
为了提高扩展性，mybatis定义了缓存接口Cache。（我们可以通过实现Cache接口来自定义二级缓存）。

1.一级缓存失效

1. sqlsession增删改操作，可能会改变原来的数据，所以必定会刷新缓存。
2. sqlsession查询不同的东西。
3. sqlsession手动清理缓存。（sqlSession.clearCache();）
4. sqlsession提交或者关闭。
5. 查询不同的mapper.xml

2.二级缓存

二级缓存需要在配置文件中配置开启。
只要开启了二级缓存，在同一个mapper下有效。
所有的数据都会先放在一级缓存中。
只有当会话提交或关闭的时候，才会提交到二级缓存中。

mybatis缓存的原理

当我们查询一个数据时，会先去二级缓存中查询有没有，如果有则直接返回。
如果二级缓存中没有，再去查询一级缓存中有没有，如果有则返回数据。
如果两个缓存中都没有，则去查询数据库。
查询数据库得到的信息会先存放在一级缓存中，当sqlsession提交或者关闭时，再把一级缓存中的数据提交到二级缓存中。
![img](..\..\notes\img\offer\kuangstudy4cd68f83-743d-41b8-ab64-89fb95133d39.jpg)



### mybatis如何进行分页

1.物理分页

通过使用limit关键字，SQL分页

```
<select id="queryUserList" resultType="user">    select * from user limit #{start},#{end}</select>
```

2.使用RowBounds实现分页(逻辑分页)

```
public User getUserByName(Stirng name,int start,int end){    return userDao.getUserByName(name,new RowBounds(start,end));}
```

3.使用分页插件PageHelper

```
public List(User) getUserByPage(User user,int start,int end){    PageHelper.startPage(start,end);    return userDao.getUserByPage(user);}
```

javaType和ofType的作用

javaType:是一对一关系中，在association标签中用来指定实体类属性的类型。
ofType:是一对多关系中，在collection标签中用来指定集合中的泛型的信息。

分页插件的原理

Mybatis只支持针对ParameterHandler、ResultSetlandler、StatementHandler、Executor这4种接口的插件，Mybatis使用JDK的动态代理，为需要拦截的接口生成代理对象以实现接口方法拦戳功能，每当执行这4种接口对象的方法时，就会进入拦截方法，具体就是InvocationHandler的invoke()方法，拦截那些你指定需要拦截的方法。

分页插件的基本原理是使用Mybatis提供的插件接口，实现自定义插件。(实现Mybatis的Interceptor接口并复写intercept()方法)
在插件的拦截方法内拦截待执行的sql，然后重写sql，根据dialect方言，添加对应的物理分页语句和物理分页参数。
Mybatis使用RowBounds对象进行分页，它是针对ResultSet结果集执行的内存分页。



### mybatis框架使用了哪些设计模式

- 工厂模式

- 单例模式

- 构建者模式（builder模式）

- 代理模式：dao接口工作原理：运行时为dao生成代理类，代理对象拦截接口方法，去执行对应的sql返回数据。

- 模板方法模式

- 组合模式

- 装饰者模式

- 适配器模式

- 迭代器模式

  

### mybatis的优缺点

优点

1. mybatis的sql写在XML的文件中，没有写在程序代码中，解除sql和程序代码的耦合性，可以统一管理。
2. mybatis支持动态的编写sql语句。
3. 消除了大量的冗余代码，不需要进行手动开关连接。
4. 能和spring很好的集成。
5. 提供映射标签，支持对象与数据库的ORM字段关系映射。

缺点

1. SQL语句的编写工作量较大，尤其当字段多、关联表多时，对开发人员编写SQL语句的功底有一定要求。

2. SQL语句依赖于数据库，导致数据库移植性差，不能随意更换数据库。

   

### mybatis加载mappers文件有几种方式？

4种方式，package、class、resource、url



### mybatis执行器有几种？默认是哪一种？

Executor是mybatis执行器的顶级接口，有3种执行器，默认是simple形式。

1. SimpleExecutor执行器：每执行一次update或select，就开启一个statement对象，用完就立即关闭statement对象。
2. ReuseExecutor执行器：执行update或select，以sql作为key查找statement对象，存在就使用，不存在就创建，用完后不关闭statement，而是放在map中。
3. BatchExecutor执行器：完成批处理。
   ![img](..\..\notes\img\offer\kuangstudy99848a54-3986-4053-8646-bbbc580b620d.jpg)
   ![img](https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2022/01/20/kuangstudyb7f6469b-0f71-4096-a89b-99a25c7c917b.jpg)





### ==83.Mysql==

### 1.事务的特性哪四个

1. 原子性：事务开启后的操作要么全部成功，要么全部失败。

2. 一致性：事务开始前和结束后，要保证数据的完整性，比如转钱的例子，总数要保持一致。

3. 隔离性：多个并发事务之间是相互隔离的，不同事务之间不会有任何干扰。

4. 持久性：事务正确完成结束后，对数据的更改是永久性的。

   

### 2.并发事务下可能产生哪些问题

1. 脏读：一个事务读取到另一个事务未提交的数据。

2. 不可重复读：一个事务内两次读取同一个数据，结果却不一致。（原因是查询的过程中，其他事务做了更新操作）

3. 幻读：一个事务内两次查询的数据的条数不一致。（原因是查询的过程中，其他事务做了添加操作）

   

### 3.事务的隔离级别

1. none：无事务级别
2. read_commited：读已提交。禁止脏读
3. read_uncommited：读未提交。
4. repeatable_read：可重复读。禁止脏读、不可重复读
5. serilizable：串行化。禁止脏读、不可重复读、幻读；但是性能低下

![img](..\..\notes\img\offer\kuangstudy384d07bf-91f8-446c-9dbb-0ea40f8e831c.jpg)



### 4.MySQL使用的是哪个隔离级别

mysql默认的事务隔离级别为repeatable-read
Oracle支持的隔离级别：read committed/serializable；默认的是read committed



### 5.MySQL怎么解决幻读的问题

> SELECT查询分为快照读和实时读
>
> 

1.隔离级别设置为串行化

事务在读操作时，先加表级别的共享锁，直到事务结束才释放。
事务在写操作时，先加表级别的排它锁，直到事务结束才释放。
串行化锁定了整张表，幻读不存在的。



2.快照读通过MVCC（并发多版本控制）来解决幻读问题。

MVCC只在REPEATABLE READ 和 READ COMMITTED 两个隔离级别 下 工作。
快照读就是每一行数据中额外保存两个隐藏的列。 一个是行的创建时间， 一个是行的删除时间。存储的不是实际值，是这个行的系统版本号。
每次开始事务时，会对系统版本号+1作为当前事务的ID。
查询数据时：
如果该行数据没有被加行锁或者其他锁（现在没有其他事务对这行数据进行修改），那就直接读取数据的版本号小于等于当前事务版本号的数据,不然不会放到查询结果集里面。
如果该行数据被加了行锁或者其他锁（现在有其他事务对这行数据进行修改），那么读数据的事务不会进行等待，而是去undo log端里面读之前版本的数据。
undo_log是一种逻辑日志，是旧数据的备份。有两个作用，用于事务回滚和为MVCC提供老版本的数据。



3.实时读通过行锁来解决幻读问题。

Next-Key Lock是Gap Lock（间隙锁）和Record Lock（行锁）的结合版，都属于Innodb的锁机制。

```
select * from id>100 for update
```

会给主键id=100的记录加上record行锁。
主键id会加上gap锁，锁住id(100,+无穷大）的范围。
其他事务对id>100范围的记录读和写操作都将被阻塞。（插入id=1000的记录时候会命中主键上加的锁会报出事务异常）
Next-Key Lock会确定一段范围，然后对这个范围加锁，保证A在where的条件下读到的数据是一致的，因为在where范围内其他事务不能进行增删数据。



### 4.事务的七种传播行为

A方法调用B方法：A是当前，B是自己

1. required（默认）:如果当前存在事务，则加入该事务，如果当前不存在事务，则创建一个新的事务。

2. required_new：重新创建一个事务，如果当前存在事务，则挂起当前事务。

3. supports：如果当前存在事务，则加入该事务，如果当前不存在事务，则以非事务的方式运行。

4. not_supported：以非事务的方式运行，如果当前存在事务，则挂起当前事务。

5. mandatory：如果当前存在事务，则加入该事务，如果当前不存在事务，则抛出异常。

6. never：以非事务的方式运行，如果当前存在事务，则抛出异常。

7. nested：如果当前存在事务，就在当前事务中嵌套其他事务，如果当前没有事务，就新建一个事务。

   

### 5.数据库的三大范式

1. 保证每列的原子性。比如地址需要拆分为省、市、区列，不能只有地址列。

2. 满足第一范式的条件下，确保表中的数据每一列都和主键相关。(消除部分依赖)比如订单编号和商品编号在一张表的联合主键，商品名称、单位、商品价格不与该表的主键的相关。

3. 满足第二范式的条件下，数据表的每一列必须要和主键直接相关，不能间接相关。（消除传递依赖）比如：订单表不能把客户信息保存在一起，需要单独一个信息表。

   

### 6.MySQL有哪些数据类型

常用的有：int、bigint、varchar、datatime
还有一些其他的：tinyint、smallint、mediumint、char、tinytext、text、date、time、year、timestamp、null



### 7.InnoDB与MyISAM的区别

![img](..\..\notes\img\offer\kuangstudy83e926e0-a1b1-4209-b734-f8652beb0db9.jpg)



### 8.SQL优化的几种方式

1. 表中建立索引，优先考虑在where、group by使用到的字段。

2. 尽量避免使用select *来查询，返回无用的字段会降低查询效率；
    优化方式：建议返回具体需要使用的字段

3. 尽量避免使用in和not in，会导致数据库引擎放弃索引进行全表扫描；
    优化方式：连续数值可以使用between and；子查询可以使用exists

4. 尽量避免使用or，会导致数据库引擎放弃索引进行全表扫描
    优化方式：可以用union代替or，比如：SELECT FROM t WHERE id = 1 UNION SELECT FROM t WHERE id = 3

5. 尽量避免在字段开头模糊查询“%张”，会导致数据库引擎放弃索引进行全表扫描；
    优化方式：尽量在字段后面使用模糊查询“张%”

6. 尽量避免进行null值的判断，会导致数据库引擎放弃索引进行全表扫描；SELECT FROM t WHERE score IS NULL
    优化方式：可以给字段添加默认值0，用0值进行判断；SELECT FROM t WHERE score = 0

7. 当数据量大时，避免使用where 1=1的条件；会导致数据库引擎放弃索引进行全表扫描；
    优化方式：用代码拼装sql时进行判断，没where加where，有where加and

8. 尽量避免在where条件中等号的左侧进行表达式、函数操作，会导致数据库引擎放弃索引进行全表扫描；
    优化方式：在等号的右侧进行表达式操作。

    

### mysql锁的类型

- 基于锁的属性分类：共享锁、排他锁。
- 基于锁的粒度分类：行级锁（INNODB）、表级锁（INNODB、MYISAM）、页级锁（BDB引擎）、记录锁、间隙锁、临键锁
- 基于锁的状态分类：意向共享锁、意向排它锁。

1. 共享锁（即读锁）：读取数据时不支持修改，防止出现重复读取的问题。

2. 排他锁（即写锁）：在数据修改的时候，不允许其他人同时修改，也不允许其他人读取，避免出现脏数据。

3. 表锁：指上锁的时候锁住的是整张表，当一个事务访问该表的时候，必须等前一个事务释放了锁才能进行对表进行访问。

4. 行锁：指上锁的时候锁住的是表的某一行或多行记录，其他事务访问同一张表时，只有被锁住的记录不能访问，其他的记录可正常访问。

5. 记录锁（属于行锁的一种，Record Lock）：记录锁的范围只是表中的某一条记录，记录锁是说事务在加锁后锁住的只是表的某一条记录。加了记录锁之后数据可以避免数据在查询的时候被修改的重复读问题，也避免了在修改的事务未提交前被其他事务读取的脏读问题。

6. 页锁：页锁是MySQL中锁定粒度介于行锁和表锁中间的一种锁。一次锁定相邻的一组记录（一页数据）。

7. 间隙锁（属于行锁的一种，gap Lock）：间隙锁是在事务加锁后其锁住的是表记录的某一个区间，遵循左开右闭原则。

8. 临键锁（属于行锁的一种，Next-Key Lock）：它是INNODB的行锁默认算法，它就是记录锁和间隙锁的组合，临键锁会把查询出来的记录锁住，同时也会把该范围查询内的所有间隙空间也会锁住，再之它会把相邻的下一个区间也会锁住。

   

### 意向锁概念

如果事务A加锁成功之后就设置一个状态告诉后面的人，已经有人对表里的行加了一个排他锁了，你们不能对整个表加共享锁或排它锁了，那么后面需要对整个表加锁的人只需要获取这个状态就知道自己是不是可以对表加锁，避免了对整个索引树的每个节点扫描是否加锁，而这个状态就是意向锁。

1. 意向共享锁：当一个事务试图对整个表进行加共享锁之前，首先需要获得这个表的意向共享锁。

2. 意向排他锁：当一个事务试图对整个表进行加排它锁之前，首先需要获得这个表的意向排它锁。

   

### 左连接、右连接、全连接，他们有什么区别？

    CREATE TABLE `a_table` (
      `a_id` int(11) DEFAULT NULL,
      `a_name` varchar(10) DEFAULT NULL,
      `a_part` varchar(10) DEFAULT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8
    
    CREATE TABLE `b_table` (
      `b_id` int(11) DEFAULT NULL,
      `b_name` varchar(10) DEFAULT NULL,
      `b_part` varchar(10) DEFAULT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8


表测试数据：


一、内连接
关键字：inner join on
语句：select * from a_table a inner join b_table b on a.a_id = b.b_id;
执行结果：


说明：组合两个表中的记录，返回关联字段相符的记录，也就是返回两个表的交集（阴影）部分。
二、左连接（左外连接）
关键字：left join on / left outer join on
语句：select * from a_table a left join b_table b on a.a_id = b.b_id;
执行结果：


说明：
left join 是left outer join的简写，它的全称是左外连接，是外连接中的一种。
左(外)连接，左表(a_table)的记录将会全部表示出来，而右表(b_table)只会显示符合搜索条件的记录。右表记录不足的地方均为NULL。

三、右连接（右外连接）
关键字：right join on / right outer join on
语句：select * from a_table a  right outer join b_table b  on a.a_id = b.b_id;
执行结果：


说明：
right join是right outer join的简写，它的全称是右外连接，是外连接中的一种。
与左(外)连接相反，右(外)连接，左表(a_table)只会显示符合搜索条件的记录，而右表(b_table)的记录将会全部表示出来。左表记录不足的地方均为NULL。


四、全连接（全外连接）
MySQL目前不支持此种方式，可以用其他方式替代解决。
五、补充，MySQL如何执行关联查询
MySQL认为任何一个查询都是一次“关联”，并不仅仅是一个查询需要到两个表匹配才叫关联，所以在MySQL中，每一个查询，每一个片段（包括子查询，甚至基于单表查询）都可以是一次关联。
当前MySQL关联执行的策略很简单：MySQL对任何关联都执行嵌套循环关联操作，即MySQL先在一个表中循环取出单条数据，然后在嵌套循环到下一个表中寻找匹配的行，依次下去，直到找到所有表中匹配的行为止。然后根据各个表匹配的行，返回查询中需要的各个列。请看下面的例子中的简单的查询：

查询语句：select tbl1.col1, tbl2.col2 from tbl1 inner join tbl2 using(col3) where tbl1.col1 in (5, 6);
假设MySQL按照查询中的表顺序进行关联操作，我们则可以用下面的伪代码表示MySQL将如何完成这个查询：

    outer_iter = iterator over tbl1 where col1 in (5, 6)
    outer_row = outer_iter.next
    while outer_row
        inner_iter = iterator over tbl2 where col3 = outer_row.col3
        inner_row = inner_iter.next
        while inner_row
            output [ outer_row.col1, inner_row.col2]
            inner_row = inner_iter.next
        end
        outer_row = outer_iter.next
    end

上面的执行计划对于单表查询和多表关联查询都适用，如果是一个单表查询，那么只需要上面外层的基本操作。对于外连接，上面的执行过程仍然适用。例如，我们将上面的查询语句修改如下：
select tbl1.col1, tbl2.col2 from tbl1 left outer join tbl2 using(col3) where tbl1.col1 in (5, 6);
那么，对应的伪代码如下：

    outer_iter = iterator over tbl1 where col1 in (5, 6)
    outer_row = outer_iter.next
    while outer_row
        inner_iter = iterator over tbl2 where col3 = outer_row.col3
        inner_row = inner_iter.next
        if inner_row
            while inner_row
                output [ outer_row.col1, inner_row.col2]
                inner_row = inner_iter.next
            end
        else
            output [ outer_row.col1, null]
        end
            outer_row = outer_iter.next
    end



### ==84.Spring==

### 1.spring是什么

spring是一个开源的、轻量级的、非侵入式的、控制反转和面向切面的容器框架。



### 2.spring的目的

- 为了解决企业级应用开发的复杂性。

- 使现有技术更加容易使用，整合了现有的技术框架。

  

### 3.谈谈你对IOC的理解

IOC（控制反转）是一种编程思想，由主动的编程变成被动的接收。
控制：由谁来控制对象的创建，传统的应用是由程序本身来控制对象的的创建，使用了spring，由spring来创建对象。
反转：程序本身不再创建对象，变成现有程序被动的接收对象。
一句话总结，就是对象由spring来创建、管理、装配。

IOC容器实际上就是一个map，map里存的是各种对象，他是通过配置文件、配置类、注解的方式实现类与类之间的依赖关系。
在项目启动的时候会读取配置文件里面的bean节点、扫描到打上注解的类，根据全限定类名使用反射创建对象放到map里。（在xml里配置的bean节点、[@repository](https://github.com/repository)、[@service](https://github.com/service)、[@controller](https://github.com/controller)、[@component](https://github.com/component)）
IOC创建对象默认是通过无参构造创建对象；也可以通过有参构造创建对象。

IOC让获得依赖对象的过程由自身管理变为了由IOC容器主动注入。
依赖注入是实现IOC的方法，就是由IOC容器在运行期间，动态地将某种依赖关系注入到对象属性。

然后通过DI（依赖注入）来进行对象属性的注入。
有四种依赖注入的方式：
构造器注入
set方法注入
c命名空间注入（本质构造器注入，需要有构造器）
p命名空间注入（本质set方法注入，需要有set方法）



### IOC他的底层是怎么实现的？

什么是 IOC

    控制反转，把对象创建和对象之间的调用过程，交给 Spring 进行管理
    使用 IOC 目的：为了耦合度降低

2、IOC 底层原理

    xml 解析——XML有三种解析方式：DOM4J  SAX  STAX  DOM
    工厂模式——把对类的创建初始化全都交给一个工厂来执行，而用户不需要去关心创建的过程是什么样的
    反射 ——反射可以在运行时根据指定的类名获得类的信息

3、画图讲解 IOC 底层原理

原始方式：

![image-20220602072644654](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220602072644654.png)

工厂模式：

![image-20220602072658875](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220602072658875.png)

IOC模式:

![image-20220602072715225](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220602072715225.png)

 4、IOC（BeanFactory 接口）

1、IOC思想是基于IOC容器完成，IOC容器底层就是对象工厂

2、Spring框架中对IOC容器实现提供了两种方式（即两种接口）

    第一种接口（BeanFactory接口）：此接口是IOC容器的基本实现，也是Spring框架内部使用的接口，不提供给开发人员进行使用
    注：此接口是在加载配置文件时不会创建对象，而是在获取对象或使用时才去创建对象
    第二个接口（ApplicationContext）：此接口是BeanFactory接口的子接口，提供了更强大的功能，一般由开发人员进行

3、ApplicationContext 接口有实现类

![image-20220602072731239](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220602072731239.png)

    FileSystemXmlApplicationContext：这个方法是从文件绝对路径加载配置文件，例如：
    
    ApplicationContext ctx = new FileSystemXmlApplicationContext( "C:/Test/spring.xml ");
    
    ClassPathXmlApplicationContext：这个方法是从classpath下加载配置文件(适合于相对路径方式加载)，例如：
    
    ApplicationContext ctx = new ClassPathXmlApplicationContext( "/spring.xml ");
    
    注：此接口在加载配置文件时就会创建配置文件中所配置的对象



### 4.IOC创建对象的方式

- 通过无参构造创建对象

- 通过有参构造创建对象

  

### 5.依赖注入的四种方式

- 构造器注入

- set方法注入

- c命名空间注入（本质构造器注入，需要有构造器）

- p命名空间注入（本质set方法注入，需要有set方法）

  

### 6.bean的作用域

bean的默认作用域是单例模式singleton

- 单例模式singleton：每个IOC容器只有唯一的一个实例对象。

- 原型模式prototype：容器中每次get的时候，都会产生一个新的对象。

- 请求request：一个HTTP请求会产生一个Bean对象。（只在基于web的Spring ApplicationContext中可用）

- 会话session：限定一个Bean的作用域为HTTPsession的生命周期。（只在基于web的Spring ApplicationContext中可用）

- 全局会话global session：限定一个Bean的作用域为全局HTTPSession的生命周期。（只在基于web的Spring ApplicationContext中可用）

  

### 7.bean的自动装配

1. 在xml配置文件中，以上的四种依赖注入方式配置属性。

2. 通过[@Bean](https://github.com/Bean)注解配置属性

3. 隐式的自动装配bean
    byname:会自动在容器上下文中查找，和自己对象set方法后面的值对应的beanid。（需要保证所有的bean的id唯一，并且这个bean需要和自动注入的属性set方法的值一致）
    bytype:会自动在容器上下文中查找，和自己需要对象的属性类型相同的bean。（需要保证所有的bean的class唯一，并且这个bean需要和自动注入的属性的类型一致）

4. 隐式的注解装配bean
    [@AutoWired](https://github.com/AutoWired)：默认通过byType的方式实现，而且必须要求这个对象存在！类型不唯一则通过byName
    如果AutoWired不能唯一自动装配上属性，则需要通过[@Qualifier](https://github.com/Qualifier)(value=”xxx”)指定名字。
    [@Resource](https://github.com/Resource)：默认通过byName的方式实现，如果找不到名字，则通过byType实现，如果两个都找不到就报错。

    

### 8.谈谈你对AOP的理解

面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。
AOP是OOP的延续，利用AOP可以将程序中的公共业务逻辑（比如安全管理，日志，事务管理，缓存等）封装成一个切面，然后注入到目标对象（主要业务逻辑）中去，可以减少系统的重复代码和降低模块之间的耦合度，提高程序可重用性，同时提高开发的效率。（切面就是那些与主要业务无关，但所有主要业务模块都会调用的公共业务逻辑。）
AOP可以对某个对象或某些对象的功能进行增强，比如对象中的方法进行增强，可以在执行某个方法之前额外的做一些事情，在某个方法执行之后额外的做一些事情。



### 9.AOP的名词解释

- 横切关注点：跨越应用程序多个模块的方法或功能。（与我们业务逻辑无关，但需要我们关注的部分就是横切关注点。例如：日志、缓存、安全、事务等等）

- 切面（Aspect）：横切关注点被模块化的特殊对象。它是一个类

- 通知（Advice）：切面必须要完成的工作。它是类中的一个方法

- 目标（Target）：被通知的对象。它是一个接口或方法

- 代理（Proxy）：向目标对象应用通知之后创建的对象。它是代理类

- 切入点（PointCut）：切面通知执行的“地点”的定义。（连接点和切入点就是在哪个地方执行，即invoke方法执行的地方）

- 连接点（JointPoint）：与切入点匹配的执行点

  

### 10.spring有哪些通知类型

- 前置通知：在目标方法执行之前执行
- 后置通知：在目标方法执行之后执行（目标方法抛出异常则不执行）
- 环绕通知：在目标方法执行之前和之后都可以执行额外代码的通知
- 异常通知：在目标方法抛出异常时执行的通知
- 最终通知：在目标方法执行之后执行（目标方法抛出异常也执行）

常见使用场景
![img](..\..\notes\img\offer\kuangstudyeab0bc55-cac1-4d89-b951-8e49be410cf5.jpg)



### 11.五种通知执行的顺序

1.在目标方法没有抛出异常的情况下

前置通知

环绕通知的调用目标方法之前的代码

目标方法

环绕通知的调用目标方法之后的代码

后置通知

最终通知

2.在目标方法抛出异常的情况下

前置通知

环绕通知的调用目标方法之前的代码

目标方法 抛出异常 异常通知

最终通知



### 12.讲一讲spring事务的原理

spring事务的本质是数据库对事务的支持，使用spring的事务管理时，在执行增删改查之前之后，spring可以自动完成开启事务和关闭事务的操作。
配置文件开启注解驱动，在类或方法上加上[@Transactional](https://github.com/Transactional)注解，spring启动时会解析相关bean，查看bean拥有[@Transactional](https://github.com/Transactional)注解的类和方法，并为这些类和方法生成代理，并且根据[@Transactional](https://github.com/Transactional)的相关参数进行配置注入，这样在代理中完成事务的处理。



### spring事务什么时候会失效

1. 发生自调用，类里面使用this调用本类的方法，此时这个this对象不是代理类，而是UserService对象本身，应该使用注入的userService调用。

2. [@Transactional](https://github.com/Transactional)只能用于public的方法上，否则事务会失效。

3. 数据库本身不支持事务。

4. 没有被spring管理的类。

5. 异常被try…catch捕获，不会回滚事务。

   

### 13.spring如何解决循环依赖的问题

解答来自：https://www.bilibili.com/video/BV1Ga4y1E7Br?from=search&seid=13790499314022312558&spm_id_from=333.337.0.0
首先spring的bean是由BeanDefinition建模类来的，spring的bean有一系列复杂的生命周期，spring容器启动会去扫描bean，扫描完之后会把bean变成一个BeanDefinition存到一个BeanDefinitionMap中，然后对这个BeanDefinitionMap进行遍历，遍历完做一些验证，比如是否单例、是否原型、是否懒加载、是否factoryBean等等，验证完后，然后获取当前的类是否在单例池当中，如果没有，再去看一下有没有在二级缓存当中，如果没有，那么spring就开始创建这个bean：第一步推断这个bean的最佳构造方法，然后通过反射实例化这个对象，然后根据这个对象对这个bean做一些初始化工作…

> 回答如下：

首先spring的bean是由BeanDefinition构建来的，BeanDefinition可以理解为bean的一个建模，如果我们要理解循环依赖的话，首先要讲到spring的bean的生命周期，spring的bean的生命周期大体上分为这么几步：spring容器启动会去扫描bean，扫描完之后会把bean变成一个BeanDefinition存到一个BeanDefinitionMap中，然后对这个BeanDefinitionMap进行遍历，遍历完做一些验证，比如是否单例、是否原型、是否懒加载、是否抽象、是否factoryBean等等，验证完后，然后获取当前的类是否在单例池当中，如果没有，再去看一下有没有在二级缓存当中，如果没有，那么spring就开始创建这个bean对象X，创建X对象之后会对这个对象做一些初始化工作，填充属性，在填充属性的过程中，发现X依赖了Y，那么他就会走Y的生命周期（和X一样），首先判断Y有没有在单例池中，如果没有，再去看一下Y有没有在二级缓存当中，这个时候Y是没有在二级缓存中的，Y往下执行，然后Y就会实例化完成，实例化完成之后会对Y做一些初始化工作，比如说把Y提前暴露（放在二级缓存中），比如说把Y属性填充，Y在填充属性的时候，发现要填充X，这个时候X并没有完整的实例化好，所以Y不能填充，Y要去走一遍创建X对象的流程，那么又会走第一次X的流程，再走X流程的过程中，发现X已经被提前暴露了，所以Y能够拿到一个已经提前暴露好的ObjectFactory所产生的一个X对象，这样子就完成了循环依赖。

> 补充回答：

spring的循环依赖只支持单例，为什么只支持单例？
因为不支持单例的话，那么第一遍，我们的X压根就不会走生命周期流程；单例的话会在spring容器初始化的时候走我们bean的生命周期流程。
如果是原型的话，一开始是不会走生命周期流程的，只有在用到的时候才会去走生命周期流程，所以说循环依赖只支持单例。

（X和Y只支持非构造方法的依赖注入）
如果X和Y是通过构造方法进行相互依赖的，那第一遍实例化X的时候，需要去拿Y，Y又没有，我要去拿Y，创建Y的过程中又需要X。就这样不行

为什么不直接缓存X，而是缓存一个X对象所对应的ObjectFactory？
如果我们直接缓存X，那么拿出来的就是一个X对象，程序员很难进行扩展；如果我们缓存的是一个ObjectFactory，spring内部可以通过BeanPostProcessor接口，能够对ObjectFactory在产生X这个过程当中，程序员还是可以进行干预，扩展，得到一个自己想要的X对象。

![img](..\..\notes\img\offer\kuangstudyd56f4aa5-a119-46f5-bcfc-74565a7dbab0.jpg)



### BeanFactory和ApplicationContext有什么区别？

ApplicationContext是BeanFactory的子接口；
ApplicationContext提供了更完整的功能：

1. 继承Messagesource，支持国际化文本消息。
2. 统一的资源文件访问方式。
3. 提供在监听器中注册bean的事件。
4. 同时加载多个配置文件。

- BeanFactory是采用延迟加载的方式创建对象，当我们使用某个bean时再去加载对象实例化。（如果某个bean属性注入失败，必须要调用该bean才会抛出异常）

- ApplicationContext是在容器启动时一次性创建所有的bean，如果存在配置错误，可以及时发现；并且如果需要使用到某个bean时可以不用等待，直接注入。
  相对于BeanFactory，ApplicationContext的不足是占用内存空间，当应用程序配置Bean较多时，程序启动较慢。
  
  

### Spring框架中的单例Bean线程安全吗？

不是线程安全的，不要在bean中声明任何有状态的实例变量或类变量。
如果一定要定义变量，就需要使用ThreadLocal把变量变为线程私有的；
如果bean的实例变量或类变量需要在多个线程之间共享，那么就只能使用synchronized、lock、CAS等这些实现线程同步的方法。



### Spring框架中用到了哪些设计模式？

工厂模式：Spring中的BeanFactory创建对象的实例就是工厂模式的体现，根据传入一个唯一的标识来获得Bean对象。
单例模式：spring创建对象的时候默认采用的都是单例模式。
模板方法模式：解决代码重复的问题，比如RestTemplate、JpaTemplate
适配器模式：
装饰者模式：
动态代理模式：Spring的AOP功能用到了JDK的动态代理和CGLIB字节码生成技术；
观察者模式：
策略模式：



### spring和springboot的关系是什么？

SpringBoot是为了让大家更容易使用spring，更容易集成各种常用的中间件、开源软件。
SpringBoot基于Spring开发, SpringBoot本身并不提供Spring框架的核心特性以及扩展功能，只是用于快速、敏捷地开发新一代基于Spring框架的应用程序。
SpringBoot不是用来替代spring的解决方案，而是和spring框架紧密结合提升spring开发者体验的工具。



#### SpringBoot有哪些优点？

#### 如何自定义一个SpringBootStarter？



### ==85.SpringMVC==

### 1.springmvc执行过程

1. 用户发送请求，前端控制器（DispatcherServlet）接收请求并拦截请求。

2. 前端控制器调用处理器映射器（handlermapping），请求获取处理器（handler），即HandlerExecution。

3. HandlerExecution根据请求的url找到具体的处理器（controller），生成处理器对象及处理器拦截器(如果有则生成)然后将其返回给前端控制器。

4. 前端控制器调用处理器适配器（handlerAdapter），处理器适配器找到具体的处理器去执行。

5. 处理器执行完成后返回给handlerAdapter一个modelandview视图。

6. handlerAdapter把视图返回给DispatcherServlet

7. DispatcherServlet调用ViewResolver，把modelandview传给他。

8. ViewResolver执行完成后返回给DispatherServlet一个具体的View
   - viewResolver获取mv的数据；
   - 解析mv的视图名字；
   - 拼接视图名字找到对应的视图；
   - 将数据渲染到视图上

9. DispatcherServlet对View进行渲染视图（即将模型数据填充至视图中）

10. DispatcherServlet把视图响应呈现给用户。

    

### 2.什么是springMVC?简单介绍下你对springMVC的理解

SpringMVC是一个基于Java实现了MVC设计模式的请求驱动类型的轻量级Web框架。
通过MVC模式把业务逻辑、视图、数据分离开来组织代码，降低视图和业务逻辑的耦合性，简化了开发，使流程更清晰。



### 3.springMVC的优点

1. 可以支持各种视图技术；jsp，thymeleaf…

2. 可以和spring框架集成。

3. 支持各种请求资源的映射策略。

   

### 4.springMVC常用的注解

@RequestMapping：用于处理请求url映射的注解，可用于类或方法上。
@RequestBody：实现接收http请求的json数据，把json转换为java对象。
@ResponseBody：将controller方法返回对象转化为json响应给客户。
@RequestParam：将请求参数绑定到控制器的方法的参数上。
@PathVariable：用于绑定url中的占位符。spring3.0以后支持；springmvc支持rest风格url的重要的标志。/user/{id}
@Controller：标记可以被spring扫描，注册到上下文中的bean
@RestController：相当于@ResponseBody+@Controller



### 5.SpringMVC怎么设定重定向和转发的

- 转发：在返回值前面加上forward；比如forward:/hello.jsp

- 重定向：在返回值前面加上redirect；比如redirect:/hello.jsp 也可以是一个请求比如redirect:/hello.do

  

### 6.springMVC和struts2的区别有哪些？

1. springmvc的入口是一个servlet即前端控制器（DispatchServlet）；
    struts2入口是一个filter过虑器（StrutsPrepareAndExecuteFilter）。

2. springmvc是基于方法开发(一个url对应一个方法)，请求参数传递到方法的形参，可以设计为单例或多例(建议单例)；
    struts2是基于类开发，传递参数是通过类的属性，只能设计为多例。

3. Struts采用值栈存储请求和响应的数据，通过OGNL存取数据；
    springmvc通过参数解析器是将request请求内容解析，并给方法形参赋值，将数据和视图封装成ModelAndView对象，最后将ModelAndView中的模型数据通过reques域传输到页面。

    

### 7.如何解决POST请求中文乱码问题?

在web.xml中配置一个CharacterEncodingFilter过滤器，设置成utf-8



### 8.GET请求乱码怎么解决

1. 修改tomcat配置文件添加编码与工程编码一致.
2. 对参数进行重新编码；ISO8859-1是tomcat默认编码，需要将tomcat编码后的内容按utf-8编码。
3. 

### 9.springMVC里面拦截器是怎么写？

1. 自定义类实现HandlerInterceptor接口，重写preHandle方法（处理前）、postHandle方法（处理后）、afterCompletion方法（清理操作）。
2. 继承适配器类，在接口方法中实现处理逻辑，最后在SpringMVC配置文件中配置拦截器。

详情：https://www.cnblogs.com/yoci/p/10642379.html



### SpringMvc的控制器是不是单例模式，如果是，有什么问题，怎么解决？

单例模式，解决方法是在控制器中不写变量，如果必须要写，需要自己进行同步问题的解决。



### 拦截器和过滤器的区别

https://www.kuangstudy.com/bbs/1377253872002789377#header18



###### 2.1过滤器

1. servlet规范中的一部分，在任何的Javaweb工程中都可以使用。
2. 在<url-pattern>中配置了/*，可以对所有要访问的资源进行拦截。

###### 2.2拦截器

1. 拦截器是springMVC框架自己的，只有使用了springMVC框架的工程才能使用。（最早在struct框架中就有）
2. 拦截器只会拦截访问的控制器方法，如果访问的是html/css/js/jsp/image则不会进行拦截。
3. 拦截器是AOP思想的具体应用。



### 10.SpringMvc和SpringBoot的区别

> - Spring 最初利用“工厂模式”（DI）和“代理模式”（AOP）解耦应用组件。
> - 大家觉得挺好用，于是按照这种模式搞了一个 MVC框架（一些用Spring 解耦的组件），用开发 web 应用（ SpringMVC ）。
> - 然后有发现每次开发都写很多样板代码，为了简化工作流程，于是开发出了一些“==懒人整合包==”（starter），这套就是 Spring Boot。 

###### Spring MVC的功能

- Spring MVC提供了一种==轻度耦合==的方式来开发web应用。

- Spring MVC**是Spring的一个模块**，是一个web框架。
- 通过Dispatcher Servlet, ModelAndView 和 View Resolver，开发web应用变得很容易。
- 解决的问题领域是网站应用程序或者服务开发——URL路由、Session、模板引擎、静态Web资源等等。

###### Spring Boot的功能

- Spring Boot实现了==自动配置==，降低了**项目搭建**的==复杂度==。
  - Spring框架需要进行**大量的配置**，Spring Boot引入自动配置的概念，让项目设置变得很容易。
  - Spring Boot本身并不提供Spring框架的核心特性以及扩展功能，只是用于快速、敏捷地开发新一代基于Spring框架的应用程序。
  - 也就是说，它==并不是用来替代Spring==的解决方案，而是和Spring框架紧密结合用于提升Spring开发者体验的工具。
  - 同时它集成了大量常用的==第三方库==配置(例如Jackson, JDBC, Mongo, Redis, Mail等等)，Spring Boot应用中这些第三方库几乎可以==零配置==的开箱即用(out-of-the-box)，大部分的Spring Boot应用都只需要非常少量的配置代码，开发者能够更加专注于业务逻辑。

- Spring Boot只是承载者，辅助你==简化项目搭建过程==的。如果承载的是WEB项目，使用Spring MVC作为MVC框架，那么工作流程和你上面描述的是完全一样的，因为这部分工作是Spring MVC做的而不是Spring Boot。

- 对使用者来说，换用Spring Boot以后，项目初始化方法变了，配置文件变了，另外就是不需要单独安装Tomcat这类容器服务器了，maven打出jar包直接跑起来就是个网站，但你最核心的业务8逻辑实现与业务流程实现没有任何变化。

- **所以，用最简练的语言概括就是：**

==Spring 是一个“引擎”；==

==Spring MVC 是基于Spring的一个 MVC 框架 ；==

==Spring Boot 是基于Spring4的条件注册的一套快速开发整合包。==



### ==86.Redis==

### 1.什么是redis？redis用来做什么

Redis（Remote Dictionary Server )，即远程字典服务。
是一个开源的使用ANSI C语言编写、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。
redis的数据是存储在内存中，读写速度非常快，每秒可以处理超十万次读写；因此广泛应用于缓存，也可以用来做分布锁。
redis支持事务、持久化、集群、多样的数据类型。



### 2.redis的基本数据类型

string字符串
list列表
set集合
hash哈希
zset有序集合
还有三种特殊类型：
geospatial：可以用来推算地理位置信息
hyperloglog：可以进行基数统计（基数：不重复的数）
bitmap：位存储，可以用来统计用户信息；登录，不登录；活跃，不活跃；打卡，365打卡，统计打卡天数。



### 3.redis为什么那么快

redis是基于内存的数据库，相对于基于磁盘的MySQL数据库，省去了IO读写磁盘的消耗。
redis是单线程的，避免了多线程CPU的上下文切换和竞争锁的阻塞，这些都是耗时的操作。
redis构建了VM的机制，不会去直接调用系统函数处理，需要一定的时间移动和请求。
虚拟内存机制就是暂时把不经常访问的数据(冷数据)从内存交换到磁盘中，从而腾出宝贵的内存空间用于其它需要访问的数据(热数据)。
通过VM功能可以实现冷热数据分离，使热数据仍在内存中、冷数据保存到磁盘。这样就可以避免因为内存不足而造成访问速度下降的问题。



### 4.redis常用的应用场景

缓存
排行榜
计数器应用
共享Session
分布式锁
社交网络（共同好友，关注/取关）
消息队列
位操作



### 5.redis持久化有哪些，说一说优缺点

rdb

rdb：在指定的时间间隔内将内存中的数据集快照写入磁盘，恢复时是将快照文件直接读到内存里。
详细过程：Redis会单独创建（fork）一个子进程来进行持久化，会先将数据写入到一个临时文件中，待持久化过程都结束了，再用这个临时文件替换上次持久化好的文件。
优点：主进程是不进行任何IO操作的，确保了极高的性能；适合进行大规模数据的恢复。
缺点：需要一定间隔进行操作，如果redis宕机，最后一次修改的数据就会丢失。创建的子线程会占用一定的内存空间。

aof

aof：以日志的方法记录我们所有的写操作，只追加不改写文件；恢复数据时就根据日志的内容把我们所有的写操作从前往后执行一次完成数据恢复。
优点：每一次修改都同步，数据会更加的完整。
缺点：每秒同步一次，可能会丢失这一秒的数据。aof的运行效率比rdb慢。aof的文件远远大于rdb，所以修复数据也比rdb慢。

性能建议

aof文件是无限追加的，所以后台可以对aof文件进行重写，使文件的体积不至于过大。
aof默认文件重写文件大小是64M，建议设置5G，默认超过原大小的100%重写文件。
如果不Enable AOF，仅靠Master-Slave Replcation实现高可用性也可以，能省掉一大笔lO，也减少了rewrite时带来的系统波动。
代价是如果Master/Slave 同时倒掉，会丢失十几分钟的数据，启动脚本也要比较两个Master/Slave中的RDB文件，载入较新的那个，微博就是这种架构。



### 6.怎么实现Redis的高可用？

主从复制，读写分离。多台redis服务器，以一主二从为例，主服务器进行写操作并把数据同步给两台从服务器，从服务器负责处理读的操作。
在主从复制之上，可以用上哨兵模式，以及部署多个集群。
当主节点宕机时就会丢失写的功能，我们需要手动把一台从服务器切换成主服务器；哨兵模式就是自动选举老大（主服务器）的模式。
Redis提供了哨兵的命令，哨兵是一个独立的进程。其原理是哨兵通过发送命令，等待Redis服务器响应，从而监控运行的多个Redis实例
当哨兵监测到master宕机，会自动将slave切换成master，然后通过发布订阅模式通知其他的从服务器，修改配置文件，让它们切换主机。
主要过程：
假设主服务器宕机，哨兵1先检测到这个结果，系统并不会马上进行failover过程，仅仅是哨兵1主观的认为主服务器不可用，这个现象成为主观下线。
当后面的哨兵也检测到主服务器不可用，并且数量达到一定值时，那么哨兵之间就会进行一次投票，投票的结果由一个哨兵发起，进行failover[故障转移]操作。
切换成功后，就会通过发布订阅模式，让各个哨兵把自己监控的从服务器实现切换主机，这个过程称为客观下线。



### 7.使用过Redis分布式锁吗？有哪些注意点呢？

分布式锁，是控制分布式系统不同进程共同访问共享资源的一种锁的实现。
秒杀下单、抢红包等等业务场景，都需要用到分布式锁。



### 8.MySQL与Redis 如何保证双写一致性

1. 缓存延时双删
2. “删除缓存重试机制”
3. 读取biglog异步删除缓存。
4. 

### 9.redis的事务

Redis事务本质是一组命令的集合，一个事务中的所有命令都会被序列化，在事务执行过程的中，会按照顺序执行。一次性、顺序性、排他性的执行一系列命令。
Redis事务没有隔离级别的概念；所有的命令在事务中，并没有直接被执行，只有发起执行命令的时候才会执行。Exec
Redis单条命令是保证原子性的，但是事务不保证原子性。
redis事务执行过程：

1. 开启事务。multi
2. 命令入栈。(…)
3. 执行事务。exec



### 10.redis如何实现乐观锁

乐观锁是无论做什么都很乐观，不会上锁，更新数据时会比较一下数据，在此期间是否有人更改数据。
获取version，更新的时候比较version。
通过watch命令来监控。
在执行事务之前，会判断watch监控的对象是否被修改，如果其他线程修改了watch监控的对象，则事务会执行失败。
失败之后，可以通过unwatch解绑监控的对象，再重新绑定监控，执行事务。



### 11.redis的缓存穿透和雪崩

1.缓存穿透

用户想要查询一个数据，发现redis内存数据库没有，于是向持久层数据库查询，发现也没有，于是本次查询失败。
当用户很多的时候，缓存都没有命中（秒杀场景），都去请求了持久层数据库。
这会给持久层数据库造成很大的压力，这时候就相当于出现了缓存穿透。
解决方案：
1.布隆过滤器：是一种数据结构，对所有可能查询的参数以hash形式存储，在控制层先进行校验，不符合则丢弃，从而避免了对底层存储系统的查询压力。
2.缓存空对象：当存储层不命中后，即使返回的空对象也将其缓存起来，同时会设置一个过期时间，之后再访问这个数据将会从缓存中获取，保护了后端数据源。
第二种方案的问题：如果空值能够被缓存起来，这就意味着缓存需要更多的空间存储更多的键。（浪费内存空间）
对空值设置了过期时间，还是会存在缓存层和存储层的数据会有一段时间窗口的不一致，对于需要保持一致性的业务会有影响。

2.缓存击穿

访问量太大，缓存过期，在缓存过期的空档期，所有的访问砸在了MySQL服务器上，导致服务器宕机。（一般是热点数据的缓存过期）
解决方案：
1.设置热点数据永不过期。
2.分布式锁：使用分布式锁，保证对于每个key同时只有一个线程去查询后端服务，其他线程没有获得分布式锁的权限，因此只需要等待即可。
这种方式将高并发的压力转移到了分布式锁，因此对分布式锁的考验很大。

3.缓存雪崩

缓存雪崩，是指在某一个时间段，缓存集中过期失效，redis宕机！
解决方案：
1.多增加几台redis服务器，搭建redis集群，实现高可用。
2.限流降级：这个解决方案的思想是，在缓存失效后，通过加锁或者队列来控制读数据库写缓存的线程数量。比如对某个key只允许一个线程查询数据和写缓存，其他线程等待。
3.数据加热：就是在正式部署之前，我先把可能的数据先预先访问一遍，这样部分可能大量访问的数据就会加载到缓存中。
在即将发生大并发访问前手动触发加载缓存不同的key，设置不同的过期时间，让缓存失效的时间点尽量均匀。



### redis过期键的删除策略

可以设置redis中缓存key的过期时间。
删除策略：指的是redis中缓存key过期了，redis如何处理？

- 惰性过期：只有当访问一个key时，才会判断该key是否已过期，过期则清除。该策略可以最太化地节省CPU资源，却对内存非常不友好。

- 定期过期：每隔一定的时间，会扫描一定数量的数据库的expires字典中一定数量的key，并清除其中已过期的key。通过调整定时扫描的时间间隔和每次扫描的限定耗时，可以在不同情况下使得CPU和内存资源达到最优的平衡效果。

- 定时过期：设定过期时间，key到期了直接就删除。

  

### redis分布式锁

如果在公司里落地生产环境用分布式锁的时候，一定是会用开源类库的，比如Redis分布式锁，一般就是用Redisson框架就好了，非常的简便易用。

可以去看看Redisson的官网，看看如何在项目中引入Redisson的依赖，然后基于Redis实现分布式锁的加锁与释放锁。下面是一个简单得Redisson分布式锁的实现伪代码：

    //获取锁
    RLock lock = redisson.getLock("myLock");
     
    //上锁
    lock.lock();
     
    //业务代码
    //...
     
    //释放锁
    lock.unlock();

代码很简单，而且人家还支持redis单实例、redis哨兵、redis cluster、redis master-slave等各种部署架构，都可以给你完美实现。

Redis分布式锁的底层原理

![image-20220602073203512](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220602073203512.png)

redis锁实现原理

（1）加锁机制：

现在某个客户端需要加锁。如果该客户端面对的是一个redis cluster 集群，它就需要根据hash节点去选择一台机器。然后就会发送一个lua脚本，脚本代码如下图所示：

![image-20220602073226263](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220602073226263.png)

redis分布式锁lua代码

为什么要用lua脚本？

因为lua脚本可以保证原子性！

上面的lua脚本代表的意思：

KEYS[1]代表的是你加锁的那个key，比如说：

RLock lock = redisson.getLock("myLock")；

这里你自己设置了加锁的那个锁key就是“myLock”。

ARGV[1]代表的就是锁key的默认生存时间，默认30秒。

ARGV[2]代表的是加锁的客户端的ID，类似于下面这样：

8743c9c0-0795-4907-87fd-6c719a6b4586:1

第一段if判断语句，就是用“exists myLock”命令判断一下，如果你要加锁的那个锁key不存在的话，你就进行加锁。

如何加锁呢？很简单，用下面的命令：

hset myLock 

    8743c9c0-0795-4907-87fd-6c719a6b4586:1 1

通过这个命令设置一个hash数据结构，这行命令执行后，会出现一个类似下面的数据结构：

![image-20220602073255215](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220602073255215.png)

上述就代表“8743c9c0-0795-4907-87fd-6c719a6b4586:1”这个客户端对“myLock”这个锁key完成了加锁。接着会执行“pexpire myLock 30000”命令，设置myLock这个锁key的生存时间是30秒。到此为止加锁就完成了。

（2）锁互斥机制：

如果客户端2来尝试加锁，执行了同样的一段lua脚本。第一个if判断会执行“exists myLock”，发现myLock这个锁key已经存在了。

接着第二个if判断，判断一下，myLock锁key的hash数据结构中，是否包含客户端2的ID，但是明显不是的，因为那里包含的是客户端1的ID。

所以，客户端2会获取到pttl myLock返回的一个数字，这个数字代表了myLock这个锁key的剩余生存时间。比如还剩15000毫秒的生存时间。此时客户端2会进入一个while循环，不停的尝试加锁。

（3）watch dog自动延期机制

客户端1加锁的锁key默认生存时间才30秒，如果超过了30秒，客户端1还想一直持有这把锁，怎么办呢？

只要客户端1一旦加锁成功，就会启动一个watch dog看门狗，他是一个后台线程，会每隔10秒检查一下，如果客户端1还持有锁key，那么就会不断的延长锁key的生存时间。

（4）可重入加锁机制：

那如果客户端1都已经持有了这把锁了，结果可重入的加锁会怎么样呢？代码如下图所示：

![image-20220602073307717](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220602073307717.png)

继续分析下lua脚本，第一个if判断肯定不成立，“exists myLock”会显示锁key已经存在了。第二个if判断会成立，因为myLock的hash数据结构中包含的那个ID，就是客户端1的那个ID，也就是“8743c9c0-0795-4907-87fd-6c719a6b4586:1”。

此时就会执行可重入加锁的逻辑，他会用：

incrby myLock 

 8743c9c0-0795-4907-87fd-6c71a6b4586:1 1

通过这个命令，对客户端1的加锁次数，累加1。

此时myLock数据结构变为下面这样：

那个myLock的hash数据结构中的那个客户端ID，就对应着加锁的次数。

（5）释放锁的机制：

如果执行lock.unlock()，就可以释放分布式锁，此时的业务逻辑也是非常简单的。就是每次都对myLock数据结构中的那个加锁次数减1。

如果发现加锁次数是0了，说明这个客户端已经不再持有锁了，此时就会用：

“del myLock”命令，从redis里删除这个key。

然后呢，另外的客户端2就可以尝试完成加锁了。这就是所谓的分布式锁的开源Redisson框架的实现机制。一般我们在生产系统中，可以用Redisson框架提供的这个类库来基于redis进行分布式锁的加锁与释放锁。

（6）上述Redis分布式锁的缺点：

一般我们在生产系统中，可以用Redisson框架提供的这个类库来基于redis进行分布式锁的加锁与释放锁。

但是这个过程中一旦发生redis master宕机，主备切换，redis slave变为了redis master。

接着就会导致，客户端2来尝试加锁的时候，在新的redis master上完成了加锁，而客户端1也以为自己成功加了锁。

此时就会导致多个客户端对一个分布式锁完成了加锁。这时系统在业务语义上一定会出现问题，导致各种脏数据的产生。

所以这个就是redis cluster，或者是redis master-slave架构的主从异步复制导致的redis分布式锁的最大缺陷：在redis master实例宕机的时候，可能导致多个客户端同时完成加锁。




### ==87.Springboot==

### 1.springboot常用的注解有哪些？

1. [@springbootApplication](https://github.com/springbootApplication):这个注解是Spring Boot最核心的注解，用在 Spring Boot的主类上，标识这是一个 Spring Boot 应用，用来开启 Spring Boot 的各项能力。实际上这个注解是[@Configuration](https://github.com/Configuration),[@EnableAutoConfiguration](https://github.com/EnableAutoConfiguration),[@ComponentScan](https://github.com/ComponentScan)三个注解的组合

2. [@EnableAutoConfiguration](https://github.com/EnableAutoConfiguration):允许 Spring Boot 自动配置注解，开启这个注解之后，Spring Boot 就能根据当前类路径下的包或者类来配置 Spring Bean

3. [@Configuration](https://github.com/Configuration):用于定义配置类，指出该类是 Bean 配置的信息源，相当于传统的xml配置文件，一般加在主类上

4. [@ComponentScan](https://github.com/ComponentScan):组件扫描。让spring Boot扫描到Configuration类并把它加入到程序上下文.[@ComponentScan](https://github.com/ComponentScan)注解默认就会装配标识了[@Controller](https://github.com/Controller)，[@Service](https://github.com/Service)，[@Repository](https://github.com/Repository)，[@Component](https://github.com/Component)注解的类到spring容器中。

5. [@Repository](https://github.com/Repository):用于标注数据访问组件，即DAO组件

6. [@Service](https://github.com/Service):用于修饰service层的组件

7. [@Component](https://github.com/Component):把普通pojo实例化到spring容器中,泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注

8. [@Controller](https://github.com/Controller):用于标注控制层组件

9. [@RestController](https://github.com/RestController) —用于标注控制层组件:相当于[@Controller](https://github.com/Controller)和[@ResponseBody](https://github.com/ResponseBody)

10. [@Bean](https://github.com/Bean)：相当于XML中的,放在方法的上面，而不是类，意思是产生一个bean,并交给spring管理

11. [@AutoWired](https://github.com/AutoWired)：byType方式。把配置好的Bean拿来用，完成属性、方法的组装，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作

12. [@Qualifier](https://github.com/Qualifier)：当有多个同一类型的Bean时，可以用[@Qualifier](https://github.com/Qualifier)(“name”)来指定。与[@Autowired](https://github.com/Autowired)配合使用

13. [@Resource](https://github.com/Resource)()：默认byName。与[@Autowired](https://github.com/Autowired)干类似的事

14. [@RequsetMapping](https://github.com/RequsetMapping)——-RequestMapping是一个用来处理请求地址映射的注解[@GetMapping](https://github.com/GetMapping)+[@PostMapping](https://github.com/PostMapping)+[@PutMapping](https://github.com/PutMapping)+[@DeleteMapping](https://github.com/DeleteMapping)等等：

15. [@Param](https://github.com/Param)：用在方法的参数前面，一般只有一个参数的时候可以考虑不用

16. [@RequestParam](https://github.com/RequestParam)：用在方法的参数前面

17. [@PathVariable](https://github.com/PathVariable)：路径变量。参数与大括号里的名字一样要相同

18. [@ConfigurationProperties](https://github.com/ConfigurationProperties)：Spring Boot可使用注解的方式将自定义的properties文件映射到实体bean中，比如config.properties文件

    

### 2.@ Component和@ bean有什么区别？

1. [@Component](https://github.com/Component)一般作用于类上，[@bean](https://github.com/bean)作用于方法上

2. [@Component](https://github.com/Component)用于自动检测和使用类路径扫描自动配置bean。注释类和bean之间存在隐式的一对一映射（即每个类一个bean）。

3. [@Bean](https://github.com/Bean)用于显式声明单个bean，而不是让Spring像上面那样自动执行它。它将bean的声明与类定义分离，并允许您精确地创建和配置bean。

   

### 3.springboot相对spring来说有什么优点？======被问+1

1. 全部采用注解方式，没有繁琐的xml配置

2. 内置http服务器，比如jetty，tomcat。不需要额外的去集成下载tomcat

3. 快速整合第三方框架，比如redis，mybatis等等（可以理解为自动配置）

   

### 4.Spring Boot 自动配置原理是什么?======被问+2

1. 首先我们可以看到，springboot的启动类上有一个[@SpringBootApplication](https://github.com/SpringBootApplication)注解。这个注解是一个复合注解或派生注解，它里面有一个注解[@EnableAutoConfiguration](https://github.com/EnableAutoConfiguration)，通俗的讲就是一个开启自动配置的注解。
2. 这个注解也是一个派生注解，其中的关键功能由[@Import](https://github.com/Import)提供，它导入的AutoConfigurationImportSelector类的selectImports()方法通过SpringFactoriesLoader.loadFactoryNames()扫描所有具有META-INF/spring.factories的jar包，它里面会有一个spring.factories的文件。
3. 这个spring.factories文件也是一组一组的key=value的形式，其中一个key是EnableAutoConfiguration类的全类名，而它的value是一个xxxxAutoConfiguration的类名的列表
4. 我们点进xxxxAutoConfiguration这个类里，它里面其实会有一些注解用来判断这个xxxxAutoConfiguration是不是生效。仔细观察还能看到一个xxxxProperties的类，点进去可以看到一些属性，后续导入需要配置一些信息的时候，可以根据这些属性去配置

PS：总结一下个人的看法：在我们启动Springboot的时候，它会通过自动配置这个注解去扫描spring.factories下的所有自动配置类，但是它并不是所有都生效，它底部会有一个判断，只有我们将对应的依赖导入了，就说明自动装配生效了，即表示配置成功！



ps：SpringBoot有一个注解@SpringBootApplication，是一个复合或者派生注解。当SpringBoot调用run方法的时候，SpringBoot会通过@EnableAutoConfiguration注解找到方法getCandidateConfigurations找到所有需要自动配置类的集合，以List集合返回，自动配置类存储在META-INF/spring.factories配置文件中，文件中以AutoConfiguration命名结尾的向spring容器中添加组件，通过以Properties结尾命名的类中取得在全局配置文件中配置的属性。XxxxProperties和@ConfigurationProperties注解与全局配置文件中对应的属性进行绑定，所以我们在项目中全局配置文件application.yml中可以修改server.port :8081等等。



ps：SpringBoot的自动配置我觉得是SpringBoot很重要的“特性”了。众所周知，SpringBoot有着“约定大于配置”的理念，这一理念一定程度上可以用“SpringBoot自动配置”来解释。



![img](..\..\notes\img\offer\offer-springboot01.webp)



SpringBoot自动配置的原理理解起来挺简单的，我们在使用SpringBoot的时候，肯定会依赖于[autoconfigure](https://www.zhihu.com/search?q=autoconfigure&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A2769103190})这么一个包。autoconfigure这个包里会有一个[spring.factories](https://www.zhihu.com/search?q=spring.factories&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A2769103190})文件，该文件定义了100+个入口的配置类。比如我们经常使用的redis、kafka等等这样常见的中间件都**预置**了配置类。当我们在启动SpringBoot项目的时候，内部就会加载这个spring.factories文件，进而去加载“有需要”的配置类。那我们在使用相关组件的时候，就会非常的方便（因为配置类已经初始化了一大部分配置信息）。一般我们只要在application配置文件写上对应的配置，就能通过各种template类直接操作对应的组件啦。



![img](..\..\\notes\img\offer\offer-springboot02.webp)



面试官：那是所有的配置类都会加载吗？这个“有需要”的配置类你是怎么理解的？

**候选者**：不是所有的配置类都会加载的，假设我们没有引入redis-starter的包，那Redis的配置类就不会被加载。具体Spring在实现的时候就是使用**@ConditionalXXX**进行判断的。比如Redis的配置类就会有@ConditionalOnClass({RedisOperations.class})的配置，说明当前环境下如果有RedisOperations.class这个字节码，才会去加载Redis的配置类



![img](..\..\\notes\img\offer\offer-springboot03.webp)



面试官：哦，这样啊，那了解了。那你知不知道Redis的配置类其实会有初始化RedisTemplate对象的操作，那假设我们没有引入redis-starter包，那他是怎么通过编译的？（当然了，其他的配置类也是有可能有一样的状况）

**候选者**：嗯，这个我看源码的时候我也发现了。其实就是在autoconfigure包里**会定义到相关的依赖**，但只是标记为optional并且只在编译环境有效。那这样是能通过编译的，**只是不会将其依赖传入到我们的应用工程里**。

**候选者**：这块还是花了我很多时间的，我最后在GitHub 的SpringBoot源码里找到的。

面试官：嗯啊，有点东西的哟。既然都聊到这块了，要不顺便聊聊你对SpringBoot starter的理解？

**候选者**：嗯，[starter](https://www.zhihu.com/search?q=starter&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A2769103190})这东西就是为了**方便调用方去使用相关的组件**的嘛，Spring框架也给我们实现了很多好用的starter。

**候选者**：比如以前我们要用Mybatis框架，可能会引入各种的包才能使用。而starter就是做了一层封装，把相关要用到的[jar](https://www.zhihu.com/search?q=jar&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A2769103190})都给包起来了，并且也写好了对应的版本。这我们使用的时候就不需要引入一堆[jar包](https://www.zhihu.com/search?q=jar包&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A2769103190})且管理版本类似的问题了。



![img](..\..\\notes\img\offer\offer-springboot04.webp)



**候选者**：现在很多开源的组件都会提供对应的springboot-starter包给我们去用，要做一个starter包并不难。参照Spring内置的实现就好了：1、在工程里引入 starter 打包相关的依赖。2、在我们工程内建spring.factories文件，编写我们配置类的全限类名。

面试官：嗯，大致都了解了，可以的。最后聊下你是怎么看这块源码的？

**候选者**：源码具体大概就不记得了，思路倒是还有的。我先从启动类开始，会有个@SpringBootApplication，后面会定位到一个自动配置的注解@EnableAutoConfiguration，那最后就能看到注解内部会去META-INF/spring.factories加载配置类

**候选者**：这块源码并不难，这个过程也了解到了原来maven有option和scope这俩标签，但确实是SpringBoot比较重要的概念吧。

面试官：好嘞，今天到这就结束了吧。

**题外**：自动配置这个问题确实被问到过几次。说实在的，对于Spring类、注解的信息我真的记不住。感觉能答出这个流程思路，也就够用了（如果面试官确实是要细究某个类名，那这种公司不去也罢）

**约定大于配置**：SpringBoot给我们内置了很多配置类，这些配置类也初始化了很多配置（**默认值**）。当我们要使用的时候，只需要覆盖这些配置项就完事了。即便我们不写，大多数情况下都不需要由我们显示配置出来，但相关组件就能正常访问了。




### 5.什么是 YAML？YAML 配置的优势在哪里 ?

1. YAML 是一种人类可读的数据序列化语言。它通常用于配置文件。与属性文件相比，如果我们想要在配置文件中添加复杂的属性，YAML 文件就更加结构化，而且更少混淆。可以看出 YAML 具有分层配置数据
2. 优势：

- 配置有序，在一些特殊的场景下，配置有序很关键

- 支持数组，数组中的元素可以是基本数据类型也可以是对象

- 简洁明了

  

### 6.Spring Boot 中如何实现定时任务 ?

1. 首先在启动类上加上注解 [@EnableScheduling](https://github.com/EnableScheduling)

2. 在你想要定时执行的方法上加cron表达式著名

   

### 7.Springboot有几个配置文件

###### **一、springboot主要配置文件种类**

1.bootstrap （.yml或.properties）

2.application（.yml或.properties）

###### **二、bootstrap与application的区别**

在 Spring Boot 中有两种上下文，一种是 bootstrap, 另外一种是 application, bootstrap  是应用程序的父上下文，也就是说 bootstrap 加载优先于  applicaton。bootstrap主要用于从额外的资源来加载配置信息，还可以在本地外部配置文件中解密属性。这两个上下文共用一个环境，它是任何Spring应用程序的外部属性的来源。bootstrap 里面的属性会优先加载，它们默认也不能被本地相同配置覆盖。

###### **三、bootstrap与application的应用场景**

1.application主要用于spring项目的自动化配置。

2.bootstrap主要有以下几个应用场景：

- 使用 Spring Cloud Config 配置中心时，这时需要在 bootstrap 配置文件中添加连接到配置中心的配置属性来加载外部配置中心的配置信息；
- 一些固定的不能被覆盖的属性；
- 一些加密/解密的场景；

##### 1.什么是SpringBoot？

- 用来简化Spring应用的初始搭建以及开发过程，使用特定的方式来进行配置
- 创建独立的Spring引用程序main方法运行
- 嵌入的tomcat无需部署war文件
- 简化maven配置
- 自动配置Spring添加对应的功能starter自动化配置

> SpringBoot来简化Spring应用开发，约定大于配置，去繁化简

##### 2、SpringBoot有哪些优缺点？

**优点**

- 独立运行

  Spring Boot 而且内嵌了各种 servlet 容器，Tomcat、Jetty 等，现在不再需要打成war 包部署到容器中，Spring Boot 只要打成一个可执行的 jar 包就能独立运行，所有的依赖包都在一个 jar 包内。

- 简化配置

  spring-boot-starter-web 启动器自动依赖其他组件，简少了 maven 的配置。

- 自动配置

  Spring Boot 能根据当前类路径下的类、jar 包来自动配置 bean，如添加一个 spring

  boot-starter-web 启动器就能拥有 web 的功能，无需其他配置。

- 无代码生成和XML配置

  Spring Boot 配置过程中无代码生成，也无需 XML 配置文件就能完成所有配置工作，这一切都是借助于条件注解完成的，这也是 Spring4.x 的核心功能之一。

- 应用监控

  Spring Boot 提供一系列端点可以监控服务及应用，做健康检测。

**缺点**

Spring Boot 虽然上手很容易，但如果你不了解其核心技术及流程，所以一旦遇到问题就很棘手，而且现在的解决方案也不是很多，需要一个完善的过程。



### 3、SpringBoot、Spring MVC和Spring有什么区别？

**Spring**

Spring最重要的特征是依赖注入。所有Spring Modules不是依赖注入就是IOC控制反转。

当我们恰当的使用DI或者是IOC的时候，可以开发松耦合应用。

**Spring MVC**

Spring MVC提供了一种分离式的方法来开发Web应用。通过运用像DispatcherServelet，MoudlAndView 和 ViewResolver 等一些简单的概念，开发 Web 应用将会变的非常简单。

**SpringBoot**

Spring和Spring MVC的问题在于需要配置大量的参数。

SpringBoot通过一个自动配置和启动的项来解决这个问题。



### 4、什么是Spring Boot Starter？

启动器是一套方便的依赖描述符，它可以放在自己的程序中。可以一站式的获取你所需要的Spring和相关技术，而不需要依赖描述符的通过示例代码搜索和复制粘贴的负载。

例如，如果想使用Spring和JPA访问数据库，只需要项目中包含spring-boot-starter-data-jpa 依赖项，你就可以正产是用。



### 5、为什么需要spring-boot-maven-plugin？

spring-boot-maven-plugin提供了一些像jar一样打包或者运行应用程序的命令。

> 1. spring-boot:run 运行SpringBoot应用程序；
>
> 2. spring-boot:repackage 重新打包你的jar包或者是war包使其可执行
>
> 3. spring-boot:start和spring-boot:stop管理Spring Boot应用程序的生命周期
>
> 4. spring-boot:build-info生成执行器可以使用的构造信息
>
>    

### 6、什么是YAML？

YAML是一种人类可读的数据序列化语言。它通常用于配置文件。
与属性文件相比，如果我们想要在配置文件中添加复杂的属性，YAML文件就更加结构化，而且更少混淆。可以看出YAML具有分层配置数据。



### 7、SpringBoot自动配置的原理

在Spring程序main方法中，添加[@SpringBootApplication](https://github.com/SpringBootApplication)或者[@EnableAutoConfiguration](https://github.com/EnableAutoConfiguration)会自动去maven中读取每个starter中的spring.gfactories文件，改文件里配置了所有需要被创建的Spring容器中的bean



### 8、RequestMapping和GetMapping的不同之处在哪里？

RequestMapping具有类属性的，可以进行GET、POST、PUT或者其他的注释中具有的请求方法。

GetMapping是Get请求方法中的一个特例，它只是RequestMapping的一个延伸，目的是为了提高清晰度。

\##9、spring-boot-starter-parent有什么作用？

我们知道，新建一个SpringBoot项目，默认都是有parent的，这个parent就是spring-boot-starter-parent，spring-boot-starter-parent主要有如下作用：

- 定义了Java编译版本为1.8

- 使用UTF-8格式编码

- 继承自spring-boor-dependencies，这里面定义了依赖的版本，也正是因为继承了这个依赖，所以我们在写依赖时才不需要写版本号

- 执行打包操作的配置

- 自动化的资源过滤

- 自动化的插件配置

- 针对application.peoperties和application.yuml的资源过滤，包括通过profile定义的不同环境的配置文件，例如application-dev.properties和application-dev.yuml。

  

### 10、SpringBoot 打成jar和普通的jar有什么区别？

Spring Boot 项目最终打包成的 jar 是可执行 jar ，这种 jar 可以直接通过`java -jar xxx.jar`命令来运行，这种 jar 不可以作为普通的 jar 被其他项目依赖，即使依赖了也无法使用其中的类。

Spring Boot 的 jar 无法被其他项目依赖，主要还是他和普通 jar 的结构不同。普通的 jar 包，解压后直接就是包名，包里就是我们的代码，而 Spring Boot 打包成的可执行 jar 解压后，在 `\BOOT-INF\classes`目录下才是我们的代码，因此无法被直接引用。如果非要引用，可以在 pom.xml 文件中增加配置，将 Spring Boot 项目打包成两个 jar ，一个可执行，一个可引用。



### 11、运行SpringBoot有几种方式？

- 打包用命令或者放到容器中运行

- 用Maven或Gradle插件运行

- 直接执行main方法运行

  

### 12、开启Spring Boot特性有哪几种方式？

- 继承spring-boot-starter-parent项目

- 导入spring-boot-dependencies项目依赖

  

### 13、什么是Spring Data？

Spring Data 是 Spring 的一个子项目。用于简化数据库访问，支持NoSQL 和 关系数据存储。其主要目标是使数据库的访问变得方便快捷。Spring Data 具有如下特点：

**SpringData 项目支持 NoSQL 存储：**

- MongoDB （文档数据库）
- Neo4j（图形数据库）
- Redis（键/值存储）
- Hbase（列族数据库）

**SpringData 项目所支持的关系数据存储技术：**

- JDBC
- JPA

Spring Data Jpa **致力于减少数据访问层 (DAO) 的开发量**. 开发者唯一要做的，就是声明持久层的接口，其他都交给 Spring Data JPA 来帮你完成！Spring Data JPA 通过规范方法的名字，根据符合规范的名字来确定方法需要实现什么样的逻辑。



### 14、什么是Swagger？你用Spring Boot实现了吗？

Swagger 广泛用于可视化 API，使用 Swagger UI 为前端开发人员提供在线沙箱。Swagger 是用于生成 RESTful Web  服务的可视化表示的工具，规范和完整框架实现。它使文档能够以与服务器相同的速度更新。当通过 Swagger  正确定义时，消费者可以使用最少量的实现逻辑来理解远程服务并与其进行交互。因此，Swagger消除了调用服务时的猜测。



### 15、前后端分离，如何维护接口文档？

前后端分离开发日益流行，大部分情况下，我们都是通过 Spring Boot 做前后端分离开发，前后端分离一定会有接口文档，不然会前后端会深深陷入到扯皮中。一个比较笨的方法就是使用 word 或者 md 来维护接口文档，但是效率太低，接口一变，所有人手上的文档都得变。在 Spring Boot 中，这个问题常见的解决方案是 Swagger ，使用 Swagger  我们可以快速生成一个接口文档网站，接口一旦发生变化，文档就会自动更新，所有开发工程师访问这一个在线网站就可以获取到最新的接口文档，非常方便。



### 16、如何使用Spring Boot实现异常处理？

Spring提供了一种使用ControllerAdvice处理异常的非常有用的方法。通过实现一个ControlerAdvice类，来处理控制类抛出的所有异常。

类中定义方法加上@MethonHandler



### 17、什么是FreeMarker模板？

FreeMarker 是一个基于 Java 的模板引擎，最初专注于使用 MVC 软件架构进行动态网页生成。使用 Freemarker  的主要优点是表示层和业务层的完全分离。程序员可以处理应用程序代码，而设计人员可以处理 html 页面设计。最后使用freemarker  可以将这些结合起来，给出最终的输出页面。



### 18、如何实现Spring Boot应用程序的安全性？

为了实现Spring Boot的安全性，使用spring-boot-starter-security依赖项，并且必须添加安全配置。它只需要很少代码。配置类将必须扩展WebSecurityConfigurerAdapter并覆盖其方法。

\##19、比较一下Spring Security和Shiro各自的优缺点？

由于Spring Boot官方提供了大量的非常方便的开箱即用的Starter，包括Spring  Security的Starter，使得在SpringBoot中使用Spring  Security变得更加容易，甚至只需要添加一个一来就可以保护所有接口，所以如果是SpringBoot项目，一般选择Spring  Security。当然这只是一个建议的组合，单纯从技术上来说，无论怎么组合，都是没有问题的。

**Shiro和Spring Security相比，主要有如下特点：**

- Spring Security是一个重量级的安全管理框架；Shiro则是一个轻量级的安全管理框架；

- Spring Security概念复杂，配置繁琐；Shiro概念简单、配置简单；

- Spring Security功能强大；Shiro功能简单

  

### 20、Spring Boot中如何解决跨域问题？

跨域可以在前端通过JSONP来解决，但是JSONP只可以发送GET请求，无法发送其他类型的请求，在RESTful风格的应用中，就显得非常鸡肋，因此推荐在后端通过（CORS，Cross-origin resource sharing）来解决跨域问题。这种解决方案并非Spring  Boot特有的，在传统的SSM框架中，就可以通过CORS来解决跨域问题，只不过之前我们是在XML文件中配置CORS，现在可以通过实现WebMvcConfigurer接口然后重写addCorsMappings方法解决跨域问题。

```
@Configurationpublic class CorsConfig implements WebMvcConfigurer {    @Override    public void addCorsMappings(CorsRegistry registry) {        registry.addMapping("/**")                .allowedOrigins("*")                .allowCredentials(true)                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")                .maxAge(3600);    }}
```

项目中前后端分离部署，所以需要解决跨域的问题。
我们使用cookie存放用户登录的信息，在spring拦截器进行权限控制，当权限不符合时，直接返回给用户固定的json结果。
当用户登录以后，正常使用；当用户退出登录状态时或者token过期时，由于拦截器和跨域的顺序有问题，出现了跨域的现象。
我们知道`一个http请求，先走filter，到达servlet后才进行拦截器的处理`，如果我们把cors放在filter里，就可以优先于权限拦截器执行。

```go
@Configurationpublic class CorsConfig {    @Bean    public CorsFilter corsFilter() {        CorsConfiguration corsConfiguration = new CorsConfiguration();        corsConfiguration.addAllowedOrigin("*");        corsConfiguration.addAllowedHeader("*");        corsConfiguration.addAllowedMethod("*");        corsConfiguration.setAllowCredentials(true);        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);        return new CorsFilter(urlBasedCorsConfigurationSource);    }}
```



### 21、什么是CSRF攻击？

CSRF 代表跨站请求伪造。这是一种攻击，迫使最终用户在当前通过身份验证的Web 应用程序上执行不需要的操作。CSRF 攻击专门针对状态改变请求，而不是数据窃取，因为攻击者无法查看对伪造请求的响应。



### 22、Spring Boot的核心注解是哪些？他主由哪几个注解组成的？

启动类上面的注解是[@SpringBootApplication](https://github.com/SpringBootApplication)，他也是SpringBoot的核心注解，主要组合包含了以下3个注解：

- [@SpringBootConfiguration](https://github.com/SpringBootConfiguration)：组合了[@Configuration](https://github.com/Configuration)注解，实现配置文件的功能；

- [@EnableAutoConfiguration](https://github.com/EnableAutoConfiguration)：打开自动配置的功能，也可以关闭某个自动配置的选项，如关闭数据源自动配置的功能：[@SpringBootApplication](https://github.com/SpringBootApplication)(exclude={DataSourceAutoConfiguration.class})；

- [@ComponentScan](https://github.com/ComponentScan)：Spring组件扫描。

  

### 23、SpringBoot的核心配置文件有哪几个？他们的区别是什么？

SpringBoot的核心配置文件是`application和bootstrap`配置文件。

application配置文件这个容易理解，主要用于Spring Boot项目的自动化配置。

bootstrap配置文件有以下几个应用场景：

- 使用Spring Cloud Config配置中心时，这时需要在bootstrap配置文件中添加连接到配置中心的配置属性来加载外部配置中心的配置信息；

- 一些固定的不能被覆盖的属性；

- 一些加密/解密的场景

  

### 24、SpringBoot有哪几种读取配置的方式？

Spring Boot 可 以 通 过 **[@PropertySource](https://github.com/PropertySource),[@Value](https://github.com/Value),[@Environment](https://github.com/Environment), [@ConfigurationProperties](https://github.com/ConfigurationProperties)** 来绑定变量。



### 25、Spring Boot 支持哪些日志框架？推荐和默认的日志框架是哪个？

Spring Boot 支持 Java Util Logging, Log4j2, Lockback 作为日志框架，如果你使用Starters 启动器，Spring Boot 将使用 Logback 作为默认日志框架。



### 26、保护SpringBoot应用有哪些方法？

- 在生产中使用HTTPS

- 使用Snyk检查你的依赖关系

- 升级到最新版本

- 启用CSRF保护

- 使用内容安全策略防止XSS攻击

  

### 27、SpringBoot 2.X有哪些新特性？与1.X有什么区别？

> - 配置变更
>
> - JDK版本升级
>
> - 第三方类库升级
>
> - 响应式Spring编程支持
>
> - HTTP/2支持
>
> - 配置属性绑定
>
> - 更多改进与加强
>
>   

### 88.jsp与servlet之间关系

JSP是Servlet技术的扩展，本质上就是Servlet的简易方式。JSP编译后是“类servlet”。
Servlet和JSP最主要的不同点在于：
Servlet的应用逻辑是在Java文件中，并且完全从表示层中的HTML里分离开来。
而JSP的情况是Java和HTML可以组合成一个扩展名为.jsp的文件。
JSP侧重于视图，Servlet主要用于控制逻辑
Servlet更多的是类似于一个Controller，用来做控制。



### 89.servlet生命周期

实例化：servlet容器创建servlet对象。默认创建servlet实例的时机：当我们发送servlet对应的请求时(在使用时创建)。类似单例模式中的懒加载方式。希望容器一旦启动，就自动创建servlet实例通过load-on-startup=1设置，正数数值越低优先级别越高，优先实例化

初始化：servlet实例一旦创建，就开始初始化一些参数配置，我们可以做一些参数配置，比如编码，可以在web.xml或注解中配置

就绪状态：当发送对应的servlet请求时，会调用service()方法，注意此时不会重新创建servlet实例，也不会调用init()方法

销毁状态：调用了destroy()方法后，当前servlet实例将会被标记为回收垃圾，会对servlet实例进行清除处理



### 90.什么是jdbc

Java DataBase Connectivity:(java数据库连接)是一种可以执行SQL语句的Java API,可以为多种数据库提供统一访问,是java语言编写的类和接口的组成.jdbc提供了一种基准,据此可以构建更高级的工具和接口,使开发人员能够编写数据库应用程序



### 91.jvm和tomcat参数设置

Xms是设置[内存](https://so.csdn.net/so/search?q=内存&spm=1001.2101.3001.7020)初始化的大小

-Xmx是设置最大能够使用内存的大小（最好不要超过物理内存大小）

Xmx 与PermSize的和不可超过JVM可获得的总内存

PermSize不可大于Xmx

