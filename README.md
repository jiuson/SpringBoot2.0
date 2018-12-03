<ul>
  <h2></h2>
  <li></li>  
</ul>
<ol>
  <h2><em></em></h2>
  <li></li>  
</ol>

# SpringBoot2.0
SpringBoot2.0  学习、整合其他组件模块，每个模块使用自己独立的pom.xml文件，parent的pom.xml中只配置公共依赖的信息

<ol>
  <h2><em>springboot2.0-yipinketang-mybatis-annotation 这个模块添加了DevTools、Web、MyBatis、LomBok等maven依赖</em></h2>
  主要演示学习通过SpringBoot+MyBatis注解的方式搭建一个简单的SpringBoot微服务工程
  <li>DevTools——Spring提供的开发组件，在开发web项目时可以做到更改项目文件之后自动重启工程——热启动： https://blog.csdn.net/u011063151/article/details/80406922</li>
  <li>LomBok——通过注解的方式减少模块化的代码（自动生成set、get、toString、equals、hashcode等方法）编写量，加快编码速度</li>
  <li>
    application.properties文件中需要添加一行关于mybatis entity类的配置：</br>
    mybatis.type-aliases-package=com.yipinketang.app.domain</br>
    接着定义mapper接口，然后在接口中声明方法，在方法上面使用Mybatis的注解编写需要的执行的sql</br>
    最后通过使用@MapperScan("com.yipinketang.app.mapper")或@Mapper注解，让Spring管理Bean</br>
    详情参考代码！
  </li>
</ol>

<ol>
  <h2><em>springboot2.0-yipinketang-mybatis-xml 这个模块添加了DevTools、Web、MyBatis、LomBok等maven依赖</em></h2>
  主要演示学习搭建一个简单的SpringBoot微服务工程
  <li>DevTools——Spring提供的开发组件，在开发web项目时可以做到更改项目文件之后自动重启工程——热启动： https://blog.csdn.net/u011063151/article/details/80406922</li>
  <li>LomBok——通过注解的方式减少模块化的代码（自动生成set、get、toString、equals、hashcode等方法）编写量，加快编码速度</li>
  <li>
    application.properties文件中需要添加一行关于mybatis entity类的配置：</br>
    mybatis.config-locations=classpath:mybatis/mybatis-config.xml #mybatis基础配置文件地址</br>
    mybatis.mapper-locations=classpath:mybatis/mapper/.xml #mybatis实体类映射文件地址</br>
    接着定义mapper接口,接口中值声明方法名，该方法名要与对应的映射文件中的sql id一致，这样MyBatis会自动根据方法名找到对应的sql语句，这里需要注意的是：每个映射文件的namespace要指向对应mapper的包路径</br>
    最后通过使用@MapperScan("com.yipinketang.app.mapper")或@Mapper注解，让Spring管理Bean</br>
    详情参考代码！
  </li>
</ol>

<p>总结：orm框架的本质原则是减少编程中与数据库的编码操作，发展到现在基本就只剩下两家独大——hibernate和mybatis。这两家的风格也是很鲜明的。hibernate主张以全编程的方式操作数据库，代码中不参杂sql语句，jpa规范就是参照hibernate制定的；而mybatis强调的是可以灵活动态的调试sql。据个人使用的体验来讲：操作数据库如果能够熟练的写sql，当然是比写代码要流畅的多，而且调试起来也很方便容易，写完sql之后直接在数据库中执行一下就知道写的对与否；相反用纯代码的方式来写，简单的crud还好说，一两行代码就能搞定，但是如果是复杂的多表连接查询，代码写起来可谓是流汗不止，好不容易写完了，还战战兢兢的怕写的不对，然后大部分的时间都是在调试中，而且jpa在执行代码中的数据库操作的时候会自动执行很多条语句，这样势必在效率上会有损失，所以结论就是：对于业务比较简单的场景，使用hibernate还是很不错的；而对于比较复杂的场景，特别是涉及到多表操作的，还是选择mybatis更加合适，会节省不少时间！</p>

<ul>
  <p>DataSource：</p>
  <li></li>  
</ul>

<ul>
  <p>mybatis事务管理：</p>
  <li>http://note.youdao.com/noteshare?id=f6fc391a6428859398f1ea06fbaad1cc</li>  
</ul>

<ul>
  <p>mybatis缓存、二级缓存：</p>
  <li>http://note.youdao.com/noteshare?id=85bc412f094d1f443a744958ca254c16</li>  
</ul>

<ul>
  <p>折腾第一个vue工程：</p>
  <li>https://blog.csdn.net/u011063151/article/details/84325429</li>
</ul>

<ul>
  <p>Nginx——windows安装：</p>
  <li>今天计划搭建前后端分离的项目，刚开始选择的前端技术是vue，所以网上搜罗各种博客开始操作，中途又犹豫了觉得前端不是主要的方向，故而又想换成bootstrap，或者干脆把前端的东西一起放在后端工程算了，各种搜罗之后还是打算保持初衷：SpringBoot+vue+nginx——时间和精力全耗费在犹豫上，不如专心做一件事</li>
  <li>http://note.youdao.com/noteshare?id=47c138bd989e9f784a449329b59aa672</li>  
  <li>https://blog.csdn.net/u011063151/article/details/84324198</li>
</ul>

<ul>
  <p>Nginx——配置vue前端静态资源和SpringBoot接口服务：</p>
  <li>http://note.youdao.com/noteshare?id=ebd3a831b07241a98b1d7fe86bd82dd6</li>  
  <li>https://blog.csdn.net/u011063151/article/details/84325228</li>
</ul>

<ul>
  <h2>SpringBoot+MyBatis+redis：基于xml文件配置的、将redis作为MyBatis的二级缓存</h2>
  <li>module：SpringBoot2.0-yipinketang-mybatis-xml-redisAsCache</li>  
  <li>http://note.youdao.com/noteshare?id=1500bbe67ef5120369416278e2e4469c</li> 
  <li>https://blog.csdn.net/u011063151/article/details/84341512</li>
</ul>

<ul>
  <h2>SpringBoot+MyBatis+redis：基于注解配置的、将redis作为MyBatis的二级缓存</h2>
  <li>module：SpringBoot2.0-yipinketang-mybatis-annotation-redisAsCache</li>  
  <li>@CacheNamespace(implementation = com.yipinketang.app.cache.MyBatisRedisCache.class)//添加二级缓存处理类，类似xml文件的<cache>标签</li>
</ul>

<ul>
  <h2>SpringBoot Actuator：SpringBoot监控</h2>
  <li>springboot2.0-yipinketang-jpa-actuator工程主要演示SpringBoot JPA + Actuator相关配置</li>  
  <li>SpringBoot JPA内置实现框架是Hibernate</li>
  <li>Actuator主要为SpringBoot应用提供监控和管理能力</li>
  <li>SpringBoot jpa将findOne(id)的返回值改成了Optional<T>，这是Java 8 的新特性</li>
  <li>今天看Actuator的时候看到了一个InitializingBean这个接口？</li>
  <li>http://note.youdao.com/noteshare?id=37261ffb786d93e4aaa73c0f0d67c118</li>
</ul>


<ul>
  <h2>SpringBoot AOP</h2>
  <li>springboot2.0-yipinketang-aop 模块主要演示SpringBoot AOP使用</li>
  <li>http://note.youdao.com/noteshare?id=7bef5658f1d06374b35f90d9b0c13efe</li>  
</ul>

<ul>
  <h2>SpringBoot 拦截器（编辑中）</h2>
  <li>http://note.youdao.com/noteshare?id=8971c447144a120d387f1d2a4537ea80</li>  
</ul>

































</br></br></br></br></br></br></br><p style="font-family:arial;color:red;font-size:20px;">持续更新中......欢迎加入,QQ群:807597909</p>
