package com.andy.myapplication.ListView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.andy.myapplication.Adapter.ListAdapter_Main;
import com.andy.myapplication.Collector.BaseActivity;
import com.andy.myapplication.R;

public class ListView_main extends BaseActivity {
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);
        listview = findViewById(R.id.lv_1);
        listview.setAdapter(new ListAdapter_Main(ListView_main.this));
    }

}
