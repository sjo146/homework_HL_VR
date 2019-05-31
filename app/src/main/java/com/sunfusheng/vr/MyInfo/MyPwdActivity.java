package com.sunfusheng.vr.MyInfo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import com.sunfusheng.vr.R;
import com.sunfusheng.vr.login.LoginActivity;
import com.sunfusheng.vr.login.RegisterActivity;
import com.sunfusheng.vr.transport.JsonUtil;
import org.json.JSONArray;
import org.json.JSONObject;

public class MyPwdActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener,  TextWatcher  {



    private EditText etOldPwd;//旧密码
    private EditText etNewPwd;//新密码
    private EditText etAgainPwd;//再次新密码
    private Button changeKeyBtn;
    private ImageView oldPwdDel;
    private ImageView newPwdDel;
    private ImageView againPwdDel;
    private TextView now_username;
    private TextView now_personal;
    private ImageButton mIbNavigationBack;
    private TextView top;

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.arg1 == 1) {
                Toast.makeText(MyPwdActivity.this, "两次输入不同", Toast.LENGTH_LONG).show();
            }
            else if(msg.arg1 == 2)
            {
                Toast.makeText(MyPwdActivity.this, "修改失败失败", Toast.LENGTH_LONG).show();
            }
        }

        ;
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pwd);
        initView();
    }
    public void initView()
    {

        mIbNavigationBack = findViewById(R.id.ib_navigation_back);
        top=findViewById(R.id.tv_navigation_label);
        top.setText("修改密码");
        etOldPwd = findViewById(R.id.et_old_pwd);//旧密码
        etNewPwd= findViewById(R.id.et_new_pwd);//新密码
        etAgainPwd= findViewById(R.id.et_again_pwd);//再次新密码
        changeKeyBtn= findViewById(R.id.changeKeySubmit);
        oldPwdDel= findViewById(R.id.iv_old_pwd_del);
        newPwdDel= findViewById(R.id.iv_new_pwd_del);
        againPwdDel= findViewById(R.id.iv_again_pwd_del);

        now_personal=findViewById(R.id.mypersonal);
        now_username=findViewById(R.id.myusername);
        now_personal.setText(LoginActivity.user.getUPersonal());
        now_username.setText(LoginActivity.user.getUUsername());



        etAgainPwd.setOnClickListener(this);
        etNewPwd.setOnClickListener(this);
        etOldPwd.setOnClickListener(this);
        changeKeyBtn.setOnClickListener(this);
        againPwdDel.setOnClickListener(this);
        oldPwdDel.setOnClickListener(this);
        newPwdDel.setOnClickListener(this);
        mIbNavigationBack.setOnClickListener(this);



        // mLayBackBar.getViewTreeObserver().addOnGlobalLayoutListener(this);
        etAgainPwd.setOnFocusChangeListener(this);
        etNewPwd.setOnFocusChangeListener(this);
        etOldPwd.setOnFocusChangeListener(this);
        etOldPwd.addTextChangedListener(this);
        etNewPwd.addTextChangedListener(this);
        etAgainPwd.addTextChangedListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_navigation_back:
                //返回
                finish();
                break;
            case R.id.et_again_pwd:
                etAgainPwd.clearFocus();
                etAgainPwd.setFocusableInTouchMode(true);
                etAgainPwd.requestFocus();
                break;
            case R.id.et_old_pwd:
                etOldPwd.clearFocus();
                etOldPwd.setFocusableInTouchMode(true);
                etOldPwd.requestFocus();
                break;
            case R.id.et_new_pwd:
                etNewPwd.clearFocus();
                etNewPwd.setFocusableInTouchMode(true);
                etNewPwd.requestFocus();
                break;
            case R.id.iv_again_pwd_del:
                //清空用户名
                etAgainPwd.setText(null);
                break;
            case R.id.iv_new_pwd_del:
                //清空密码
                etNewPwd.setText(null);
                break;
            case R.id.iv_old_pwd_del:
                //清空密码
                etOldPwd.setText(null);
                break;
            case R.id.changeKeySubmit:
                //登录
                changeRequest();
                break;
            default:
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();

        if (id == R.id.et_old_pwd) {
            if (hasFocus) {
                etOldPwd.setActivated(true);
                etOldPwd.setActivated(false);
            }
        } else if(id==R.id.et_new_pwd)
        {
            if (hasFocus) {
                etNewPwd.setActivated(true);
                etNewPwd.setActivated(false);
            }
        }
        else
        {
            if (hasFocus) {
                etAgainPwd.setActivated(true);
                etAgainPwd.setActivated(false);
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }
    @Override
    public void afterTextChanged(Editable s) {
        String oldPwd = etOldPwd.getText().toString().trim();
        String newPwd = etNewPwd.getText().toString().trim();
        String againPwd = etAgainPwd.getText().toString().trim();



        //是否显示清除按钮
        if (oldPwd.length() > 0) {
            oldPwdDel.setVisibility(View.VISIBLE);
        } else {
            oldPwdDel.setVisibility(View.INVISIBLE);
        }
        if (newPwd.length() > 0) {
            newPwdDel.setVisibility(View.VISIBLE);
        } else {
            newPwdDel.setVisibility(View.INVISIBLE);
        }
        if (againPwd.length() > 0) {
            againPwdDel.setVisibility(View.VISIBLE);
        } else {
            againPwdDel.setVisibility(View.INVISIBLE);
        }

        //登录按钮是否可用
        if (!TextUtils.isEmpty(oldPwd) && !TextUtils.isEmpty(newPwd)&& !TextUtils.isEmpty(againPwd)) {
            System.out.println("===========================);");
            changeKeyBtn.setBackgroundResource(R.drawable.bg_login_submit);
            changeKeyBtn.setTextColor(getResources().getColor(R.color.white));
        } else {
            changeKeyBtn.setBackgroundResource(R.drawable.bg_login_submit_lock);
            changeKeyBtn.setTextColor(getResources().getColor(R.color.account_lock_font_color));
        }
    }
    int  changeresult;
    private void changeRequest() {
        String urlString=getResources().getString(R.string.connecturl)+"/changePwd";
        String oldPwd=etOldPwd.getText().toString();
        String newPwd=etNewPwd.getText().toString();
        String againPwd=etAgainPwd.getText().toString();
        System.out.println(oldPwd);
        System.out.println(newPwd);
        System.out.println(againPwd);
        if(!newPwd.equals(againPwd))
        {
            Message message = handler.obtainMessage();
            message.arg1 = 1;
            handler.sendMessage(message);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json=new JSONObject();
                    json.put("old",oldPwd);
                    json.put("new",newPwd);
                    json.put("uid",LoginActivity.user.getUId());
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
                        Message message = handler.obtainMessage();
                        message.arg1 = 2;
                        handler.sendMessage(message);
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();



    }

}