package com.andy.myapplication.Test_Broadcast;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.andy.myapplication.Collector.BaseActivity;
import com.andy.myapplication.R;

public class Activity_Broadcast4_LoginActivity extends BaseActivity {
    private EditText accountEdit, passwoedEdit;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast4_loginactivity);
        accountEdit = findViewById(R.id.account);
        passwoedEdit = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwoedEdit.getText().toString();
                if (account.equals("admin") && password.equals("123456")) {
                    Intent intent = new Intent(Activity_Broadcast4_LoginActivity.this, Activity_Broadcast4_MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Activity_Broadcast4_LoginActivity.this, "account or password is invalid", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
