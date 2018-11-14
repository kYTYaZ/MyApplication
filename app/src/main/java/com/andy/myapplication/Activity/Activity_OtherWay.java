package com.andy.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andy.myapplication.Collector.BaseActivity;
import com.andy.myapplication.ListView.ListView_main;
import com.andy.myapplication.R;

public class Activity_OtherWay extends BaseActivity {
    private TextView Other, Login;
    private EditText Name, Password;
    private LinearLayout layout1,layout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otherway);

        findViewById();
        setListeners();
        OnFocusChange();
    }

    private void findViewById() {
        Other = findViewById(R.id.tv_other);
        Name = findViewById(R.id.et_country);
        Password = findViewById(R.id.et_phone);
        Login = findViewById(R.id.bt_next);
        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
    }

    private void setListeners() {
        MyTextWatcher myTextWatcher = new MyTextWatcher();
        OnClick onClick = new OnClick();
        Login.addTextChangedListener(myTextWatcher);
        Password.addTextChangedListener(myTextWatcher);
        Name.addTextChangedListener(myTextWatcher);
        Other.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.tv_other:
                    intent = new Intent(Activity_OtherWay.this, Activity_PhoneLogin.class);
                    break;
            }
            startActivity(intent);
        }
    }

    private class MyTextWatcher implements android.text.TextWatcher {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Login.setEnabled(Boolean.FALSE);//在这里重复设置，以保证清除任意EditText中的内容，按钮重新变回不可点击状态
            Login.setBackground(getResources().getDrawable(R.drawable.bg_button_noedit));
            Login.setTextColor(getResources().getColor(R.color.green_next_noedit));
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!(Name.getText().toString().equals("") || Password.getText().toString().equals
                    (""))) {
                Login.setEnabled(Boolean.TRUE);
                Login.setBackground(getResources().getDrawable(R.drawable.bg_button));
                Login.setTextColor(getResources().getColor(R.color.green_next2));
                Login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Activity_OtherWay.this, ListView_main.class);
                        startActivity(intent);
                    }
                });
            }
        }
    }

    private void OnFocusChange() {
        Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    layout1.setBackground(getDrawable(R.drawable.line_green));
                } else {
                    layout1.setBackground(getDrawable(R.drawable.line_gray));

                }
            }
        });

        Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    layout2.setBackground(getDrawable(R.drawable.line_green));
                } else {
                    layout2.setBackground(getDrawable(R.drawable.line_gray));
                }
            }
        });
    }
}
