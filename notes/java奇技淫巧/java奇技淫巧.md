# java

#### 1、excel 公式生成sql实现快速导入

通过excel生成sql语句
有的时候业务部门直接甩过来一个excel表格让我们插入或者更新到数据库中。插入还好说，只要字段对应，就可以插入，但是更新呢？所以我们需要一个其他的操作方式，将excel生成想要的sql语句。

具体操作步骤

1.写好插入、更新语句，将指定位置替换成列序号。
① insert table into (name,age,sex) values(‘张三’,‘11’,‘1’);
② insert table into (name,age,sex) values(’"&A2&"’,’"&A2&"’,’"&A2&"’);
2.在excel中选择公式，在选择CONCATENATE将之前写好的语句放入CONCATENATE括号中。记得一定要有双引号，否则不是公式。
=CONCATENATE(“insert table into (name,age,sex) values(’”&A2&"’,’"&A2&"’,’"&A2&"’);")
3.鼠标点击生成的语句那个单元框的右下角向下拖动，即可生成



![image-20230413095207559](C:\Users\DELL\Desktop\iooi\md\java奇技淫巧\img\image-20230413095207559.png)



