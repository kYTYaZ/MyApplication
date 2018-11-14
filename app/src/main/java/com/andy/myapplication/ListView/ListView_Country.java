package com.andy.myapplication.ListView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.andy.myapplication.Adapter.ListAdapter_Country;
import com.andy.myapplication.Collector.BaseActivity;
import com.andy.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListView_Country extends BaseActivity {
    ListView listView;
    String[] Country;
    private List<Map> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_country);
        listView = findViewById(R.id.country);
        text();
        ListAdapter_Country adapter = new ListAdapter_Country(this, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                String country = list.get(position).get("returnName").toString();
                intent.putExtra("name", country);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public void text() {
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