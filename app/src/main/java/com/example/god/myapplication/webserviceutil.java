package com.example.god.myapplication;

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
 * Created by GOD on 2016/8/28.
 */
public class webserviceutil {

//    final static String SERVICE_NS = "http://tempuri.org/";
//    final static String SERVICE_URL =  "http://10.148.81.128/Service1.asmx";




    // 定义webservice的命名空间
    public static final String SERVICE_NAMESPACE = "http://tempuri.org/";
    // 定义webservice提供服务的url
    public static final String SERVICE_URL = "http://10.148.81.128:8011/Service1.asmx";

    // 调用远程webservice获取省份列表
    public static List<String> getProvinceList() {
        // 调用 的方法
        String methodName = "selectAllCargoInfor";
        // 创建HttpTransportSE传输对象
        HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
        try {
            ht.debug = true;
            // 使用SOAP1.1协议创建Envelop对象
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            // 实例化SoapObject对象
            SoapObject soapObject = new SoapObject(SERVICE_NAMESPACE,
                    methodName);
            envelope.bodyOut = soapObject;
            // 设置与.NET提供的webservice保持较好的兼容性
            envelope.dotNet = true;

            // 调用webservice
            ht.call(SERVICE_NAMESPACE + methodName, envelope);
            if (envelope.getResponse() != null) {
                // 获取服务器响应返回的SOAP消息
                SoapObject result = (SoapObject) envelope.bodyIn;
                SoapObject detail = (SoapObject) result.getProperty(methodName
                        + "Result");
                // 解析服务器响应的SOAP消息
                return parseProvinceOrCity(detail);
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



    // 解析省份或城市
    public static List<String> parseProvinceOrCity(SoapObject detail) {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < detail.getPropertyCount(); i++) {
            // 解析出每个省份
            result.add(detail.getProperty(i).toString().split(",")[0]);
        }
        return result;
    }


}
