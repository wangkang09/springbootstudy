package com.wangkang;

import java.nio.ByteBuffer;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 19:28 2019/12/5
 * @Modified By:
 */
public interface FileLog {
    void write(ByteBuffer byteBuffer);
    void close();
}
