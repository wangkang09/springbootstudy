package com.wangkang.RandomAccessFile;

import com.wangkang.FileLog;
import com.wangkang.FileLogManage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 21:17 2019/12/5
 * @Modified By:
 */
public class RandomAccessFileLog extends FileLog {

    private RandomAccessFile randomAccessFile;
    private String fileName;

    public RandomAccessFileLog(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        randomAccessFile = new RandomAccessFile(fileName,"w");
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        FileLogManage.getExecutorService().submit(()-> {
            try {
                randomAccessFile.write(byteBuffer.array());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
