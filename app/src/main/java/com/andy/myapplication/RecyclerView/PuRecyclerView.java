package com.andy.myapplication.RecyclerView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import com.andy.myapplication.Adapter.GridAdapter;
import com.andy.myapplication.Adapter.PuAdapter;
import com.andy.myapplication.Collector.BaseActivity;
import com.andy.myapplication.R;

public class PuRecyclerView extends BaseActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_pu);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new PuAdapter(PuRecyclerView.this));
    }
}
