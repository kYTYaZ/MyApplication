package com.andy.myapplication.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andy.myapplication.R;

public class Fragment1 extends Fragment {
private TextView textView;

//构造器
    public static Fragment1 newInstance(String text){
        Fragment1 fragment1  = new Fragment1();
        Bundle bundle = new Bundle();
        bundle.putString("textView",text);
        fragment1.setArguments(bundle);
        return fragment1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment1,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.fragment1);
        if(getArguments()!=null){
            textView.setText(getArguments().getString("textView"));
        }

//        if(getActivity()!=null){
//            todo
//        }else{
//        }
    }
}


