package com.wangkang;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 19:28 2019/12/5
 * @Modified By:
 */
public abstract class FileLog implements Closeable {
    protected Closeable closeable;

    abstract public void write(ByteBuffer byteBuffer);

    public void close() {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeable = null;
            }
        }
    }
}
