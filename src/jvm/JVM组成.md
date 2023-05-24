---
updateTime：2023/5/12
---

# 																															JVM

首先jvm是一套规范,只要遵守，个人也可以实现。

最常见的是oreale,HotSpot 

## JVM构成

首先是java文件编译为class文件

1. CLassLOader: 	jvm使用`CLassLoader`类加载器将class文件加载到内存，并且进行校验和初始化

2. Methods Area: 	类都放在方法区

3. Heap: 	创建的实例将放在Heap，在调用方法时会用到，程序计数器，方法栈
4. 虚拟机栈：执行的方法会进入栈，但只能有一个互动帧栈，执行完毕后弹出
5. 热点代码：频繁使用的代码会被解释权，即使编译器优化

6. CG: 	垃圾回收器会回收堆中无用的对象

本地方法接口用于调用jvm不便实现的代码，如使用*native*修饰的获取当前时间

![																																						](E:\document-notes\src\jvm\images\image-20230408151650608.png)



1. 类加载子系统(负责将类读到内存，校验类的合法性，对类进行初始化)
2. 运行时数据区(方法区/堆区/栈区/计数器,负责存储类信息，对象信息，执行逻辑)
3. 执行引擎(负责从指定地址对应的内存中读取数据然后解释执行以及GC操作)
4. 本地库接口(负责实现JAVA语言与其它编程语言之间的协同,例如调用C库)

![output](E:\document-notes\src\jvm\images\output.png)

## 程序计数器

- 每一个线程都有一个自己的计数器，字节码解释器工作时通过改变这个计数器的值来选取下一条需要执行的字节码指令。
- 在多线程的情况下，程序计数器用于记录当前线程执行的位置，以便于切换回来后知道上次执行的位置。
- 它是**程序控制流**的指示器，分支、循环、跳转、异常处理、线程恢复等基础功能都需要依赖这个计数器来完成。
- 计数器也是唯一一个不存在溢出的问题，它的生命周期随着线程的创建而创建，消亡而消亡。
- 如果当前线程执行的 是 native 方法，则这个计数器为空。



## 虚拟机栈

​	每一个线程都需要一个运行空间，**栈是线程运行需要的内存空间**，每一个栈对应一个线程并且是独自私有的。随线程创建而创建，随着线程消亡而消亡。

​	栈是 JVM 运行时数据区域的一个核心，所有的 Java 方法调用都是通过栈来实现的，除了一些 Native 关键字修饰的方法调用是通过本地方法栈实现的，在运行时也需要和其他运行时数据区域比如程序计数器配合。

>  虚拟机栈和数据结构上的栈类似，两者都是先进后出的数据结构，只支持出栈和入栈两种操作。
>
> 在一些虚拟机中，虚拟机栈和本地方法栈是合二为一的，如：hotSpot。

![image-20230519160328403](E:\document-notes\src\jvm\images\image-20230408184438202.png)



### 栈帧

每个栈帧中都拥有：局部变量表、操作数栈、动态链接、方法返回地址。

**每一个方法对应一个栈帧**，一次方法调用的时候会把对应的栈帧向下压栈，执行完毕后陆续弹出

只能有一个活动栈帧，就是当前执行的方法。



#### 局部变量表



#### 操作数栈

- 操作数栈，**主要用于保存计算过程的中间结果，同时作为计算过程中变量临时的存储空间**。



#### 动态链接

- 每一个栈帧内部都包含**一个指向运行时常量池中该栈帧所属方法的引用**。



#### 方法返回地址

- 





### 提问

- 垃圾回收是否涉及栈内存？

  不会。栈内存是方法调用产生的，方法调用结束后会弹出栈。

- 栈内存分配越大越好吗？

  不是。因为物理内存是一定的/不变的，栈内存越大，可以支持更多的递归f调用，但是可执行的线程数就会越少。

  可以通过虚拟机参数`-Xss`修改默认的1024k

- 方法的局部变量是否线程安全?

  如果方法内部的变量没有逃离方法的作用访问，它是线程安全的。因为每一个线程对应独立的栈，互不影响。

  如果是局部变量引用了对象，并逃离了方法的访问，那就要考虑线程安全问题。

  逃离的方式包括但不限于返回值，和方法参数的值传递（值= 地址值）

  简单来说如果是static修饰的变量就需要考虑线程安全问题



### 栈内存溢出	SackOverFlowError 

- 栈帧过多导致栈空间过深，从而溢出

​		如果栈帧过多，超出了栈内存的上限，将造成栈溢出。发生的场景有方法错误递归/死循环

- 栈帧过大导致溢出

  比较少见，栈帧一般为局部变量，难以超出上限。可以通过虚拟机参数`-Xss`修改默认的1024k

- 使用第三方库    一般为不得当使用

  

### 线程运行诊断

1. 使用top命令查询进程的cpu占用
2. 使用ps进一步查看线程的状态   ps H  -eo pid,tid,%cpu | grep  进程id
3. 使用JDK的 jstack  线程id
4. 一般问题后再jstack日志的最底层，如cpu占用过高，长时间无响应



## 本地方法栈

​	本地方法栈的使用是比较多的。在object里面就有native 关键字修饰的方法，它没有自己Java代码的实现，因为部分场景java无法与系统底层交互，所以它是通过本地方法接口来调用底层的c和c++来实现，常用的有获取时间等。



#### 本地方法接口

简单地讲，**一个Native Method是一个Java调用非Java代码的接囗**

本地接口的作用是融合不同的编程语言为Java所用，它的初衷是融合C/C++程序。

**为什么要使用 Native Method？**

使用Native Method主要为与Java环境外交互和与操作系统的交互。

Java使用起来非常方便，然而有些层次的任务用Java实现起来不容易，或者我们对程序的效率很在意时，问题就来了。毕竟效率和占用不是java的长处。

目前该方法使用的越来越少了，除非是与硬件有关的应用。



## 堆空间

堆空间是虚拟机所管理的内存中最大的一块。也是被所有线程共享的一块内存区域。此内存区域的唯一目的就是存放对象实例。

通常来说，通过new关键字创建的对象以及数组都会使用到堆，但通过逃逸分析技术，栈上分配等技术，对象也可能在栈分配。

堆存是在垃圾回收机制的，堆空间也是GC回收器的主战场，从内存回收角度，Java堆通常被分为新生代和老年代。

**堆是线程共享的**，需要考虑线程安全问题。

> Java堆既可以被实现成固定大小的，也可以是可扩展的，不过当前主流的Java虚拟机都是按照可 展来实现的（通过参数-Xmx和-Xms设定）



### 堆内存溢出  OutOfMemoryError

首先堆里有垃圾回收机制，当对象不被使用后作为垃圾被回收掉，怎么还可能出现这个堆内存耗尽的？

对象可以当做垃圾被回收的一个条件是这个对象不被使用，但是如果不断的产生新对象，而产生的这些新对象，仍然有人在使用他们，那么将不满足回收的条件,这样的对象达到一定的数量后将产生堆内存溢出。

>  可通过`-Xmx`来设置较小的堆内存来测试，以此暴露出可能的问题



### 堆内存诊断

1. jps 工具
   查看当前系统中有哪些 java 进程
2. jmap 工具
   查看堆内存占用情况 jmap - heap 进程id,可配合休眠使用
3. jconsole 工具
   图形界面的，多功能的监测工具，可以连续监测
4. jvisualvm 工具  更高级的图形化界面
5. **排查：**堆内存回收后大小居高不下的场景，使用jvisualvm 的堆转储快照，查看当前时间各个对象的内存占用



## 方法区

​	方法区（Method Area）主要存放的是已被虚拟机加载的类信息、常量、静态变量、编译器编译后的代码等数据。与堆空间一样，方法区是各个线程共享的内存区域。他在虚拟机启动的时候创建， 在逻辑上是堆的一部分。

方法区其实是一个概念，具体的是现实是元空间，1.7前则为永久代。

- 元空间与永久代最大的区别在于：元空间不再虚拟机设置的内存当中，而是使用本地内存。


方法区的大小决定了系统可以保存多少个类，如果系统定义了太多的类，可能导致方法区的溢出OutOfMemoryErro。

-XX:MetaspaceSize 设置初始的元空间大小。一旦触及这个水位线， Full GC 将会被触发并卸载没用的类（即这些类对应的类加载器不再存活）。

<img src="E:\document-notes\src\jvm\images\image-20230408184438201.png" alt="image-20230408184438201" style="zoom: 80%;" />

**为什么使用永久代实现方法区？**

- 当时的HotSpot虚拟机设计团队选择把收集器的分代设计扩展至方法区，这样使得 HotSpot的垃圾收集器能够像管理Java堆一样管理这部分内存，省去专门为方法区编写内存管理代码的 工作
- 这种设计导致了Java应用更容易遇到 内存溢出的问题。

**为什么使用元空间实现方法区？**

- 这就需要盘点使用永久代缺点了，如上图：永久代是被JVM管辖的区域。拥有-XX：MaxPermSize的内存上限，容易造成内存溢出。
- 元空间的位置在直接内存上，所以在使用元空间实现后，只要没有触碰到进程可用内存的上限，就不会产生内存溢出。

> **元空间的垃圾清除**
>
> 元空间的垃圾清除条件非常苛刻，并不是如同堆空间只要对象不在被使用，在下次GC扫描即回收
>
> 在元空间中整个类加载器不在被使用，且类加载器加载的所有类都被回收了才会被卸载。类加载器被回收后，元空间对应的原始数据再回被清除









Class字节码由三部分组成

- 类的基本信息
- 类的一个常量池
- 类中的一些方法定义，包含虚拟机指令

ClassLoader有主要有三个

- Bootstrap 启动类加载器
- Extension 扩展类加载器
- Application 应用程序加载器



## 常量池

常量池存在于方法区的元空间，主要分为Class文件常量池、运行时常量池，全局字符串常量池，以及基本类型包装类对象常量池。

**常量池: **  是一张表，虚拟机指令根据这张常量表的符号引用找到要执行的类名、方法名、参数类型、字面量信息。

**字面量:**   这里的"hello,world"即为字面量，或者一些基本的布尔，数字。

**运行时常量池**  

​	 常量池存在于class文件中，当运行的时候class会被加载器加载到内存中。将把常量池里面的符号`#5`替换为真实的内存地址。编译时使用符号应用的原因是因为编译时并不能知道内存地址，因此通过符号引用来代替。



使用 javap -v HelloWorld.class显示编译后的详细信息

```java
																											javap -v UserService.class
//类的基本信息
Classfile /E:/java/SE/handwritingframe/test/service/UserService.class
  Last modified 2023-4-8; size 620 bytes                          
  MD5 checksum b14ed10915f4f4c14802a5f7b675753e                   
  Compiled from "UserService.java"                                
public class com.example.handwritingframe.test.service.UserService
  minor version: 0                                                
  major version: 52                                               
  flags: ACC_PUBLIC, ACC_SUPER  
//常量池
Constant pool:  
   //符号引用：健            值              通过注释直接表示实际类容
   #1 = Methodref          #6.#20         // java/lang/Object."<init>":()V
   #2 = Fieldref           #21.#22        // java/lang/System.out:Ljava/io/PrintStream;
   #3 = String             #23            // hello,world
   #4 = Methodref          #24.#25        // java/io/PrintStream.println:(Ljava/lang/String;)V
   #5 = Class              #26            // com/example/handwritingframe/test/service/UserService
   #6 = Class              #27            // java/lang/Object
   #7 = Utf8               <init>
   #8 = Utf8               ()V
   #9 = Utf8               Code
  #10 = Utf8               LineNumberTable
  #11 = Utf8               LocalVariableTable
  #12 = Utf8               this
  #13 = Utf8               Lcom/example/handwritingframe/test/service/UserService;
  #14 = Utf8               main
  #15 = Utf8               ([Ljava/lang/String;)V
  #16 = Utf8               args
  #17 = Utf8               [Ljava/lang/String;
  #18 = Utf8               SourceFile
  #19 = Utf8               UserService.java
  #20 = NameAndType        #7:#8          // "<init>":()V
  #21 = Class              #28            // java/lang/System
  #22 = NameAndType        #29:#30        // out:Ljava/io/PrintStream;
  #23 = Utf8               hello,world
  #24 = Class              #31            // java/io/PrintStream
  #25 = NameAndType        #32:#33        // println:(Ljava/lang/String;)V
  #26 = Utf8               com/example/handwritingframe/test/service/UserService
  #27 = Utf8               java/lang/Object
  #28 = Utf8               java/lang/System
  #29 = Utf8               out
  #30 = Utf8               Ljava/io/PrintStream;
  #31 = Utf8               java/io/PrintStream
  #32 = Utf8               println
  #33 = Utf8               (Ljava/lang/String;)V
{//方法定义
  public com.example.handwritingframe.test.service.UserService();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lcom/example/handwritingframe/test/service/UserService;
  public static void main(java.lang.String[]);//方法
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=1, args_size=1          //#* 所对应的值
         0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
		 3: ldc           #3                  // String hello,world
         5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         8: return
      LineNumberTable:
        line 10: 0
        line 11: 8
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       9     0  args   [Ljava/lang/String;
}
SourceFile: "UserService.java"
```



## 串池（StringTable）

**什么是StringTable()？**

- StringTable用于存放String字符串，JDK8 StringTable使用char[]，在JDK9改用byte[]加上编码标记节约空间。
- Stirnig是Java中使用最为频繁的对象，而String被设计为不可变，所以不停的创建String对象是一件较为消耗资源的事情。而StringTable可以避免部分多次创建相同的对象，而带来的消耗。

> **为什么String被设计为不可变？**
>
> 1.保证常量池复用的可靠 2. 哈希码只需要计算一次 3. 不可变的是线程安全的。适合作为hashKey



### 概念

常量池`"a"`存在字节码文件，后加载到运行时常量池`"a`"，但这时候还是运行常量池符号，并没有成为java字符串对象`"a"`。

等待具体执行到哪一行代码的时候(懒惰加载)，将这一行的地址变为`"a"`的字符串对象。

会准备一个StringTable（底层是用哈希表实现的,），把`"a"`作为一个key，去table查找，如没有将放入，如已存在，则引用串池的地址

**总结**

- 常量池中的字符串仅是符号，只有在被用到时才会转化为对象
- 利用串池的机制，来避免重复创建字符串对象
- 字符串变量拼接的原理是StringBuilder
- 字符串常量拼接的原理是编译器优化
- 可以使用intern方法，主动将串池中还没有的字符串对象放入串池中

**String.intern()**; 尝试将对象放入串池，有则不放入，没有则放入。无论放入是否成功，都会返回串池中的字符串对象

### 题目解析

```java
String s1 = "a";
String s2 = "b";
String s3 = "ab";
String s4 = s1 + s2; //true?false  false
java中存在编译器优化，会将其优化为使用StringBuilder拼接，并且调用toStirng，而toString中使用的是new String
而new出来的对象将产生在堆，/**所以不相等的原因为对象为不在同一个地方**/，但使用equals则一样（需实现为字符比较）
s1 + s2 == new StringBuilder().append("a").append("b").tostring() new String("ab")
    
String s5 = "a" + "b"; //true?false  true
结果为true，实际为javac编译期间的一个优化，它认为他们都是常量，已经是确定的，不可能是其他值，所以直接拼接
    
	false	
    true
    true
	false
```

*注：普通声明的字符在常量池子，new的字符在堆*



### stringTable 位置

1.8的stringTable（串池）移动到了堆，StringTable使用非常频繁，所以效率（空间/时间）很重要。

而StringTable在堆上`的`回收效比永久代`的`常量池里`的`stringTable效率高。

> 如何验证StringTable在堆空间？使用大量字符导致溢出，查看溢出提示。



### StringTable垃圾回收

StringTable的字符并不是永久存在，将在内存不足的时候进行回收。



### StringTable调优

- 使用`-XX:StringTableSize=2000`将桶个数将table长度调整为大，减小hash碰撞概率。
- 考虑将使用`String.intern();`将字符串对象添加如串池，因为堆会有多个同一值对象，而串池只保存一份唯一值。



## 直接内存

- 直接内存不受 JVM 内存回收管理，也不是jvm规范的，通过unsafe回收。
- 常见于 NIO 操作时，通过存在堆中的`DirectByteBuffer`操作Native内存，用于数据缓冲区。
- 分配回收成本较高，但读写性能高。

Java不能直接操作文件管理，只能使用使用本地方法进行操作，会在系统内存中创建一个缓冲区，将数据读到系统缓冲区， 从系统缓冲区复制数据到 java 堆内存中。多出一个步骤，降低了效率。

直接内存是操作系统和 Java 代码都可以访问的一块区域，无需将代码从系统内存复制到 Java 堆内存，从而提高了效率。 

但不存在于jvm的内存区域,，所以GC不能对直接内存回收，但可以通过unsafe.freeMemory来进行释放，unsafe是jvm的类。

一般用 jvm 调优时，会静止显示的 GC:`-XX:+DisableExplicitGC  `。使`System.gc()` 无效。

- 因为`System.gc()`  是一种Full GC,会回收新生代、老年代，会造成程序回收的时间比较长，迫使用户线程暂停，影响性能

- 所以我们就通过 unsafe 对象调用 freeMemory 的方式释放内存。

虽然直接内存的分配不会受到Java堆大小的限制，但是，既然是内存，则肯定还是会受到 本机总内存的限制，还是会出现OutOfMemoryError的。

























