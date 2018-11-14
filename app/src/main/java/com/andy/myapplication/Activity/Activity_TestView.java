package com.andy.myapplication.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.andy.myapplication.Collector.ActivityCollector;
import com.andy.myapplication.Collector.BaseActivity;
import com.andy.myapplication.ListView.ListView_Country;
import com.andy.myapplication.R;
import com.andy.myapplication.RecyclerView.GridRecyclerView;
import com.andy.myapplication.RecyclerView.HorRecyclerView_Country;
import com.andy.myapplication.RecyclerView.PuRecyclerView;
import com.andy.myapplication.Test_Broadcast.Activity_Broadcast;
import com.andy.myapplication.Test_Broadcast.Activity_Broadcast2;
import com.andy.myapplication.Test_Broadcast.Activity_Broadcast3;
import com.andy.myapplication.Test_Broadcast.Activity_Broadcast4_LoginActivity;
import com.andy.myapplication.Test_News.NewsMainActivity;

public class Activity_TestView extends BaseActivity {
    private Button button1, button2, button3, button4, button5, button6, button7, button8,
    button9, button10,button11,button12,button13,button14,button15,button16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview);
        findViewById();
        setOnClick();
    }

    public void setOnClick() {
        OnClick onClick = new OnClick();
        button1.setOnClickListener(onClick);
        button2.setOnClickListener(onClick);
        button3.setOnClickListener(onClick);
        button4.setOnClickListener(onClick);
        button5.setOnClickListener(onClick);
        button6.setOnClickListener(onClick);
        button7.setOnClickListener(onClick);
        button8.setOnClickListener(onClick);
        button9.setOnClickListener(onClick);
        button10.setOnClickListener(onClick);
        button11.setOnClickListener(onClick);
        button12.setOnClickListener(onClick);
        button13.setOnClickListener(onClick);
        button14.setOnClickListener(onClick);
        button15.setOnClickListener(onClick);
        button16.setOnClickListener(onClick);
    }

    public void findViewById() {
        button1 = findViewById(R.id.ListView);
        button2 = findViewById(R.id.HorRecyclerView);
        button3 = findViewById(R.id.GridRecyclerView);
        button4 = findViewById(R.id.PuRecyclerView);
        button5 = findViewById(R.id.WebView);
        button6 = findViewById(R.id.Toast);
        button7 = findViewById(R.id.Dialog);
        button8 = findViewById(R.id.Esc);
        button9 = findViewById(R.id.ProgressDialog);
        button10 = findViewById(R.id.Chat);
        button11 = findViewById(R.id.Main);
        button12 = findViewById(R.id.NewsFragment);
        button13 = findViewById(R.id.Test_Broadcast);
        button14 = findViewById(R.id.Test_Broadcast2);
        button15 = findViewById(R.id.Test_Broadcast3);
        button16 = findViewById(R.id.Test_Broadcast4);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.ListView:
                    intent = new Intent(Activity_TestView.this, ListView_Country.class);
                    startActivity(intent);
                    break;
                case R.id.HorRecyclerView:
                    intent = new Intent(Activity_TestView.this, HorRecyclerView_Country.class);
                    startActivity(intent);
                    break;
                case R.id.GridRecyclerView:
                    intent = new Intent(Activity_TestView.this, GridRecyclerView.class);
                    startActivity(intent);
                    break;
                case R.id.PuRecyclerView:
                    intent = new Intent(Activity_TestView.this, PuRecyclerView.class);
                    startActivity(intent);
                    break;
                case R.id.WebView:
                    intent = new Intent(Activity_TestView.this, Activity_WebView.class);
                    startActivity(intent);
                    break;
                case R.id.Toast:
                    intent = new Intent(Activity_TestView.this, Activity_Toast.class);
                    startActivity(intent);
                    break;
                case R.id.Dialog:
                    intent = new Intent(Activity_TestView.this, Activity_Dialog.class);
                    startActivity(intent);
                    break;
                case R.id.Esc:
                    AlertDialog.Builder builder = new AlertDialog.Builder(Activity_TestView.this);
                    builder.setTitle("提示")
                            .setMessage("确定要退出吗？")
                            .setPositiveButton("退出", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 一键退出
                                    ActivityCollector.finishall();
                                }
                            })
                            .setNegativeButton("再看看", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                    break;
                case R.id.ProgressDialog:
                    ProgressDialog.Builder builder1 = new ProgressDialog.Builder(Activity_TestView.this);
                    builder1.setTitle("This is ProgressDialog")
                            .setMessage("Loading...")
                            .setCancelable(true).show();
                    break;
                case R.id.Chat:
                    intent = new Intent(Activity_TestView.this, Activity_Chat.class);
                    startActivity(intent);
                    break;
                case R.id.Main:
                    intent = new Intent(Activity_TestView.this,Activity_Main2.class);
                    startActivity(intent);
                    break;
                case R.id.NewsFragment:
                    intent = new Intent(Activity_TestView.this,NewsMainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.Test_Broadcast:
                    intent = new Intent(Activity_TestView.this,Activity_Broadcast.class);
                    startActivity(intent);
                    break;
                case R.id.Test_Broadcast2:
                    intent = new Intent(Activity_TestView.this,Activity_Broadcast2.class);
                    startActivity(intent);
                    break;
                case R.id.Test_Broadcast3:
                    intent = new Intent(Activity_TestView.this,Activity_Broadcast3.class);
                    startActivity(intent);
                    break;
                case R.id.Test_Broadcast4:
                    intent = new Intent(Activity_TestView.this,Activity_Broadcast4_LoginActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }


}
