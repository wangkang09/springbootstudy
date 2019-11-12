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
public class ClassE {
    private ClassF classF;
    @Autowired
    public ClassE(ClassF classF) {
        this.classF = classF;
    }
}
