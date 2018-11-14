package com.andy.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.andy.myapplication.Collector.BaseActivity;
import com.andy.myapplication.ListView.ListView_Country;
import com.andy.myapplication.R;

public class Activity_PhoneLogin extends BaseActivity {
    private TextView Other, Country, Next;
    private EditText Phone;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);
        findViewById();
        setListeners();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String name = data.getStringExtra("name");
                    Country.setText(name);
                    break;
                }
            case 2:
                if (resultCode == RESULT_OK) {
                    Country.setText("中国(+86)");
                    Phone.setText("");
                    Phone.clearFocus();
                    break;
                }
        }
    }

    private void findViewById() {
        Next = findViewById(R.id.bt_next);
        Other = findViewById(R.id.tv_other);
        Country = findViewById(R.id.et_country);
        Phone = findViewById(R.id.et_phone);
    }

    private void setListeners() {
        MyTextWatcher myTextWatcher = new MyTextWatcher();
        OnClick onClick = new OnClick();
        Other.setOnClickListener(onClick);
        Country.setOnClickListener(onClick);
        Phone.addTextChangedListener(myTextWatcher);
        Next.addTextChangedListener(myTextWatcher);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.tv_other:
                    intent = new Intent(Activity_PhoneLogin.this, Activity_OtherWay.class);
                    startActivity(intent);
                    break;
                case R.id.et_country:
                    intent = new Intent(Activity_PhoneLogin.this, ListView_Country.class);
                    startActivityForResult(intent, 1);
                    break;
            }
        }
    }

    private class MyTextWatcher implements android.text.TextWatcher {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Next.setEnabled(false);//在这里重复设置，以保证清除任意EditText中的内容，按钮重新变回不可点击状态
            Next.setBackground(getResources().getDrawable(R.drawable.bg_button_noedit));
            Next.setTextColor(getResources().getColor(R.color.green_next_noedit));
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!(Phone.getText().toString().equals(""))) {
                Next.setEnabled(Boolean.TRUE);
                Next.setBackground(getResources().getDrawable(R.drawable.bg_button));
                Next.setTextColor(getResources().getColor(R.color.green_next2));
                Next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Activity_PhoneLogin.this, Activity_Test2.class);
                        startActivityForResult(intent, 2);
                    }
                });
            }
        }
    }
}

