package com.example.dell.day05.utls;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

/**
 * Created by Dell on 2017/2/21.
 */

public class StreamUtils {
    // 第1中把 输入流装换成 String 格式的方式
    public static String streamToString(InputStream in) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int length;
        try {
            while ((length = in.read(bytes)) != -1) {
                baos.write(bytes, 0,length);
                baos.flush();
            }
            baos.close();
            return new String(baos.toByteArray(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 第 2中把 输入流转成 String 的方式
    String parseStream(InputStream in) {
        InputStreamReader inReader = new InputStreamReader(in);
        BufferedReader read = new BufferedReader(inReader);
        StringWriter writer = new StringWriter();
        String str;
        try {
            while ((str = read.readLine()) !=null) {
                writer.write(str);
            }
            read.close();
            writer.close();
            return writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
