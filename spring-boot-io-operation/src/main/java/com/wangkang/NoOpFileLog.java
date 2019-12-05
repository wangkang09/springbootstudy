package com.wangkang;

import java.nio.ByteBuffer;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 20:41 2019/12/5
 * @Modified By:
 */
public class NoOpFileLog extends FileLog {

    @Override
    public void write(ByteBuffer byteBuffer){
    }
}
