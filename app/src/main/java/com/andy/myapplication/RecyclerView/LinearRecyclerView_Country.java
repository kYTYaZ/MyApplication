package com.andy.myapplication.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.andy.myapplication.Adapter.LinearAdapter_Country;
import com.andy.myapplication.Collector.BaseActivity;
import com.andy.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinearRecyclerView_Country extends BaseActivity {
    private RecyclerView recyclerView;
    private String[] Country;
    private List<Map> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_linear_country);
        recyclerView = findViewById(R.id.recyclerview);
        init();
        LinearAdapter_Country adapter = new LinearAdapter_Country(LinearRecyclerView_Country.this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(LinearRecyclerView_Country.this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemListener(new LinearAdapter_Country.OnItemListener() {
            @Override
            public void onClick(int position) {
                String country = list.get(position).get("returnName").toString();
                Intent intent = new Intent();
                intent.putExtra("name", country);
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public void onLongClick(int position) {
                String country = list.get(position).get("returnName").toString();
                Toast.makeText(LinearRecyclerView_Country.this, country, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        Country = getResources().getStringArray(R.array.country);
        list = new ArrayList<>();
        for (int i = 0; i < Country.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("Country_name", Country[i].substring(0, Country[i].indexOf("+")));
            map.put("Country_number", Country[i].substring((Country[i].indexOf("+") + 1)));
            map.put("returnName", Country[i].substring(0, Country[i].indexOf("+")) + "(" + Country[i].substring((Country[i].indexOf("+") + 1)) + ")");
            list.add(map);
        }
    }
}
