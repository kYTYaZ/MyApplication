package com.andy.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;

import com.andy.myapplication.Collector.BaseActivity;
import com.andy.myapplication.R;
import com.bumptech.glide.Glide;

public class Activity_Test1 extends BaseActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        imageView = findViewById(R.id.imageView3);
        picture();
    }

    private void picture() {
        Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1540151158488&di=44f3bb7f206b7381adebc9b0c6f257be&imgtype" +
                "=0&src=http%3A%2F%2Fimg.mp.sohu.com%2Fq_mini%2Cc_zoom%2Cw_640%2Fupload%2F20170722%2F56633d53b7514bd69ce9c1c8fe2b80ab.jpg").into(imageView);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//刷新
            setResult(RESULT_OK, intent);
            finish();
        }
        return true;
    }
}
