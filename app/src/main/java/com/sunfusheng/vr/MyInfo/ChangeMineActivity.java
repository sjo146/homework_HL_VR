package com.sunfusheng.vr.MyInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import com.sunfusheng.vr.R;
import com.sunfusheng.vr.login.LoginActivity;
import com.sunfusheng.vr.transport.JsonUtil;
import org.json.JSONArray;
import org.json.JSONObject;

public class ChangeMineActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener,  TextWatcher  {



    private EditText etusername;
    private EditText etpersonal;
    private Button changepersonalBtn;
    private ImageView usernameDel;
    private ImageView personalDel;
    private ImageButton mIbNavigationBack;
    private TextView top;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_mine);
        initView();
    }
    public void initView() {

        mIbNavigationBack = findViewById(R.id.ib_navigation_back);
        top = findViewById(R.id.tv_navigation_label);
        top.setText("修改信息");
        etpersonal = findViewById(R.id.et_personal);
        etusername = findViewById(R.id.et_username);
        changepersonalBtn = findViewById(R.id.changePersonalSubmit);
        personalDel = findViewById(R.id.iv_personal_del);
        usernameDel = findViewById(R.id.iv_username_del);
        etpersonal.setText(LoginActivity.user.getUPersonal());
        etusername.setText(LoginActivity.user.getUUsername());


        etusername.setOnClickListener(this);
        etpersonal.setOnClickListener(this);
        changepersonalBtn.setOnClickListener(this);
        usernameDel.setOnClickListener(this);
        personalDel.setOnClickListener(this);
        mIbNavigationBack.setOnClickListener(this);


        // mLayBackBar.getViewTreeObserver().addOnGlobalLayoutListener(this);
        etpersonal.setOnFocusChangeListener(this);
        etusername.setOnFocusChangeListener(this);
        etpersonal.addTextChangedListener(this);
        etusername.addTextChangedListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_navigation_back:
                //返回
                finish();
                break;
            case R.id.et_personal:
                etpersonal.clearFocus();
                etpersonal.setFocusableInTouchMode(true);
                etpersonal.requestFocus();
                break;
            case R.id.et_username:
                etusername.clearFocus();
                etusername.setFocusableInTouchMode(true);
                etusername.requestFocus();
                break;
            case R.id.iv_personal_del:
                etpersonal.setText(null);
                break;
            case R.id.iv_username_del:
                etusername.setText(null);
                break;
            case R.id.changePersonalSubmit:
                //登录
                changePersonalRequest();
                break;
            default:
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();

        if (id == R.id.et_personal) {
            if (hasFocus) {
                etpersonal.setActivated(true);
                etpersonal.setActivated(false);
            }
        } else if(id==R.id.et_username)
        {
            if (hasFocus) {
                etusername.setActivated(true);
                etusername.setActivated(false);
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
        String username = etusername.getText().toString().trim();
        String personal = etpersonal.getText().toString().trim();



        //是否显示清除按钮
        if (username.length() > 0) {
            usernameDel.setVisibility(View.VISIBLE);
        } else {
            usernameDel.setVisibility(View.INVISIBLE);
        }
        if (personal.length() > 0) {
            personalDel.setVisibility(View.VISIBLE);
        } else {
            personalDel.setVisibility(View.INVISIBLE);
        }
        //登录按钮是否可用
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(personal)) {
            System.out.println("===========================);");
            changepersonalBtn.setBackgroundResource(R.drawable.bg_login_submit);
            changepersonalBtn.setTextColor(getResources().getColor(R.color.white));
        } else {
            changepersonalBtn.setBackgroundResource(R.drawable.bg_login_submit_lock);
            changepersonalBtn.setTextColor(getResources().getColor(R.color.account_lock_font_color));
        }
    }
    int  changeresult;
    private void changePersonalRequest() {
        String urlString=getResources().getString(R.string.connecturl)+"/changeMine";
        String username=etusername.getText().toString();
        String personal=etpersonal.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json=new JSONObject();
                    json.put("username",username);
                    json.put("personal",personal);
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
                        LoginActivity.user.setUUsername(username);
                        LoginActivity.user.setUPersonal(personal);
                        Intent intent=new Intent(ChangeMineActivity.this, MyInfoActivity.class);
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
        }).start();



    }

}