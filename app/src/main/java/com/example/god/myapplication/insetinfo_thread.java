package com.example.god.myapplication;

import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GOD on 2016/8/29.
 */
public class insetinfo_thread extends Thread{

    public String SERVICE_NAMESPACE = "http://tempuri.org/";
//    public String SERVICE_URL = "http://10.148.82.102:8011/Service1.asmx";
    private String methodName = "insertCargoInfo ";   //设置方法名
    private SoapObject result;
    private ListView listView;
    private MainActivity activity;
    List<String> List_result;
    String insetinfo_result;


    private Handler handler; //设置消息，通知主线程进行相关操作

    String Cname=null;  //webservice 需要的参数
    String Cnum=null;

    public insetinfo_thread(String methodName, Handler handler){   // 构造方法，传入方法名和消息
        super();
        this.methodName=methodName;
        this.handler=handler;
    }

    public void setCname(String Cname) {
        this.Cname = Cname;
    }  //设置方法对应的参数

    public void setCnum(String Cnum) {
        this.Cnum = Cnum;
    }





    public  List<String> getProvinceList() {
        // 调用 的方法
        String methodName = "insertCargoInfo";
        // 创建HttpTransportSE传输对象
        HttpTransportSE ht = new HttpTransportSE(Data.getSERVICE_URL());
        try {
            ht.debug = true;
            // 使用SOAP1.1协议创建Envelop对象
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            // 实例化SoapObject对象
            SoapObject soapObject = new SoapObject(SERVICE_NAMESPACE,methodName);
            soapObject.addProperty("Cname",Cname);
            soapObject.addProperty("Cnum",Cnum);
            envelope.bodyOut = soapObject;
            // 设置与.NET提供的webservice保持较好的兼容性
            envelope.dotNet = true;

            // 调用webservice
            ht.call(SERVICE_NAMESPACE + methodName, envelope);

            if (envelope.getResponse() != null) {
                // 获取服务器响应返回的SOAP消息
                SoapObject result = (SoapObject) envelope.bodyIn;
                String resuly_back ;
                resuly_back = result.getProperty(0).toString();//true
                insetinfo_result= resuly_back;
            }
        } catch (SoapFault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run(){


        getProvinceList();
        Message msg=new Message();
        msg.what=0x12345;
        handler.sendMessage(msg);
    }

    public String parseProvinceOrCity(String resuly_back) {
//
        String result_array[]=resuly_back.split(":");
        return result_array[1];

    }

    public String getList_result(){

        return insetinfo_result;
    }


}
