package com.example.god.myapplication;

import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

/**
 * Created by GOD on 2016/8/28.
 */

public class listviewAsyncTask extends AsyncTask<String, String, List<String>> {
    private MainActivity activity;
    private List<String> provinces;
    private ListView listView;

    public listviewAsyncTask (MainActivity activity,ListView listView){
        this.activity = activity;
        this.listView = listView;
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
        listView.setAdapter(adapter);

    }
}
