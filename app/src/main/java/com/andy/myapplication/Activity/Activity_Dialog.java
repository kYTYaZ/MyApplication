package com.andy.myapplication.Activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.andy.myapplication.Collector.BaseActivity;
import com.andy.myapplication.R;
import com.andy.myapplication.widget.CustomDialog;

public class Activity_Dialog extends BaseActivity {
    private Button button1, button2, button3, button4, button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        findViewById();
        OnClick onClick = new OnClick();
        button1.setOnClickListener(onClick);
        button2.setOnClickListener(onClick);
        button3.setOnClickListener(onClick);
        button4.setOnClickListener(onClick);
        button5.setOnClickListener(onClick);
    }

    private void findViewById() {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
    }

    private class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button1:
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Activity_Dialog.this);
                    builder1.setTitle("请回答")
                            .setMessage("你觉得我好看吗？")
                            .setPositiveButton("好看", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(Activity_Dialog.this, "你很诚实", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNeutralButton("还行", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(Activity_Dialog.this, "你再说一遍", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("不好", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(Activity_Dialog.this, "打死你！", Toast.LENGTH_SHORT).show();
                                }
                            }).show();
                    break;

                case R.id.button2:
                    final String[] array2 = new String[]{"男", "女"};
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(Activity_Dialog.this);
                    builder2.setTitle("选择性别")
                            .setItems(array2, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(Activity_Dialog.this, array2[which], Toast.LENGTH_SHORT).show();
                                }
                            }).show();
                    break;

                case R.id.button3:
                    final String[] array3 = new String[]{"男", "女"};
                    AlertDialog.Builder builder3 = new AlertDialog.Builder(Activity_Dialog.this);
                    builder3.setTitle("选择性别")
                            .setSingleChoiceItems(array3, 1, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(Activity_Dialog.this, array3[which], Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            })
                            .setCancelable(false)
                            .show();
                    break;

                case R.id.button4:
                    final String[] array4 = new String[]{"健身", "滑板", "写代码"};
                    boolean[] isSelected = new boolean[]{true, true, false};
                    AlertDialog.Builder builder4 = new AlertDialog.Builder(Activity_Dialog.this);
                    builder4.setTitle("选择兴趣")
                            .setMultiChoiceItems(array4, isSelected, new DialogInterface.OnMultiChoiceClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                    Toast.makeText(Activity_Dialog.this, array4[which] + ":" + isChecked, Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            }).show();
                    break;

                case R.id.button5:
                    CustomDialog customDialog = new CustomDialog(Activity_Dialog.this,R.style.MyDialog);
                    customDialog.setTitle("提示")
                            .setMessage("确定要删除订单吗")
                            .setCancel("取消", new CustomDialog.IOnCancelListener() {
                                @Override
                                public void onCancel(CustomDialog dialog) {
                                    Toast.makeText(Activity_Dialog.this, "cancel", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setConfirm("确认", new CustomDialog.IOnConfirmListener() {
                                @Override
                                public void onConfirm(CustomDialog dialog) {
                                    Toast.makeText(Activity_Dialog.this, "confirm", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                    customDialog.setCancelable(false);
                    break;
            }
        }
    }
}
