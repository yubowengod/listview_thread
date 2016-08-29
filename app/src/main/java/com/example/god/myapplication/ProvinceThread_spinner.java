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
public class ProvinceThread_spinner extends Thread{

    public String SERVICE_NAMESPACE = "http://tempuri.org/";
    public String SERVICE_URL = "http://10.148.82.102:8011/Service1.asmx";
    private String methodName = "selectAllCargoInfor";   //设置方法名
    private SoapObject result;
    private ListView listView;
    private MainActivity activity;
    List<String> List_result;


    private Handler handler; //设置消息，通知主线程进行相关操作

    public ProvinceThread_spinner(String methodName, Handler handler){   // 构造方法，传入方法名和消息
        super();
        this.methodName=methodName;
        this.handler=handler;
    }


    public void setListView(ListView listView) {
        this.listView = listView;
    }  //设置方法对应的参数


    public  List<String> getProvinceList() {
        // 调用 的方法
        String methodName = "selectAllCargoInfor";
        // 创建HttpTransportSE传输对象
        HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
        try {
            ht.debug = true;
            // 使用SOAP1.1协议创建Envelop对象
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            // 实例化SoapObject对象
            SoapObject soapObject = new SoapObject(SERVICE_NAMESPACE,methodName);
            envelope.bodyOut = soapObject;
            // 设置与.NET提供的webservice保持较好的兼容性
            envelope.dotNet = true;

            // 调用webservice
            ht.call(SERVICE_NAMESPACE + methodName, envelope);

            if (envelope.getResponse() != null) {
                // 获取服务器响应返回的SOAP消息
                SoapObject result = (SoapObject) envelope.bodyIn;
                SoapObject detail = (SoapObject) result.getProperty(methodName + "Result");
                // 解析服务器响应的SOAP消息
                List_result=parseProvinceOrCity(detail);
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
        msg.what=0x1234;
        handler.sendMessage(msg);
    }

    public List<String> parseProvinceOrCity(SoapObject detail) {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < detail.getPropertyCount(); i++) {
            // 解析出每个省份
            result.add(detail.getProperty(i).toString().split(",")[0]);
        }
        return result;
    }

    public List<String> getList_result(){

        return List_result;
    }


}
