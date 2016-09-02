package com.example.god.myapplication;

import org.kobjects.base64.Base64;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

/**
 * Created by GOD on 2016/8/30.
 */
public class ImageToString
{

    // public static byte[] GetImageStr(String imagepath) throws Exception
    // {
    // FileInputStream fs = new FileInputStream(imagepath);
    // ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    // byte[] buffer = new byte[1024];
    // int len = 0;
    // while (-1 != (len = fs.read(buffer)))
    // {
    // outStream.write(buffer, 0, len);
    // }
    // outStream.close();
    // fs.close();
    // return outStream.toByteArray();
    // }

//    public static String GetImageStr(String imagepath) throws Exception

    public static String GetImageStr(String imagepath) throws Exception
    {
        FileInputStream fs = new FileInputStream(imagepath);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int count = 0;
        while ((count = fs.read(buffer)) >= 0)
        {
            outStream.write(buffer, 0, count);
        }
        String uploadBuffer = new String(Base64.encode(outStream.toByteArray()));
        outStream.close();
        fs.close();
        return uploadBuffer;
    }
}
