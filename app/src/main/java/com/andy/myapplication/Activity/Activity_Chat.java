package com.andy.myapplication.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.andy.myapplication.Adapter.MsgAdapter;
import com.andy.myapplication.Chat.Msg;
import com.andy.myapplication.Collector.BaseActivity;
import com.andy.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class Activity_Chat extends BaseActivity implements View.OnClickListener {
    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button yuyin, biaoqing, send;
    private RecyclerView msgRecyclerView;
    MsgAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        findViewById();
        initMsgs();
        Listener();
        adapter = new MsgAdapter(this,msgList);
        msgRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        msgRecyclerView.setAdapter(adapter);
    }

    private void findViewById() {
        inputText = findViewById(R.id.input_text);
        yuyin = findViewById(R.id.yuyin);
        biaoqing = findViewById(R.id.biaoqing);
        send = findViewById(R.id.send);
        msgRecyclerView = findViewById(R.id.recycler_view);
    }

    private void initMsgs() {
        Msg msg1 = new Msg("Hello guy.", Msg.TYPE_PECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello.Who is that", Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("This is Tom.Nice talking to you.", Msg.TYPE_PECEIVED);
        msgList.add(msg3);
    }

    private void Listener() {
        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send:
                String content = inputText.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1);
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);
                    inputText.setText("");
                }
                break;
        }
    }
}
