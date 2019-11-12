https://github.com/mockito/mockito
### 遗留问题
- 用mockMVC做单元测试时，没有启动filter，应该不是@ServletCompentScan的位置问题，回来看看mockMVC方法说明
- https://www.cnblogs.com/duanxz/p/3539198.html  
- mockMVC测试时，只启动了spring context，filter等其它组件需要自己注入！
- 可以通过TestRestTemplate来进行接口测试！

### 常用方法说明
- 调用MockMvc.perform(RequestBuilder requestBuilder)后将得到ResultActions，通过ResultActions完成如下三件事
- ResultActions andExpect(ResultMatcher matcher) ：添加验证断言来判断执行请求后的结果是否是预期的
- ResultActions andDo(ResultHandler handler) ：添加结果处理器，用于对验证成功后执行的动作，如输出下请求/结果信息用于调试
- MvcResult andReturn() ：返回验证成功后的MvcResult；用于自定义验证/下一步的异步处理