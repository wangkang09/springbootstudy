## Validator 

Spring 校验最关键的就是validator这个类，我们在Config中进行单例配置，或在别的地方new即可

- javaX校验，主要是调用了 validator.validate(...)方法，我们可以自己包装用在任何地方，这里我们就要new一个Validator了

- 分组校验：在使用注解的时候可以指定groups，通过@validated({group.class})指定特定的分组

- 校验顺序：@GroupSequence注解在类上，可以指定校验顺序

- 级联校验：如果类中依赖了另一个类，可以在其上加@valid，这样校验是可以级联校验

- 自定义新的校验注解：首先创建指定注解，在注解中指定校验器（可以多个），最后实现校验器即可（可分为类上校验器和属性校验器）

- 跨参数校验：自定义注解的一种，注解在方法上，会提取方法的参数，最后在校验器中做处理，这样就可以跨参数校验了。该方法所在类需要使用@Validated使注解生效

- 使用了 自定义的 StudentValidator 后，因为绑定了这个校验器，所以默认的被覆盖了，但不常用

- 后置校验：spring默认开启了方法级别的后置校验


### @validated 与 @valid
- validated 是 valid 的包装
- 提供了分组，时序等功能
















## MessageInterpolator

```java
public class Car {

    @NotNull
    private String manufacturer;
    @Size(
            min = 2,
            max = 14,
            message = "The license plate '${validatedValue}' must be between {min} and {max} characters long"
    )
    private String licensePlate;
    @Min(
            value = 2,
            message = "There must be at least {value} seat${value > 1 ? 's' : ''}"
    )
    private int seatCount;
    @DecimalMax(
            value = "350",
            message = "The top speed ${formatter.format('%1$.2f', validatedValue)} is higher " +
                    "than {value}"
    )
    private double topSpeed;
    @DecimalMax(value = "100000", message = "Price must not be higher than ${value}")
    private BigDecimal price;
    public Car(
            String manufacturer,
            String licensePlate,
            int seatCount,
            double topSpeed,
            BigDecimal price) {
        this.manufacturer = manufacturer;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.topSpeed = topSpeed;
        this.price = price;
    }
    //getters and setters ...
}
```
- Message：其中，{}里面取的是注解里参数的值，${}里取的是ValidationMessages.properties中的值，且可以有表达式
- vaild：当参数类型是 entity时，用 @vaild 修饰即可，后面紧跟result接收错误
- vailded：当参数类型是 基本类型时，用 @vailded 修饰该类，才可在参数前用注解，这是需要全局异常处理来拦截异常





http://hibernate.org/validator/documentation/