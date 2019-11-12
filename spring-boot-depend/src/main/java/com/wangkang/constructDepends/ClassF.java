package com.wangkang.constructDepends;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 22:58 2019/5/10
 * @Modified By:
 */
//@Component
public class ClassF {
    private ClassD classD;
    @Autowired
    public ClassF(ClassD classD) {
        this.classD = classD;
    }
}
