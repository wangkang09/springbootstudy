### 加密与解密
- 主要就是将object做相应的解密加密即可，之后的json转换可以不用管

### 自定义异常
- 首先，可以创建一个异常代码枚举类，里面存放code,msg,level
- 这个异常代码类继承了一个异常代码接口，里面做了一些规范，和一些默认异常枚举
- 其次，创建自定义异常类，将异常枚举类放入构造器

### 全局异常处理
- 通过@ControllerAdvice和@ExceptionHandler注解达到分类异常处理效果
- 实现HandlerExceptionResolver
- 配置SimpleMappingExceptionResolver类，实现特定错误显示特定页面