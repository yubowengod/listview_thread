package com.example.god.myapplication;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemSelectedListener;
        import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
        import android.widget.TextView;
        import android.app.Activity;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    public Spinner sp_province, sp_city;
    public SoapObject detail;
    public EditText edit_test,edit_test1;
    public TextView txt_today, txt_tomorrow, txt_afterday, txt_current,txt_test;
    public ImageView img_today1, img_today2, img_tomorrow1, img_tomorrow2,
            img_afterday1, img_afterday2;

    public Button btn_test;
    public String Cname_Cnum=null;


    public List<String> provinces;
    public ListView listview;

//    public static final String SERVICE_NAMESPACE = "http://tempuri.org/";
//    // 定义webservice提供服务的url
//    public static final String SERVICE_URL = "http://10.148.81.128:8011/Service1.asmx";





    private void initControls() {
        edit_test = (EditText) findViewById(R.id.edit_test);
        edit_test1 = (EditText) findViewById(R.id.edit_test1);
        txt_test = (TextView) findViewById(R.id.txt_test);
        btn_test= (Button) findViewById(R.id.btn_test);
        listview = (ListView) findViewById(R.id.listview);
        sp_province = (Spinner) findViewById(R.id.sp_province);
        sp_city = (Spinner) findViewById(R.id.sp_city);
        txt_today = (TextView) findViewById(R.id.weatherToday);
        txt_tomorrow = (TextView) findViewById(R.id.weatherTomorrow);
        txt_afterday = (TextView) findViewById(R.id.weatherAfterday);
        txt_current = (TextView) findViewById(R.id.weatherCurrent);
        img_today1 = (ImageView) findViewById(R.id.todayWhIcon1);
        img_today2 = (ImageView) findViewById(R.id.todayWhIcon2);
        img_tomorrow1 = (ImageView) findViewById(R.id.tomorrowWhIcon1);
        img_tomorrow2 = (ImageView) findViewById(R.id.tomorrowWhIcon2);
        img_afterday1 = (ImageView) findViewById(R.id.afterdayWhIcon1);
        img_afterday2 = (ImageView) findViewById(R.id.afterdayWhIcon2);
    }


    public Handler mHandler=new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch(msg.what)
            {
                case 1:
                    int i=0;
                    Log.e("ok1", " is saled by "+i++);
                    break;
                case 2:
                    Log.e("ok1", "2");
                    break;
                case 3:
                    Log.e("ok1", "3");
                    break;
                default:
                    break;
            }
        }
    };



    public insetinfo_thread myThread_insetinfo;
    public ProvinceThread_spinner myThread_spinner;
    public ProvinceThread myThread;
    public Handler handler;
    public Handler handler1;
    public List<String> result;
    public Boolean result_insetinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();
        getInfo();


//        ProvinceAsyncTask task = new ProvinceAsyncTask(this, sp_province,listview);
//        task.execute();


        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handler1=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {

                        if(msg.what==0x12345){    //更新UI或其他操作
                            txt_test.setText("889");
                        }
                    }
                };
                myThread_insetinfo=new insetinfo_thread("insertCargoInfo",handler1);
                myThread_insetinfo.setCname(edit_test.getText().toString());
                myThread_insetinfo.setCnum(edit_test1.getText().toString());
                myThread_insetinfo.start();
            }
        });

    }

    private void getInfo(){

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0x123){    //更新UI或其他操作
                    result=myThread.getList_result();//有值

                    listview.setAdapter(new ListAdapter(MainActivity.this,result));

                }
                if(msg.what==0x1234){    //更新UI或其他操作
                    result=myThread_spinner.getList_result();//有值

                    sp_province.setAdapter(new ListAdapter(MainActivity.this,result));

                }
            }
        };

        myThread=new ProvinceThread("selectAllCargoInfor",handler);
        myThread.start();

        myThread_spinner=new ProvinceThread_spinner("selectAllCargoInfor",handler);
        myThread_spinner.start();




    }

}