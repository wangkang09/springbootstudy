package com.wangkang;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import javax.annotation.PostConstruct;

/**
 * Description: 通过 import 导入，如果是 继承了 ImportSelector 接口，将会获取到 selectImports 方法返回的 类名，把里面的类，重新做 processImport() 来处理 @Import 里面的类
 *
 * @author wangkang
 * @date: 2019-07-24
 */
public class ImportSeletorMy implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {SelectorConfig.class.getName()};
    }

    @PostConstruct
    public void init() {
        System.out.println("初始化 ImportSeletorMy ....");
    }
}
