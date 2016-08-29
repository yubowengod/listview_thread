package com.example.god.myapplication;

/**
 * Created by GOD on 2016/8/29.
 */
public class Data {
//    private static String a ="feiyangxiaomi";

    private static String SERVICE_URL = "http://10.148.83.238:8011/Service1.asmx";
    public static String getSERVICE_URL() {
        return SERVICE_URL;
    }

    public static void setA(String SERVICE_URL) {
        Data.SERVICE_URL = SERVICE_URL;
    }
}
