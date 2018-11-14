package com.andy.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;

import com.andy.myapplication.Collector.BaseActivity;
import com.andy.myapplication.R;
import com.bumptech.glide.Glide;

public class Activity_Test2 extends BaseActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        imageView = findViewById(R.id.imageView4);
        picture();
    }

    private void picture() {
        Glide.with(this).load("https://timgsa.baidu" +
                ".com/timg?image&quality=80&size=b9999_10000&sec=1540151880073&di" +
                "=d9ed7f581684ba3d26f8bcf922e095b0&imgtype=0&src=http%3A%2F%2Fimg4.duitang" +
                ".com%2Fuploads%2Fitem%2F201607%2F23%2F20160723145453_hKyAV.jpeg").into(imageView);
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
