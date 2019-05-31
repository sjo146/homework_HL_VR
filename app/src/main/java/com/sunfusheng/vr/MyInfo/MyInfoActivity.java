package com.sunfusheng.vr.MyInfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.sunfusheng.vr.Load.LoadMyCommentActivity;
import com.sunfusheng.vr.Load.LoadZanActivity;
import com.sunfusheng.vr.R;
import com.sunfusheng.vr.login.LoginActivity;
import com.sunfusheng.vr.login.RegisterActivity;
import com.sunfusheng.vr.transport.JsonUtil;
import org.json.JSONArray;
import org.json.JSONObject;

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
        ImageButton logout;
        ImageView logoutIcon;
        TextView top;
        ImageButton mIbNavigationBack;
        ImageButton changePersonal;


        username=(TextView)findViewById(R.id.username);
        personal=(TextView)findViewById(R.id.personal);
        myPwd=(ImageButton)findViewById(R.id.changeKey);
        myComment=(ImageButton)findViewById(R.id.commentDetail);
        changePersonal=(ImageButton)findViewById(R.id.changePersonal);
        myLike=(ImageButton)findViewById(R.id.likeDetail);
        aboutUs=(ImageButton)findViewById(R.id.aboutDetail);
        logout=(ImageButton)findViewById(R.id.logoutnow);
        logoutIcon=(ImageView)findViewById(R.id.logout);
        logoutIcon.setVisibility(4);
        mIbNavigationBack = findViewById(R.id.ib_navigation_back);
        top=findViewById(R.id.tv_navigation_label);
        top.setText("个人中心");

        username.setText(LoginActivity.user.getUUsername());
        personal.setText(LoginActivity.user.getUPersonal());

        myComment.setOnClickListener(this);
        myLike.setOnClickListener(this);
        myPwd.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
        changePersonal.setOnClickListener(this);
        logout.setOnClickListener(this);
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
            case R.id.logoutnow:
                //关于我们
                LoginActivity.user=null;
                startActivity(new Intent(MyInfoActivity.this,LoginActivity.class));
                break;
            case R.id.likeDetail:
                Intent intent1=new Intent(MyInfoActivity.this, LoadZanActivity.class);
                startActivity(intent1);
               //我的赞
                break;
            case R.id.commentDetail:
                Intent intent2=new Intent(MyInfoActivity.this, LoadMyCommentActivity.class);
                startActivity(intent2);
                //我的评论
                break;
            case R.id.changePersonal:
                Intent intent4=new Intent(MyInfoActivity.this, ChangeMineActivity.class);
                startActivity(intent4);
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
    public void seeComment()
    {
        String urlString=getResources().getString(R.string.connecturl)+"/getMyComment";
        int uid=LoginActivity.user.getUId();
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json=new JSONObject();
                    json.put("uid",uid);
                    String content=String.valueOf(json);
                    String resultData = JsonUtil.getJsonString(urlString,content);
                    JSONArray data = new JSONArray(resultData);
                    int line=data.length();
                    for (int i = 0; i < data.length() ; i++) {
                        JSONObject j = data.getJSONObject(i);
                        changeresult=Integer.valueOf(j.getString("registerResult"));
                        System.out.println(changeresult);

                        // imgMsgs.add(new ImgMsg(j.getInt("imgType"), j.getString("imgTitle"), j.getString("imgDesc"), Base64Object.base64ToBitmap(Base64Object.base64ToString(j.getString("imgAssetName")))));
                    }
                    if(changeresult==1)
                    {
                        Intent intent=new Intent(MyPwdActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else if(changeresult==0)
                    {
                        // 失败
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();*/

    }
}
