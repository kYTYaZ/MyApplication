package com.andy.myapplication.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andy.myapplication.Collector.BaseActivity;
import com.andy.myapplication.R;
import com.andy.myapplication.RecyclerView.LinearRecyclerView_Country;
import com.bumptech.glide.Glide;


public class Activity_PhoneReg extends BaseActivity {
    private TextView Text, country, Next;
    private EditText Name, Phone, Password;
    private ImageView imageView;
    private LinearLayout line1, line2, line3;

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_reg);
        findViewById();
        Listener();
        SetText();
        picture();
        OnFocusChange();

        Text.setMovementMethod(LinkMovementMethod.getInstance());//给这个TextView实现超链接效果，不设置就没有点击事件
        Text.setHighlightColor(Color.TRANSPARENT); //设置点击后的颜色为透明，否则会一直出现高亮

    }

    private void clearFocus() {
        Name.clearFocus();
        Phone.clearFocus();
        Password.clearFocus();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String name = data.getStringExtra("name");
                    country.setText(name);
                    break;
                }
            case 2:
                if (resultCode == RESULT_OK) {
                    Name.setText("");
                    Phone.setText("");
                    Password.setText("");
                    country.setText("中国(+86)");
                    clearFocus();
                    break;
                }
        }
    }

    private void picture() {
        Glide.with(this).load("https://timgsa.baidu" +
                ".com/timg?image&quality=80&size=b9999_10000&sec=1540069972855&di" +
                "=6a45b5b3ba56f1ea919f384f4c7f0236&imgtype" +
                "=0&src=http%3A%2F%2Fc.hiphotos.baidu" +
                ".com%2Fzhidao%2Fpic%2Fitem%2F962bd40735fae6cd5919273907b30f2442a70f3c.jpg").into
                (imageView);
    }

    private void findViewById() {
        Text = findViewById(R.id.textView5);
        Name = findViewById(R.id.et_country);
        Phone = findViewById(R.id.et_phone2);
        Password = findViewById(R.id.et_phone3);
        Next = findViewById(R.id.bt_next);
        imageView = findViewById(R.id.imageView5);
        country = findViewById(R.id.et_phone);
        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
    }

    private void Listener() {
        OnClick onClick = new OnClick();
        MyTextWatcher myTextWatcher = new MyTextWatcher();
        Name.addTextChangedListener(myTextWatcher);
        Password.addTextChangedListener(myTextWatcher);
        Phone.addTextChangedListener(myTextWatcher);
        country.setOnClickListener(onClick);
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
            if (!(Name.getText().toString().equals("") || Password.getText().toString().equals
                    ("") || Phone.getText().toString().equals(""))) {
                Next.setEnabled(Boolean.TRUE);
                Next.setBackground(getResources().getDrawable(R.drawable.bg_button));
                Next.setTextColor(getResources().getColor(R.color.green_next2));
                Next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Activity_PhoneReg.this, Activity_Test1.class);
                        startActivityForResult(intent, 2);
                    }
                });
            }
        }

    }

    private class OnClick implements View.OnClickListener {
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.et_phone:
                    intent = new Intent(Activity_PhoneReg.this, LinearRecyclerView_Country.class);
                    startActivityForResult(intent, 1);
            }
        }
    }

    private void SetText() {
        String text = getString(R.string.小字);
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new MyClickText1(), 18, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new MyClickText2(), 34, 44, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Text.setText(spannableString);
    }

    private class MyClickText1 extends ClickableSpan {
        @Override //设置颜色
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(Color.parseColor("#8B99B4"));       //设置文件颜色
            ds.setUnderlineText(true);      //设置下划线
        }

        @Override//设置点击事件
        public void onClick(View widget) {
            Intent intent = new Intent(Activity_PhoneReg.this, Activity_Test1.class);
            startActivity(intent);
        }
    }

    private class MyClickText2 extends ClickableSpan {
        @Override //设置颜色
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(Color.parseColor("#8B99B4"));       //设置文件颜色
            ds.setUnderlineText(true);      //设置下划线
        }

        @Override//设置点击事件
        public void onClick(View widget) {
            Intent intent = new Intent(Activity_PhoneReg.this, Activity_Test2.class);
            startActivity(intent);
        }
    }

    private void OnFocusChange() {
        Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    line1.setBackground(getDrawable(R.drawable.line_green));
                } else {
                    line1.setBackground(getDrawable(R.drawable.line_gray));
                }
            }
        });

        Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    line3.setBackground(getDrawable(R.drawable.line_green));
                } else {
                    line3.setBackground(getDrawable(R.drawable.line_gray));
                }
            }
        });

        Phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    line2.setBackground(getDrawable(R.drawable.line_green));
                } else {
                    line2.setBackground(getDrawable(R.drawable.line_gray));
                }
            }
        });
    }
}
