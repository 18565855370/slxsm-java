### 代理模式
1、静态代理，在编译期间就预先确定了代理和被代理类的关系，在目标类和调用类之间会做一些其他的工作，如日志打印等操作。
2、动态代理（GDK动态代理和CGLIB动态代理）
2.1、GDK动态代理有两个非常重要的类和接口，分别是InvocationHandler和Proxy,InvocationHandler是给动态代理类实现的，
负责处理被代理对象的操作。而Proxy是用来创建动态代理类实例对象的。GDK动态代理必须对接口代理，目标类必须要实现接口。
2.2、CGLIB动态代理也是被用来做动态代理，动态的生成一个代理类的自类，自类重写要代理类所有不是final的方法，
在子类中拦截所有父类方法的调用，
3、AOP动态代理，采用的是面相切面的代理，@AspectJ注解标识类为一个代理类，
@Before、@After、@AfterReturn、@AfterThroable、@Around注解注释方法操作

### code
本工程演示了四种代理模式

[具体参考点我哦!](https://blog.csdn.net/ShuSheng0007/article/details/80864854#_11)