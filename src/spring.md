---
createDate:2023/4/28
---

## Spring两大核心技术是什么？

### **什么是spring？**

- spring是一个开源轻量级的框架，提供了IOC和AOP这两个核心功能。



### **IOC（控制反转）** 

IOC是Spring最核心的点，并且贯穿始终，但IOC更多的是控制反转的一种编程思想。

在传统的开发中，只能通过`User u = new User()`来创建对象，这样导致依赖关系比较复杂，耦合度高。

控制反转就是从自己创建管理bean  到  把创建和管理 bean 的过程转移给了第三方。

**在Spring框架中实现控制反转的是Spring IoC容器(BeanFactory)，其具体实现就是由容器来控制对象的生命周期和业务对象之间的依赖关系，而不是像传统方式（new对象）中由代码来直接控制**。

IOC将传统自己新建的对象的这个过程进行了一个反转，也就是说，我们只需要把设计好的对象（Component）交给 IOC 容器控制，然后在需要用到目标对象的时候，直接从容器中去获取（Autowried）。

**IOC带来的优势**

- IOC提供的对象默认为单列，当想要维持单列的时候我没不再需要手动编写单列代码。
- 不必关系对象创建的细节，可以将目光聚焦于业务。
- 降低了使用资源双方的依赖程度，也就是我们说的**耦合度**。
- **缺点**：如果使用JDK动态代理来创建，那么是使用反射来创建对象，所以在效率上会有些损耗。初学者适应了new ，创建变得复杂，可能难以理解。

> 使用JDK代理在运行效率上有损耗，使用CGLib代理在创建上的性能有损耗	

**控制正转和反转**

- 正转：如传统应用程序是由我们自己在对象中主动控制去直接获取依赖对象
- 反转：反转则是由容器来帮忙创建及注入依赖对象，对象只是被动的接受依赖对象
- 哪些方面反转了？依赖对象的获取被反转了

#### SpringIOC

对于IOC最重要的就是容器，在Spring中实现控制反转的是IoC容器

- 实现BeanFactory接口的简单容器
- 实现ApplicationContext接口的高级容器



#### **DI（依赖注入）**

上面说了IOC更多的是一种思想，而具体的落地实现则是DI依赖注入

依赖注入DI，将一个程序依赖的资源注入到合适的地方，也就是将BeanFactory容器的对象通过DI注入到合适的位置。

IOC和DI其实是同一个概念，只是从不同的角度描述罢了（**IoC是一种思想，而DI则是一种具体的技术实现手段**）。简单的说：IoC是目的（创建对象），DI是手段（怎么获取外部对象）

#### **注入方式**

实现具体的依赖注入方式有三种

- 构造器注入:
- Set注入:
- 接口注入：已被淘汰
- 自动注入：不过在使用上更加倾向于使用@Autowird来进行实例变量的自动注入

###  **AOP(面向切面)** 

AOP（Aspect Orient Programming）面向切面编程，是对OOP面向对象的一种补充，AOP不同于OOP面向对象的关注点是对象，而AOP关注的是切面。

AOP可以在不惊动方法原有的基础上对其进行一个增强，且这个增强，不会影响到原本的方法。 

AOP可以拦截指定的方法并且对方法增强，而且无需侵入到业务代码中，使业务与非业务处理逻辑分离。

AOP并不是spirng独创的技术，不过在spring中使用非常便捷，它的亮点在于AOP和SpringIOC之间的紧密结合。

#### Aop的应用场景？

Aop的应用场景可以有 日志，安全，检查

个人的应用不是特别常用，而在一些框架中，不少都会使用AOP来进行一个增强。

- SpringSecuirty 权限检查
- SpringValidation 参数检查
- Spring  事务

#### Spring 对 AOP的实现？

在spring中使用AOP非常简单，通过注解定义即可

- @Before(execution) 在方法执行前拦
-  @AfterReturning(execution) 在方法正常return结束后拦截
-  @AfterThrowing(execution)  在方法抛出异常时拦截 
- @After(execution) 在方法结束后拦截,无论正常结束还是异常结束 
- @**Around**(execution)唯一可以使ProceedingJoinPoint参数来控制流程的advice,在 方法执行前拦截,可以在切面逻辑中手动释放拦截,且可以在其后加入逻辑代码,该代 码段会在方法执行后执行，  

#### Aop是如何实现的？

Spring 的 AOP 实现原理其实很简单，就是通过动态代理实现的。

Spring AOP 采用了两种混合的实现方式：JDK 动态代理和 CGLib 动态代理。

- JDK动态代理：Spring AOP的首选方法。 每当目标对象实现一个接口时，就会使用JDK动态代理。目标对象必须实现接口
- CGLIB代理：如果目标对象没有实现接口，则可以使用CGLIB代理。
  - Cglib的实现是在字节码的基础上的，并且使用了开源的ASM读取字节码，对类实现增强功能的。

> **如何回答？**
>
> 什么是IOC ，控制反转和正转，Spring对IOC如何实现，什么是DI，DI的实现方式，
>
> 什么是AOP，AOP的应用场景，Spring中的AOP ，AOP原理







## springmvc的九大组件有哪些？

1.**HandlerMapping**
根据request找到相应的处理器。因为Handler（Controller）有两种形式，一种是基于类的Handler，另一种是基于Method的Handler（也就是我们常用的）

2.HandlerAdapter
调用Handler的适配器。如果把Handler（Controller）当做工具的话，那么HandlerAdapter就相当于干活的工人

3.HandlerExceptionResolver
对异常的处理

4.ViewResolver
用来将String类型的视图名和Locale解析为View类型的视图

5.RequestToViewNameTranslator
有的Handler（Controller）处理完后没有设置返回类型，比如是void方法，这是就需要从request中获取viewName

6.LocaleResolver
从request中解析出Locale。Locale表示一个区域，比如zh-cn，对不同的区域的用户，显示不同的结果，这就是i18n（SpringMVC中有具体的拦截器LocaleChangeInterceptor）

7.ThemeResolver
主题解析，这种类似于我们手机更换主题，不同的UI，css等

8.MultipartResolver
处理上传请求，将普通的request封装成Multip	artHttpServletRequest

9.FlashMapManager
用于管理FlashMap，FlashMap用于在redirect重定向中传递参数



## 讲讲SpringMVC工作流程

1. 首先是浏览器发出http请求，这个请求会被转接到DispatcherServlet前端控制器
2. 前端控制器（DispatcherServlet）会将这个请求转交给，HandlerMapping处理器来进行一个路径映射，它会返回一个执行链，以此来确定某一个Controller。
3. 前端控制器（DispatcherServlet）会根据HandlerMapping返回的执行链路，来找到能够执行的处理器适配器(HandlerAdaptor)
4. 处理器适配器 (HandlerAdaptoer) 会调用对应的具体的 Controller
5. 后端控制器（Controller）会返回一个ModelAndView对象（Model 是返回的数据对象，View 是个逻辑上的视图）HandlerAdaptor
6. 处理器适配器（HandlerAdaptor）再次将ModelAndView对象返回给DispatcherServlet，至此，业务处理完毕
7. 前端控制器（DispatcherServlet）调用ViewResolver，将ModelAndView中的视图名称封装为视图对象，然后返回
8.  DispatcherServlet 调用视图对象，让其自己 (View) 进行渲染（将模型数据填充至视图中），形成响应对象 (HttpResponse)
9. 前端控制器 (DispatcherServlet) 响应 (HttpResponse) 给浏览器，展示在页面上。

![img](https://upload-images.jianshu.io/upload_images/5220087-3c0f59d3c39a12dd.png?imageMogr2/auto-orient/strip|imageView2/2/format/webp)

而在前后端分离的情况下，返回的ModelAndView为空，并且HandlerAdapter会把方法返回值放到响应体中

所以解析视图等其他操作不在进行

>1. 前端控制器转发给处理器映射路径，返回一个执行链
>2. 前端控制器转发处理适配器
>3. 处理适配器转发给具体的handler，也就是我们编写的controller
>4. 依次返回到前端控制器
>5. 解析视图，渲染视图。注：前后分离不在需要解析、渲染视图



## @Autowired和@Resource注解的区别？

Autowried和Resource都可以完成自动注入，主要区别在与默认根据名字和类型装配的一个区别

**@Autowired**

- Autowired在归属上属于spring，默认根据类型匹配，也只能根据类型匹配，但可以和Qualifier配合完成名字匹配

**@Resource**

- Resource在归属上属于javax，默认根据名字匹配，在次根据类型匹配



## Spring如何解决循环依赖问题?

**什么是依赖循环？**

- 依赖循环和线程死锁类型，都是二个以上，并且相互持有。
- 但spring依赖循环还可以会存在自我依赖。

spring通过**三级缓存**在解决依赖循环问题



## 简述一下spirng事务

**什么是事务？**

- 事务是一组操作的集合，符合ACID特性

Spring框架为事务管理提供了一个一致的抽象，在不同的事务API（JDBC、JPA、 MyBatis）中具有一致的编程模型。

Spring对事务支持提供了编程式和声明式两种。

**声明式事务**

- Spring支持声明式事务管理，与编程式事务相比，声明式事务使用更加简单，往往只需要在类或者方法上标注 事务注解即可。

- 声明式事务一般通过添加@Transactional来实现，默认回滚发生的运行时异常，可通过rollbackFor指定。
- `@Transactional` 的作用范围
  1. **方法** ：推荐将注解使用于方法上，不过需要注意的是：**该注解只能应用到 public 方法上，否则不生效。**
  2. **类** ：如果这个注解使用在类上的话，表明该注解对该类中所有的 public 方法都生效。
  3. **接口** ：对该接口的所有实现类生效，不推荐在接口上使用，不过添加的代价也不大。
- `@Transactional`的参数
  1. rollbackFor：用于指定能够触发事务回滚的异常类型，并且可以指定多个异常类型。
  2. propagation：事务的传播行为，默认值为 REQUIRED
  3. isolation：事务的隔离级别，默认值采用 DEFAULT
  4. timeout 超时时间以及readOnly是否只读

**编程式事务**

- 通过 `TransactionTemplate`或者`TransactionManager`手动管理事务，但推荐使用声明式事务

**具体实现**

- Spring框架的声明式事务管理是通过Spring面向方面编程（AOP）实现的，**底层依赖数据库的事务功能（需要数据库支持事务）**。

- 而AOP 又是使用动态代理实现的。如果目标对象实现了接口，默认情况下会采用 JDK 的动态代理，如果目标对象没有实现了接口,会使用 CGLIB 动态代理。

**`@Transactional` 的使用注意事项总结**

- `@Transactional` 注解只有作用到 public 方法上事务才生效，不推荐在接口上使用；
- 避免同一个类中调用 `@Transactional` 注解的方法，这样会导致事务失效；
- 正确的设置 `@Transactional` 的 `rollbackFor` 和 `propagation` 属性，否则事务可能会回滚失败;
- 被 `@Transactional` 注解的方法所在的类必须被 Spring 管理，否则不生效；
- 底层使用的数据库必须支持事务机制，否则不生效；
- .....



## BeanFactory和Application Context有什么区别？

BeanFactory是Spring 实现IOC容器的核心，而ApplicationContext继承于BeanFactory。

按照一些继承的特性来说，ApplicationContext提供了比BeanFactory更多的功能。

**BeanFactory**

1. BeanFactory是延迟加载，即只有在使用到某个Bean时`调用getBean()`，才对该Bean进行加载实例化，可能无法发现一些问题

**ApplicationContext**

1. ApplicationContext它是在容器启动时，一次性创建了所有的Bean，这样，在容器启动时，我们就可以发现Spring中存在的配置错误，这样有利于检查所依赖属性是否注入，并且通过预载入单实例bean ，确保当需要的时候就可以直接只用，无需等待。
2. 由于在BeanFactory基础上增加了功能，所以ApplicationContext 占用内存空间大，当程序的配置bean特别多时，程序启动慢。
3. 继承MessageSource，因此支持国际化。
4. 提供了统一的资源文件访问方式。
5. 可以同时加载多个配置文件。

所以通常情况下更加体检倾向于使用ApplicationContext。



## Spring中 Bean作用域？

spirng中的bean提供了5种不同的作用域

我们可以通过 @Scope 注解来设置 Bean 的作用域，直接输入作用域名字或使用常量。

1. **singleton**（单例）
   - 使用该属性定义Bean时，IOC容器仅创建一个Bean实例，IOC容器每次返回的是同一个Bean实例。
2. **prototype**（多列）
   -  使用该属性定义Bean时，IOC容器可以创建多个Bean实例，每次返回的都是一个新的实例。
3. **session**（同一个Session）
   - 该属性仅用于HTTP Session，同一个Session共享一个Bean实例  。不同Session使用不同的实例。
4. **request**（每一次请求）
   - 该属性仅对HTTP请求产生作用，使用该属性定义Bean时，每次HTTP请求都会创建一个新的Bean。
5. **application**（全局作用域）
   - 该属性仅用于HTTP Session，同session作用域不同的是，所有的Session共享一个Bean实例。

注意后 3 种作用域只适用于 Spring MVC 框架。



## Spring 中事务的传播行为有哪些？

**什么是事务传播？**

所谓的事务传播行为，就是多个声明了事务的方法相互调用的时候，这个 事务应该如何传播。

简单来说，A和B都开启了事物，那么B事务该怎么做？这个怎么做就是事务传播行为。

**在spirng中的事务传播行为有七种**，分别是：

- REQUIRED：默认的 Spring 事物传播级别，如果当前存在事务，则加入这个事 务，如果不存在事务，就新建一个事务。 
- REQUIRE_NEW：不管是否存在事务，都会新开一个事务，新老事务相互独立。 外部事务抛出异常回滚不会影响内部事务的正常提交。 
- NESTED：如果当前存在事务，则嵌套在当前事务中执行。如果当前没有事务， 则新建一个事务，类似于 REQUIRE_NEW。 
- SUPPORTS：表示支持当前事务，如果当前不存在事务，以非事务的方式执行。 
- NOT_SUPPORTED：表示以非事务的方式来运行，如果当前存在事务，则把当 前事务挂起。 
- MANDATORY：强制事务执行，若当前不存在事务，则抛出异常. 
- NEVER：以非事务的方式执行，如果当前存在事务，则抛出异常。 Spring 事务传播级别一般不需要定义，默认就是 PROPAGATION_REQUIRED， 除非在嵌套事务的情况下需要重点了解。



## MyBatis的一级缓存二级缓存?

MyBatis有2种缓存机制，**分别称之为一级缓存和二级缓存，一级缓存基于SqlSession，默认开启的，二级缓存基于namespace默认关闭**，当应用了缓存机制后，在执行查询时，会暂时将查询结果保存下来，以便于下次查询时直接返回此前的缓存，以提高查询效率！但数据会在更改之后失效。

**一级缓存**

- MyBatis的一级缓存也称之为会话缓存，是基于`SqlSession`的，默认是开启的，不能关闭。
- 同一个SqlSession会在`insert`  `update`  `delete`  或者手动清除之后，缓存会失效，需要重新查询。

**什么是SqlSession？**

- 使用MyBatis开启一次和数据库的会话，MyBatis会创建出一个SqlSession对象表示一次数据库会话

**二级缓存**

- MyBatis的二级缓存也可以称之为`namespace`缓存，是 Mapper 级别的缓存，多个 SqlSession 去操作同一个 Mapper 的 sql 语句，多个 SqlSession 可以共用二级缓存，所以二级缓存是跨 SqlSession 的，并且二级缓存默认是关闭的。

开启二级查询后查询顺序为 `二级缓存`   >  `一级缓存`  > `数据库`



## MyBatis #{}和${}的区别

1. #{}是预编译处理，${}是字符串替换。
2. Mybatis 在处理#{}时，会将 sql 中的#{}替换为?号，调用 PreparedStatement 的 set 方法来赋值；
3. Mybatis 在处理${}时，就是把${}替换成变量的值。
4. 使用#{}可以有效的防止 SQL 注入，提高系统安全性。



## Spring Bean 生命周期的执行流程

spring的Bean大概分为五个阶段，

1. 创建前准备 
2. 创建实例
3. 依赖注入
4. 容器缓存
5. 销毁实例



## 什么是SpirngBoot？

Spring Boot 是 Spring 开源组织下的子项目，是为简化spring开发而生的。

**springBoot产生的原因？**

- 在使用spring开发的时候往往需要对mybatis，springMvc等框架进项配置，这些配置和业务无关且就有一定通用性，也就是每一个spirng项目需要配置基本一模一样的设置。
- 还需要安装额外的tomcat容器，对新手非常折磨。
- 需要管理复杂的maven版本依赖，一不小心造成严重的依赖冲突
- 对bean进行复杂的xml文件配置  *spring4.0之后不在需要*
- 需要导入指定的配置文件

而springBoot去除了这些繁琐的各种配置，只需要一个*SpringBootApplication*注解

**springBoot优点**

1. 开箱即用，避免spring繁琐的配置。
2. 内嵌 Tomcat、Jetty 或 Undertow 作为 Web 容器，无需部署 WAR 文件。
3. 没有代码生成，也不需要XML配置。
4. 提供了一些生产级的特性，如指标、健康检查、外部化配置等
5. 提供了一些“start启动器”依赖，简化了构建配置，避免大量的 Maven 导入和各种版本冲突。

**springBoot有两个重要点**

- 开箱即用
- 约定优于配置

> springBoot是约定优于配置是一种软件设计的范式



## SpringBoot自动配置原理?

> springBoot是如何实现自动装配的？实现自动配置。

### SpringBootApplication注解

`SpringBoot` 的诞生就是为了简化 `Spring` 中繁琐的 `XML` 配置

`springBoot`一切来源于`SpringBoot`的启动类，main方法上会有一个注解**@SpringBootApplication**

```java
/**
 * Indicates a {@link Configuration configuration} class that declares one or more
 * {@link Bean @Bean} methods and also triggers {@link EnableAutoConfiguration
 * auto-configuration} and {@link ComponentScan component scanning}. This is a convenience
 * annotation that is equivalent to declaring {@code @Configuration},
 * {@code @EnableAutoConfiguration} and {@code @ComponentScan}.
 *
 * @author Phillip Webb
 * @author Stephane Nicoll
 * @author Andy Wilkinson
 * @since 1.2.0
 */
@Target(ElementType.TYPE)  //SpringBootApplication注解可以在类、接口（包括注释类型）或枚举声明
@Retention(RetentionPolicy.RUNTIME) //指定该注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在
@Documented //这个注解只是用来标注生成javadoc的时候是否会被记录
@Inherited //指定该注解在类上使用时，可以被子类继承
@SpringBootConfiguration //套壳的Configuration，类似Service和Component的区别 
@EnableAutoConfiguration
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
//ComponentScan默认会扫描当前包和所有子包，和xml配置自动扫描效果一样，@Filter是排除了两个系统类
public @interface SpringBootApplication {}
```

**@SpringBootApplication是一个复合注解，主要包含三个重要元注解**

- `@SpringBootConfiguration`：	里面就是@Configuration，标注当前类为配置类，其实只是做了一层封装改了个名字而已
  - `@Configuration ` ：  标注在某个类上，表示这是一个 springboot的`配置类`。可以向容器中注入组件。 
- `@ComponentScan `： 配置用于 Configuration 类的组件扫描指令。
- **`@EnableAutoConfiguration`** ：  springBoot自动配置原理依赖于该注解，引入了AutoConfigurationImportSelector，该类中 的方法会扫描所有存在META-INF/spring.factories的jar包。

如上就是springBoot基本的自动配置原理。

> **SpringBootApplication注意事项**
>
> - @SpringBootApplication 注解应该放在主类上，也就是启动类上，通常位于项目的根包下，这样可以保证 @ComponentScan 注解能够扫描到所有的组件和配置类。
> - @SpringBootApplication 注解可以通过 exclude 或 excludeName 属性来排除一些不需要的自动配置类，从而减少启动时间和内存占用
> - @SpringBootApplication 注解可以通过 scanBasePackages 或 scanBasePackageClasses 属性来指定需要扫描的包或类，从而覆盖默认的扫描规则。



### 导入自动配置类

#### 深入@EnableAutoConfiguration

之前我们了解了spirngBoot依赖于@EnableAutoConfiguration注解进行自动配置的基本信息，接下来我们对该注解注解继续深入

```java
/**
 * Enable auto-configuration of the Spring Application Context, attempting to guess and
 * configure beans that you are likely to need. Auto-configuration classes are usually
 * applied based on your classpath and what beans you have defined. For example, if you
 * have {@code tomcat-embedded.jar} on your classpath you are likely to want a
 * {@link TomcatServletWebServerFactory} (unless you have defined your own
 * {@link ServletWebServerFactory} bean).
 * <p>
 * When using {@link SpringBootApplication @SpringBootApplication}, the auto-configuration
 * of the context is automatically enabled and adding this annotation has therefore no
 * additional effect.
 * <p>
 * Auto-configuration tries to be as intelligent as possible and will back-away as you
 * define more of your own configuration. You can always manually {@link #exclude()} any
 * configuration that you never want to apply (use {@link #excludeName()} if you don't
 * have access to them). You can also exclude them via the
 * {@code spring.autoconfigure.exclude} property. Auto-configuration is always applied
 * after user-defined beans have been registered.
 * <p>
 * The package of the class that is annotated with {@code @EnableAutoConfiguration},
 * usually via {@code @SpringBootApplication}, has specific significance and is often used
 * as a 'default'. For example, it will be used when scanning for {@code @Entity} classes.
 * It is generally recommended that you place {@code @EnableAutoConfiguration} (if you're
 * not using {@code @SpringBootApplication}) in a root package so that all sub-packages
 * and classes can be searched.
 * <p>
 * Auto-configuration classes are regular Spring {@link Configuration @Configuration}
 * beans. They are located using the {@link SpringFactoriesLoader} mechanism (keyed
 * against this class). Generally auto-configuration beans are
 * {@link Conditional @Conditional} beans (most often using
 * {@link ConditionalOnClass @ConditionalOnClass} and
 * {@link ConditionalOnMissingBean @ConditionalOnMissingBean} annotations).
 *
 * @author Phillip Webb
 * @author Stephane Nicoll
 * @since 1.0.0
 * @see ConditionalOnBean
 * @see ConditionalOnMissingBean
 * @see ConditionalOnClass
 * @see AutoConfigureAfter
 * @see SpringBootApplication
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage  //2.向springBoot自动注入配置类
@Import(AutoConfigurationImportSelector.class) //1.扫描需要被自动配置的类
public @interface EnableAutoConfiguration {}
```



**@EnableAutoConfiguration的作用是什么？**

> 查看顺序：AutoConfigurationImportSelector类   >  DeferredImportSelector接口  > ImportSelector接口  > selectImports方法 >
>
> AutoConfigurationImportSelector类的selectImport具体实现方法

该注解主要用于扫描和配置可能需要的 bean。先提一嘴，这个可能的bean在spring.factories中配置。

1. 可以看到，该注解使用@Import导入了一个类文件**AutoConfigurationImportSelector**，该类实现了DeferredImportSelector接口
2. 而DeferredImportSelector接口继承于ImportSelector类



#### selectImports方法

ImportSelector接口定义了一个重要方法selectImports

```java
public interface ImportSelector {
	/**
	 * Select and return the names of which class(es) should be imported based on
	 * the {@link AnnotationMetadata} of the importing @{@link Configuration} class.
	 * @return the class names, or an empty array if none
	 */
	String[] selectImports(AnnotationMetadata importingClassMetadata);
}
```

我们主要观看ImportSelector接口的下级DeferredImportSelector接口，**但由于DeferredImportSelector为接口**，所以真正的逻辑代码在DeferredImportSelector的下级AutoConfigurationImportSelector的selectImports方法中实现。

```java
public class AutoConfigurationImportSelector implements DeferredImportSelector, BeanClassLoaderAware,
		ResourceLoaderAware, BeanFactoryAware, EnvironmentAware, Ordered {
            
	private static final String[] NO_IMPORTS = {};
     
	/**
     * 如果在springBoot配置文件中开启了自动配置
     * 那么就获取需要被引入的的自动配置
     */    
   	@Override
	public String[] selectImports(AnnotationMetadata annotationMetadata) {
		if (!isEnabled(annotationMetadata)) {
			return NO_IMPORTS;
		}
		AutoConfigurationEntry autoConfigurationEntry = getAutoConfigurationEntry(annotationMetadata);
		return StringUtils.toStringArray(autoConfigurationEntry.getConfigurations());
	}
                     
}
```

#### **什么是如果在springBoot配置文件中开启了自动配置？**

这个就是我们常用的application.yml

```yaml
server:
  port: 9091

spring:
  autoconfigure:
    exclude: org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
#  datasource:
#    url: jdbc:mysql://localhost:3306/mall_ams?useUnicode=true&characterEncoding
#    username: root
#    password: root
```

这样在导入jdbc后不需要配置datasource，但mybatis依赖于datasource，仍然报错。

开启DataSourceAutoConfiguration后报错为

- `Error creating bean with name 'adminMapper' defined in`

关闭DataSourceAutoConfiguration后报错为

- `Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured`

同理也可以在@*SpringBootApplication*上关闭

```java
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
```

> 当我们没有配置的时候，默认就是开启自动配置的

> 查看顺序：this.getAutoConfigurationEntry    >>>    this.getCandidateConfigurations    >>> SpringFactoriesLoader.loadFactoryNames    >>>  this.loadFactoryNames >>> this.**loadSpringFactories**



#### 核心方法getAutoConfigurationEntry

接下里继续解析selectImports方法里面的this.getAutoConfigurationEntry，**这个是核心方法**

```java
protected AutoConfigurationEntry getAutoConfigurationEntry(AnnotationMetadata annotationMetadata) {
	// 判断是否开启自动配置
   if (!isEnabled(annotationMetadata)) {
       //如果没有返回一个空的AutoConfigurationEntry
      return EMPTY_ENTRY;
   }
   // 获取@EnableAutoConfiguration注解的属性
   AnnotationAttributes attributes = getAttributes(annotationMetadata);
   // 重点：使用SpringFactoriesLoader从所有的spring.factories文件中获取需要配置的bean全限定名列表，
   List<String> configurations = getCandidateConfigurations(annotationMetadata, attributes);
   // 去重
   configurations = removeDuplicates(configurations);
   // 获取注解中exclude或excludeName排除的类集合
   Set<String> exclusions = getExclusions(annotationMetadata, attributes);
   // 检查被排除类是否可以实例化，是否被自动配置所使用，否则抛出异常
   checkExcludedClasses(configurations, exclusions);
   // 去除被排除的类
   configurations.removeAll(exclusions);
   // 使用spring.factories配置文件中配置的过滤器对自动配置类进行过滤
   configurations = getConfigurationClassFilter().filter(configurations);
   // 抛出事件
   fireAutoConfigurationImportEvents(configurations, exclusions);
    //将需要的自动配置 和 被排除的自动配置，一同封装到AutoConfigurationEntry对象
   return new AutoConfigurationEntry(configurations, exclusions);
}
```



>  List<String> configurations 包含所有需要被自动配置的类的，是一个全限定路径名称。
>
> configurations 包含的类在随后都将被注入到IOC容器，完成自动配置。
>
>  List<String> configurations结构 如图：

![image-20230518101644472](images\image-20230518101644472.png)



继续深入，很明显，该方法getAutoConfigurationEntry的重点方法是this.getCandidateConfigurations方法，我们来分析一下

```java
	/**
	 * Return the auto-configuration class names that should be considered. By default
	 * this method will load candidates using {@link SpringFactoriesLoader} with
	 * {@link #getSpringFactoriesLoaderFactoryClass()}.
	 * @param metadata the source metadata
	 * @param attributes the {@link #getAttributes(AnnotationMetadata) annotation
	 * attributes}
	 * @return a list of candidate configurations
	 */
	protected List<String> getCandidateConfigurations(
        AnnotationMetadata metadata,AnnotationAttributes attributes) {
        //调用SpringFactoriesLoader.loadFactoryNames，获得自动配置的信息
		List<String> configurations = SpringFactoriesLoader.loadFactoryNames(
            getSpringFactoriesLoaderFactoryClass(),getBeanClassLoader());
        
        //如果自动配置的信息是否为空，则抛出异常
		Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories. If you " + "are using a custom packaging, make sure that file is correct.");
        
		return configurations;
	}
```



#### 什么是AnnotationMetadata？

AnnotationMetadata用来访问指定类上的注解。

`AnnotationMetadata` 存在两个实现类分别为 `StandardAnnotationMetadata`与 `AnnotationMetadataReadingVisitor`。前者主要使用 Java 反射原理获取元数据，而 后者 使用 ASM 框架获取元数据。

![image-20230518100836530](images\image-20230518100836530.png)



#### FactoryNames

继续深入SpringFactoriesLoaderload类的FactoryNames方法

```java
	/**
	 * Load the fully qualified class names of factory implementations of the
	 * given type from {@value #FACTORIES_RESOURCE_LOCATION}, using the given
	 * class loader.
	 * <p>As of Spring Framework 5.3, if a particular implementation class name
	 * is discovered more than once for the given factory type, duplicates will
	 * be ignored.
	 * @param factoryType the interface or abstract class representing the factory
	 * @param classLoader the ClassLoader to use for loading resources; can be
	 * {@code null} to use the default
	 * @throws IllegalArgumentException if an error occurs while loading factory names
	 * @see #loadFactories
	 */
	public static List<String> loadFactoryNames(Class<?> factoryType, @Nullable ClassLoader classLoader) {
		ClassLoader classLoaderToUse = classLoader;
		if (classLoaderToUse == null) {
			classLoaderToUse = SpringFactoriesLoader.class.getClassLoader();
		}
		String factoryTypeName = factoryType.getName();
		return loadSpringFactories(classLoaderToUse).getOrDefault(factoryTypeName, Collections.emptyList());
	}
```

该方法大概是：使用指定的类加载器从所有 “META-INF/spring.factory”路径下把**`EnableAutoConfiguration对应的的Bean值添加到容器中`**

具体的实现在下方的this.loadSpringFactories。

总之AutoConfigurationEntry类用于返回导入@Configuration类的AnnotationMetadata。

> 从 Spring Framework 5.3 开始，如果多次发现给定工厂类型的特定实现类名，则重复项将被忽略。



#### 什么是spring.factories？

spring.factories是一个Spring Boot提供的SPI机制，用于在类路径中的多个JAR文件中加载和实例化特定类型的工厂类。spring.factories文件必须是Properties格式，其中键是接口或抽象类的完全限定名，值是实现类名的逗号分隔列表。

**=后面的路径都作为容器中的一个组件，被添加到IoC 容器中，从而实现Spring Boot 的自动配置。**

**列如：**

```properties
# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.mybatis.spring.boot.autoconfigure.MybatisLanguageDriverAutoConfiguration,\
org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration
```

org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration是一个类的全限定名称，可直接查看

**MybatisAutoConfiguration这个相当于我们以前手动配置的mybatisConf**配置类

```java
// spring配置mybatis简单示例
public class MyBatisConfig {
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource source){
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setTypeAliasesPackage("SpringQuickStart.MybatiesBean.domain"); //扫描路径
        sessionFactoryBean.setDataSource(source);
        return sessionFactoryBean;
}
```

也就是说我们以前**在spring中需要编写的mybatis配置类，在我们导入mybatis依赖的时候，mybatis已经编写好，不用我们重复编写**。

1. mybatis将自己编写的配置类的一个路径放入到spring.factories
2. springBoot扫描spring.factories文件，就能找到mybatis编写的配置类具体路径
3. 然后springBoot自动装配这些具体的配置类

但业务千变万化，配置不是完全一样的，如数据源是不确定的，还需要手动配置。

这就是Spring Boot 的约定优于配置，通俗的说，SpringBoot提供了一套默认的配置，可以直接使用，这个默认的配置不合适就自己覆盖

自动配文件一般在spring-boot-autoconfigure包下

![image-20230518000044716](images\image-20230518000044716.png)



#### 如何创建spring.factories？

创建spring.factories的方法是：

1. 在你的项目中创建一个类，用@Configuration注解标记，并定义你想要自动配置的bean。例如：

```java
@Configuration
public class MyAutoConfiguration {

  @Bean
  public MyService myService() {
    return new MyService();
  }
}
```

1. 在你的项目的资源目录下，创建一个META-INF/spring.factories文件，用Properties格式指定你的配置类的完全限定名，以EnableAutoConfiguration为键。例如：

```properties
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.example.MyAutoConfiguration
```

1. 将你的项目打包成一个jar文件，并发布到Maven仓库或者其他地方。
2. 在其他需要使用你的自动配置的项目中，添加你的jar文件作为依赖。Spring Boot会在启动时扫描spring.factories文件，并加载其中配置的类

如上，springBoot就扫描完了并且使用类加载器加载了需要被自动配置的配置类，接下里需要注入到springIOC容器

> 小总结:
>
> 1. `AutoConfigurationImportSelector`类是`ImportSelector`的实现类，实现了`selectImports()`方法。`selectImports()`方法又调用`getAutoConfigurationEntry()`方法从spring.factories文件中读取配置类的全限定名列表，并进行过滤，最终得到需要自动配置的类全限定名列表。
> 2. 随后spring根据需要自动配置的类全限定名列表，将这些自动注入到容器，完成自动配置



### 注册自动配置包

而@EnableAutoConfiguration注解的核心是@AutoConfigurationPackage，也就是将之前扫描的类注入到容器

```java
/**
 * Registers packages with {@link AutoConfigurationPackages}. When no {@link #basePackages
 * base packages} or {@link #basePackageClasses base package classes} are specified, the
 * package of the annotated class is registered.
 *
 * @author Phillip Webb
 * @since 1.3.0
 * @see AutoConfigurationPackages
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(AutoConfigurationPackages.Registrar.class)
public @interface AutoConfigurationPackage {}
```

该AutoConfigurationPackage注解上的前四个都是java的基本注解，不在过多解释

该注解的重点是@Import(AutoConfigurationPackages.Registrar.class)



**AutoConfigurationImportSelector和AutoConfigurationPackages的区别**

AutoConfigurationImportSelector 和 AutoConfigurationPackages 是 Spring Boot 自动配置的两个重要的类，它们分别用于导入自动配置类和注册自动配置包。

- AutoConfigurationImportSelector 是一个实现了 ImportSelector 接口的类，它会根据类路径中的依赖和条件注解（如 @ConditionalOnClass、@ConditionalOnMissingBean 等）来选择合适的配置类，并将它们返回给 @Import 注解，从而导入到容器中。这些配置类都是以 AutoConfiguration 结尾的，位于 org.springframework.boot.autoconfigure 包下，或者在 META-INF/spring.factories 文件中声明。

- AutoConfigurationPackages 是一个抽象类，它有一个内部类 Registrar，实现了 ImportBeanDefinitionRegistrar 接口，它会根据 @EnableAutoConfiguration 注解所在的类的包名，来注册一个名为 org.springframework.boot.autoconfigure.AutoConfigurationPackages 的 BeanDefinition，该 BeanDefinition 的属性值就是包名的集合。这样，其他的自动配置类就可以通过这个 BeanDefinition 来获取需要扫描的包名。



> **总结1：**
>
> 1. spirngBoot依赖于*@SpringBootApplication*注解完成自动配置
> 2. *@SpringBootApplication*依赖于*@EnableAutoConfiguration*
> 3. @EnableAutoConfiguration依赖于AutoConfigurationImportSelector类，该类需要扫描所有需要被自动配置的类
>    - 扫描完成后自然需要注入到ioc容器
>
> 4. @EnableAutoConfiguration依赖于@*AutoConfigurationPackage*
> 5. @AutoConfigurationPackage依赖于AutoConfigurationPackages.Registrar类部类
>
> **总结2：**
>
> 1. springBoot使用AutoConfigurationImportSelector类扫描在spring.factories文件中的配置类
> 2. springBoot使用AutoConfigurationPackages类将之前扫描的类注入到ioc容器





## Spring Boot 的约定优于配置，你的理解 是什么？

约定优于配置是一种软件设计的范式，它的核心思想是减少软件开发人员对于配置项的维护，从而让开发人员更加聚焦在业务逻辑上

- Spring Boot 就是约定优于配置这一理念下的产物，springBoot去除了spring繁琐的xml配置。
- 在spring的web开发中，需要做一些和业务无关，并且需要配置一次的工作，比如：管理jar依赖，xml维护，部署到容器。
- 而在 Spring Boot 中，我们不需要再去做这些繁琐的配置，去除一些必要的配置之外，如：数据库链接等等，Spring Boot 已经自动帮我们完成了，这就是约定由于配置思想的体现。
- 在个性化配置上：通过扫描约定路径下的 spring.factories 文件来识别配置类，来实现不同的配置。

> 约定优于配置也可以说是**约定先于配置**，这样好理解一些，springBoot已经提供了默认配置，我们可以配置个性化的值。





## 如何理解 Spring Boot 中的 Starter？

starter是springBoot的核心技术之一，也是一个约定大于配置的一个体现，starter可以简化配置。

- Starter 组件会把对应功能的所有 jar 包依赖全部导入进来，避免了开发者自己去 引入依赖带来的麻烦。
- Starter是启动依赖，简单来说Starter可以使得我们不在需要关系依赖版本，只需要指定SpringBoot的版本即可。
- Spring Boot 里面的这些特性，都是为了让开发者在开发基于 Spring 生态下的企 业级应用时，只需要关心业务逻辑。

> starter也是约定大于配置的一个体现



























 



