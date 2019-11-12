package com.wangkang.constructDepends;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 22:57 2019/5/10
 * @Modified By:
 */
//@Component
public class ClassD {

    private ClassE classE;
    @Autowired
    public ClassD(ClassE classE) {
        this.classE = classE;
    }
}
