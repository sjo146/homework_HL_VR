package com.sunfusheng.vr.login;

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
import com.sunfusheng.vr.transport.JsonUtil;
import org.json.JSONArray;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener,  TextWatcher  {



    private EditText mEtRegisterUsername;
    private EditText mEtRegisterPwd;
    private Button registerBtn;
    private ImageView userDel;
    private ImageView pwdDel;
    private ImageButton mIbNavigationBack;
    private LinearLayout mLlRegisterUsername;
    private LinearLayout mLlRegisterPwd;

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.arg1 == 1) {
                Toast.makeText(RegisterActivity.this, "用户名已存在", Toast.LENGTH_LONG).show();
            }
            else if(msg.arg1 == 2)
            {
                Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_LONG).show();
            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }
    public void initView()
    {
        mEtRegisterUsername=(EditText)findViewById(R.id.et_register_username);
        mEtRegisterPwd=(EditText)findViewById(R.id.et_register_pwd_input);
        registerBtn=(Button)findViewById(R.id.bt_register_submit);
        userDel=findViewById(R.id.iv_register_username_del);
        pwdDel=findViewById(R.id.iv_register_pwd_del);
        mIbNavigationBack = findViewById(R.id.ib_navigation_back);
       mLlRegisterUsername=findViewById(R.id.ll_register_two_username);
       mLlRegisterPwd=findViewById(R.id.ll_register_two_pwd);

        mEtRegisterUsername.setOnClickListener(this);
        mEtRegisterPwd.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        userDel.setOnClickListener(this);
        pwdDel.setOnClickListener(this);
        mIbNavigationBack.setOnClickListener(this);


       // mLayBackBar.getViewTreeObserver().addOnGlobalLayoutListener(this);
        mEtRegisterUsername.setOnFocusChangeListener(this);
        mEtRegisterUsername.addTextChangedListener(this);
        mEtRegisterPwd.setOnFocusChangeListener(this);
        mEtRegisterPwd.addTextChangedListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_navigation_back:
                //返回
                finish();
                break;
            case R.id.et_register_username:
                mEtRegisterUsername.clearFocus();
                mEtRegisterUsername.setFocusableInTouchMode(true);
                mEtRegisterUsername.requestFocus();
                break;
            case R.id.et_register_pwd_input:
                mEtRegisterPwd.clearFocus();
                mEtRegisterPwd.setFocusableInTouchMode(true);
                mEtRegisterPwd.requestFocus();
                break;
            case R.id.iv_register_username_del:
                //清空用户名
                mEtRegisterUsername.setText(null);
                break;
            case R.id.iv_register_pwd_del:
                //清空密码
                mEtRegisterPwd.setText(null);
                break;
            case R.id.bt_register_submit:
                //登录
                registerRequest();
                break;
            default:
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();

        if (id == R.id.et_login_username) {
            if (hasFocus) {
                mLlRegisterUsername.setActivated(true);
                mLlRegisterPwd.setActivated(false);
            }
        } else {
            if (hasFocus) {
                mLlRegisterPwd.setActivated(true);
                mLlRegisterUsername.setActivated(false);
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
        String username = mEtRegisterUsername.getText().toString().trim();
        String pwd = mEtRegisterPwd.getText().toString().trim();

        //是否显示清除按钮
        if (username.length() > 0) {
            userDel.setVisibility(View.VISIBLE);
        } else {
            userDel.setVisibility(View.INVISIBLE);
        }
        if (pwd.length() > 0) {
            pwdDel.setVisibility(View.VISIBLE);
        } else {
            pwdDel.setVisibility(View.INVISIBLE);
        }

        //登录按钮是否可用
        if (!TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(username)) {
            System.out.println("===========================);");
            registerBtn.setBackgroundResource(R.drawable.bg_login_submit);
            registerBtn.setTextColor(getResources().getColor(R.color.white));
        } else {
            registerBtn.setBackgroundResource(R.drawable.bg_login_submit_lock);
            registerBtn.setTextColor(getResources().getColor(R.color.account_lock_font_color));
        }
    }
    int  registerresult;
    private void registerRequest() {
        String urlString=getResources().getString(R.string.connecturl)+"register";
        String username=mEtRegisterUsername.getText().toString();
        String pwd=mEtRegisterPwd.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json=new JSONObject();
                    json.put("UUsername",username);
                    json.put("UPwd",pwd);
                    String content=String.valueOf(json);
                    String resultData = JsonUtil.getJsonString(urlString,content);
                    JSONArray data = new JSONArray(resultData);
                    int line=data.length();
                    for (int i = 0; i < data.length() ; i++) {
                        JSONObject j = data.getJSONObject(i);
                        registerresult=Integer.valueOf(j.getString("registerResult"));
                        // imgMsgs.add(new ImgMsg(j.getInt("imgType"), j.getString("imgTitle"), j.getString("imgDesc"), Base64Object.base64ToBitmap(Base64Object.base64ToString(j.getString("imgAssetName")))));
                    }
                    if(registerresult==2)
                    {
                        Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else if(registerresult==1)
                    {
                        Message message = handler.obtainMessage();
                        message.arg1 = 1;
                        handler.sendMessage(message);
                    }
                    else
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