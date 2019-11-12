package com.wangkang.controller;

import org.springframework.stereotype.Controller;

/**
 * Description: 和 ZtestOnBeanNameController、ZtestOnBeanTypeController，一起测试 @ConditionalOnBean(name = "onBeanNameController") 的效果
 * 结果：@ConditionalOnBean(name = "onBeanNameController") 只会去匹配 name 为 onBeanNameController 的 beanDefinition
 * @author wangkang
 * @date: 2019-07-25
 */
@Controller(value = "myBeanName")
public class OnBeanNameController {
}
