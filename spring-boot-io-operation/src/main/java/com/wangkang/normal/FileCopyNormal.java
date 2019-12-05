package com.wangkang.normal;

import com.wangkang.FileLog;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 13:34 2019/12/2
 * @Modified By:
 */
public class FileCopyNormal implements FileLog {
    public void fileCopyNormal(File fromFile, File toFile) throws FileNotFoundException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(fromFile));
            outputStream = new BufferedOutputStream(new FileOutputStream(toFile));
            byte[] bytes = new byte[1024];
            int i;
            //读取到输入流数据，然后写入到输出流中去，实现复制
            //read() 方法，是阻塞的
            while ((i = inputStream.read(bytes)) != -1) {
                outputStream = switchOutputStreamFile(outputStream);
                //buffer 为 1024，写满之后才刷内部的 buf 数组
                outputStream.write(bytes, 0, i);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private OutputStream switchOutputStreamFile(OutputStream outputStream) {
        //根据策略更换输出源
        if (shouldSwitch) {
            try {
                outputStream.close();
                return new BufferedOutputStream(new FileOutputStream(switchFile));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outputStream;
    }

    private volatile static boolean shouldSwitch;
    private volatile static String switchFile;

    @Override
    public void write(ByteBuffer byteBuffer) {

    }
}
