#Web Application context and Root context. 
##Task 1.1. Lifecycle and definition.

Web Application context extended Application Context which is designed to work with the standard javax.servlet.ServletContext so it's able to communicate with the container.

Beans, instantiated in WebApplicationContext will also be able to use ServletContext if they implement ServletContextAware interface

There are many things possible to do with the ServletContext instance, for example accessing WEB-INF resources (xml configs and etc.) by calling the getResourceAsStream() method. Typically, all application contexts defined in web.xml in a servlet Spring application are Web Application contexts, this goes both to the root webapp context and the servlet's app context.

Also, depending on web application context capabilities may make your application a little harder to test, and you may need to use MockServletContext class for testing.

###Difference between servlet and root context:
Spring allows you to build multilevel application context hierarchies, so the required bean will be fetched from the parent context if it's not present in the current application context. In web apps as default there are two hierarchy levels, root and servlet contexts: 
 ![root context](https://i1.wp.com/www.dineshonjava.com/wp-content/uploads/2017/02/ApplicationContext-vs-WebApplicationContext.png?w=530&ssl=1)

This allows you to run some services as the singletons for the entire application (Spring Security beans and basic database access services typically reside here) and another as separated services in the corresponding servlets to avoid name clashes between beans. For example, one servlet context will be serving the web pages, and another will be implementing a stateless web service.

This two-level separation comes out of the box when you use the spring servlet classes: to configure the root application context you should use context-param tag in your web.xml
```$xslt
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>
        /WEB-INF/root-context.xml
            /WEB-INF/applicationContext-security.xml
    </param-value>
</context-param>
```
(the root application context is created by ContextLoaderListener which is declared in web.xml
```<listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener> 
```
and servlet tag for the servlet application contexts
```
<servlet>
   <servlet-name>myservlet</servlet-name>
   <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
   <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>app-servlet.xml</param-value>
   </init-param>
</servlet>
```
Please note that if init-param will be omitted, then spring will use myservlet-servlet.xml in this example.

The WebApplicationContext is an extension of the plain ApplicationContext that has some extra features necessary for web applications. It differs from a normal ApplicationContext in that it is capable of resolving themes (see Using themes), and that it knows which Servlet it is associated with (by having a link to the ServletContext). The WebApplicationContext is bound in the ServletContext, and by using static methods on the RequestContextUtils class you can always look up the WebApplicationContext if you need access to it.

By the way servlet and root context are both webApplicationContext:
![webApplicationContext](https://docs.spring.io/spring/docs/current/spring-framework-reference/images/mvc-context-hierarchy.png)

###About WebApplicationContext:
WebApplicationContext is a web aware application context i.e. it has servlet context information. A <b>single</b> web application can have <b>multiple WebApplicationContext</b> and each <b>Dispatcher servlet</b> (which is the front controller of Spring MVC architecture) is associated with a <b>WebApplicationContext</b>. 

The WebApplicationContext configuration file *-servlet.xml is specific to a DispatcherServlet. And since a web application can have more than one dispatcher servlet configured to serve multiple requests, there can be more than one webApplicationContext file per web application.

In addition to the standard Spring bean scopes singleton and prototype, there are three additional scopes available in a web application context:

* request - scopes a single bean definition to the lifecycle of a single HTTP request; that is, each HTTP request has its own instance of a bean created off the back of a single bean definition 
* session - scopes a single bean definition to the lifecycle of an HTTP Session 
* application - scopes a single bean definition to the lifecycle of a ServletContext

This article from [here](https://stackoverflow.com/questions/11708967/what-is-the-difference-between-applicationcontext-and-webapplicationcontext-in-s)

---
Let’s begin from what the Root Context is. There is a good article at [baeldung.com](https://www.baeldung.com/spring-web-contexts)

Every Spring webapp has an associated application context that is tied to its lifecycle: the root web application context.

The root web application context is simply a centralized place to define shared beans.

---
according to spring documentation, for many applications, having a single WebApplicationContext is simple and suffices. It is also possible to have a context hierarchy where one root WebApplicationContext is shared across multiple DispatcherServlet (or other Servlet) instances, each with its own child WebApplicationContext configuration.

The root WebApplicationContext typically contains infrastructure beans, such as data repositories and business services that need to be shared across multiple Servlet instances. Those beans are effectively inherited and can be overridden (that is, re-declared) in the Servlet-specific child WebApplicationContext, which typically contains beans local to the given Servlet. The following image shows this relationship:
![webApplicationContext](https://docs.spring.io/spring/docs/current/spring-framework-reference/images/mvc-context-hierarchy.png)

If an application context hierarchy is not required, applications can return all configuration through getRootConfigClasses() and null from getServletConfigClasses(). (<i>from me – inside child AbstractAnnotationConfigDispatcherServletInitializer</i>)
