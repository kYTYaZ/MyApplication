package com.andy.myapplication.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.andy.myapplication.Collector.BaseActivity;
import com.andy.myapplication.Fragment.Fragment1;
import com.andy.myapplication.R;

public class Activity_Main2 extends BaseActivity {
    LinearLayout sousuo, jia;
    PopupWindow pop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        replaceFragment(Fragment1.newInstance("fragment1"));
        findViewById();
        OnClick();

    }



    private void OnClick(){
        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(jia);
            }
        });
    }

    private  void findViewById(){
        sousuo = findViewById(R.id.sousuo);
        jia = findViewById(R.id.jia);

    }

    private void showPopupWindow(View parent) {//一个自定义的布局作为显示的内容
        View view = getLayoutInflater().inflate(R.layout.title_jia, null);
        LinearLayout faqi = view.findViewById(R.id.faqiqunliao);
        LinearLayout tianjia = view.findViewById(R.id.tianjiahaoyou);
        LinearLayout saoyisao = view.findViewById(R.id.saoyisao);
        LinearLayout shoukuan = view.findViewById(R.id.shoukuan);
        LinearLayout bangzhu = view.findViewById(R.id.bangzhu);

        WindowManager manger = (WindowManager) getSystemService(Context.WINDOW_SERVICE);


//        int width = manger.getDefaultDisplay().getWidth() / 3;
//        int xpos = manger.getDefaultDisplay().getWidth() / 2 - pop.getWidth() / 2;

        pop = new PopupWindow(view, 500, 500, true);// 实例化popupWindow
        pop.setTouchable(true);//控制PopupWindow内部控件的点击事件
        pop.setOutsideTouchable(true);//点击外部消失
        pop.setFocusable(true);//再次点击按钮消失
        pop.showAsDropDown(view, 0, 0);//距离被点击的控件下方15dp
        click(view);
    }

    private void click(View v) {//PopWindow的布局控件的点击事件的处理
        switch (v.getId()) {
            case R.id.faqiqunliao:
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tianjiahaoyou:
                Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.saoyisao:
                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.shoukuan:
                Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bangzhu:
                Toast.makeText(this, "5", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment,fragment);
        transaction.commitAllowingStateLoss();
    }
}
