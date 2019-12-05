package com.wangkang.normal;

import com.wangkang.FileLog;
import com.wangkang.FileLogManage;
import com.wangkang.Util;
import sun.misc.Unsafe;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 19:28 2019/12/5
 * @Modified By:
 */
public class BufferedFileLog implements FileLog {

    private BufferedOutputStream outputStream;
    private byte[] buffer;
    private int bufferSize = 4096;
    private String fileName;

    public BufferedFileLog(String fileName, int bufferSize) throws FileNotFoundException {
        this.fileName = fileName;
        this.bufferSize = bufferSize;
        buffer = new byte[bufferSize];
        outputStream = new BufferedOutputStream(new FileOutputStream(fileName,true));
    }

    public BufferedFileLog(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.bufferSize = Util.pageSize() * 4;
        buffer = new byte[bufferSize];
        outputStream = new BufferedOutputStream(new FileOutputStream(fileName,true));
    }

    @Override
    public void write(ByteBuffer byteBuffer){
        FileLogManage.getExecutorService().submit(()-> {
            try {
                outputStream.write(byteBuffer.array());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void close() {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                outputStream = null;
            }
        }
    }

}
