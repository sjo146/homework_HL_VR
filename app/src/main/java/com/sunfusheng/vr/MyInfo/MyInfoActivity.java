package com.sunfusheng.vr.MyInfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.sunfusheng.vr.Load.LoadZanActivity;
import com.sunfusheng.vr.R;
import com.sunfusheng.vr.login.LoginActivity;
import com.sunfusheng.vr.login.RegisterActivity;

public class MyInfoActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        initView();
    }
    private void initView()
    {
        TextView username;
        TextView personal;
        ImageButton myPwd;
        ImageButton myComment;
        ImageButton myLike;
        ImageButton aboutUs;
        TextView top;
        ImageButton mIbNavigationBack;


        username=(TextView)findViewById(R.id.username);
        personal=(TextView)findViewById(R.id.personal);
        myPwd=(ImageButton)findViewById(R.id.changeKey);
        myComment=(ImageButton)findViewById(R.id.commentDetail);
        myLike=(ImageButton)findViewById(R.id.likeDetail);
        aboutUs=(ImageButton)findViewById(R.id.aboutDetail);
        mIbNavigationBack = findViewById(R.id.ib_navigation_back);
        top=findViewById(R.id.tv_navigation_label);
        top.setText("个人中心");

        username.setText(LoginActivity.user.getUUsername());
        personal.setText(LoginActivity.user.getUPersonal());

        myComment.setOnClickListener(this);
        myLike.setOnClickListener(this);
        myPwd.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
        mIbNavigationBack.setOnClickListener(this);


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aboutDetail:
                //关于我们
                Intent intent=new Intent(MyInfoActivity.this,AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.likeDetail:
                Intent intent1=new Intent(MyInfoActivity.this, LoadZanActivity.class);
                startActivity(intent1);
               //我的赞
                break;
            case R.id.commentDetail:
                Intent intent2=new Intent(MyInfoActivity.this,AboutUsActivity.class);
                startActivity(intent2);
                //我的评论
                break;
            case R.id.changeKey:
                Intent intent3=new Intent(MyInfoActivity.this,MyPwdActivity.class);
                startActivity(intent3);
                //修改密码
                break;
            case R.id.ib_navigation_back:
                finish();
                break;
            default:
                break;
        }
    }
}
