package com.example.god.myapplication;

/**
 * Created by GOD on 2016/8/28.
 */
import java.util.List;
import android.os.AsyncTask;
import android.widget.Spinner;

public class ProvinceAsyncTask extends AsyncTask<String, String, List<String>> {
    private MainActivity activity;
    private List<String> provinces;
    private Spinner sp_province;

    public ProvinceAsyncTask (MainActivity activity,Spinner sp_province){
        this.activity = activity;
        this.sp_province = sp_province;
    }

    @Override
    protected List<String> doInBackground(String... params) {
        // TODO Auto-generated method stub
        provinces = webserviceutil.getProvinceList();
        return null;
    }

    @Override
    protected void onPostExecute(List<String> result) {
        // TODO Auto-generated method stub
        ListAdapter adapter = new ListAdapter(activity, provinces);
        sp_province.setAdapter(adapter);

    }
}
