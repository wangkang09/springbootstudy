package com.wangkang;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 19:38 2019/12/2
 * @Modified By:
 */
public class Util {

    public static int getPageSize() throws NoSuchFieldException, IllegalAccessException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe)f.get(null);

        int pageSize = unsafe.pageSize();
        System.out.println("Page size: " + pageSize);
        return pageSize;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        System.out.println(getPageSize());
    }

    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static int pageSize = -1;
    private static Unsafe unsafe() {
        return unsafe;
    }

    public static int pageSize() {
        if (pageSize == -1)
            pageSize = unsafe().pageSize();
        return pageSize;
    }
}
