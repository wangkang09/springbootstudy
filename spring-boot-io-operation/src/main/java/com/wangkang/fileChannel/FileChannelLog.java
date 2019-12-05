package com.wangkang.fileChannel;

import com.wangkang.FileLog;
import com.wangkang.FileLogManage;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 19:12 2019/12/5
 * @Modified By:
 */
public class FileChannelLog implements FileLog {

    private FileChannel fileOutputChannel;
    private String fileName;

    public FileChannelLog(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        fileOutputChannel = new FileOutputStream(fileName,true).getChannel();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        //一个线程负责异步写文件
        FileLogManage.getExecutorService().submit(()->fileOutputChannel.write(byteBuffer));
    }

    public void close() {
        if (fileOutputChannel != null) {
            try {
                fileOutputChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                fileOutputChannel = null;
            }
        }
    }
}
