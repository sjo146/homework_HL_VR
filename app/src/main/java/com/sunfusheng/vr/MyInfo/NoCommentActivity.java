package com.sunfusheng.vr.MyInfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.sunfusheng.vr.Load.StartActivity;
import com.sunfusheng.vr.R;
import com.sunfusheng.vr.login.LoginActivity;

public class NoCommentActivity extends AppCompatActivity {
    private TextView username;
    private TextView personal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_comment);
        TextView top;
        ImageButton mIbNavigationBack;
        Button button;
        mIbNavigationBack = findViewById(R.id.ib_navigation_back);
        top=findViewById(R.id.tv_navigation_label);
        button=findViewById(R.id.button);
        username=findViewById(R.id.username);
        personal=findViewById(R.id.personal);
        username.setText(LoginActivity.user.getUUsername());
        personal.setText(LoginActivity.user.getUPersonal());
        top.setText("我的评论");
        mIbNavigationBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NoCommentActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });
    }
}
