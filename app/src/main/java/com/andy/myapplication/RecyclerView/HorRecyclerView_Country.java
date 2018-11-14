package com.andy.myapplication.RecyclerView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.andy.myapplication.Adapter.HorAdapter_Country;
import com.andy.myapplication.Adapter.LinearAdapter_Country;
import com.andy.myapplication.Collector.BaseActivity;
import com.andy.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HorRecyclerView_Country extends BaseActivity {
    private RecyclerView recyclerView;
    private String[] Country;
    private List<Map> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_hor_country);
        recyclerView = findViewById(R.id.recyclerview);
        text();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new HorAdapter_Country(HorRecyclerView_Country.this, list));
    }

    public void text() {
        Country = getResources().getStringArray(R.array.country);
        list = new ArrayList<>();
        for (int i = 0; i < Country.length; i++) {
            Map map = new HashMap();
            map.put("Country_name", Country[i].substring(0, Country[i].indexOf("+")));
            map.put("Country_number", Country[i].substring((Country[i].indexOf("+") + 1)));
            list.add(map);
        }
    }

}
