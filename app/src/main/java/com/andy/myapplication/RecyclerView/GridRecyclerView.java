package com.andy.myapplication.RecyclerView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.andy.myapplication.Adapter.GridAdapter;
import com.andy.myapplication.Collector.BaseActivity;
import com.andy.myapplication.R;

public class GridRecyclerView extends BaseActivity {
    private RecyclerView recyclerView;
    private int imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_grid);
        recyclerView = findViewById(R.id.gridrecyclerview);
        imageView = R.drawable.daxiong;
        recyclerView.setLayoutManager(new GridLayoutManager(GridRecyclerView.this,5));
        recyclerView.setAdapter(new GridAdapter(GridRecyclerView.this, imageView));
    }


}
