package com.example.god.myapplication;

import android.widget.ImageView;



        import org.ksoap2.serialization.SoapObject;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemSelectedListener;
        import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
        import android.widget.TextView;
        import android.app.Activity;

public class MainActivity extends Activity {
    public Spinner sp_province, sp_city;
    public SoapObject detail;
    public TextView txt_today, txt_tomorrow, txt_afterday, txt_current;
    public ImageView img_today1, img_today2, img_tomorrow1, img_tomorrow2,
            img_afterday1, img_afterday2;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControls();

        ProvinceAsyncTask task = new ProvinceAsyncTask(this, sp_province,listview);
        task.execute();


    }

    private void initControls() {
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

}