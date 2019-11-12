## 需要@ServletComponentScan开启filter/servlet/listener
- 也可以在相关类上，直接使用@Component注解注入

## webflux默认的静态文件路径
- 使用ResourceWebHandler这个类定义的 
- /static (or /public or /resources or /META-INF/resources) in the classpath

## 自定义静态文件路径
- overriding the addResourceHandlers method.
- spring.webflux.static-path-pattern=/resources/**
- 以上默认的是 = /**
- spring.resources.static-locations,使用这个定义，可以方便的自定义不同的文件路径
- index.html就可以在任何你自定义的地方了

## webflux不依赖servlet规范，不能以war包发布，不能用src/main/webapp路径

## @WebFilter注解目前还没有Order来控制顺序，可以通过name字母顺序

## @WebListener注解注明监听器，@WebServlet注解注明Servlet类

## filter servlet 执行顺序
before filter -> servlet -> after filter
 
## filter filter 执行顺序(注意首字母顺序)
before afilter -> before bfilter -> after bfilter -> after afilter

