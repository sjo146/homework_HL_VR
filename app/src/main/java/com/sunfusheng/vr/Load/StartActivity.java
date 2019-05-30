package com.sunfusheng.vr.Load;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.sunfusheng.vr.Base64Util.Base64Object;
import com.sunfusheng.vr.MainActivity;
import com.sunfusheng.vr.R;
import com.sunfusheng.vr.login.LoginActivity;
import com.sunfusheng.vr.model.ImgMsg;
import com.sunfusheng.vr.transport.JsonUtil;
import com.wang.avi.AVLoadingIndicatorView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class StartActivity extends Activity {

    private String urlString;
    private boolean isGet = false;
    public static ArrayList<ImgMsg> imgMsgs;
    public static Map<Integer, Boolean> zans;
    private AVLoadingIndicatorView avi;

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.arg1 == 1) {
                Log.println(Log.WARN, "msg", "成功得到图片信息");
                getItemZanState();
                isGet = true;
            }
            if (msg.arg2 == 2) {
                Toast.makeText(StartActivity.this, "网络繁忙！稍后再试", Toast.LENGTH_LONG).show();
            }
            if (msg.arg1 == 3) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }

        ;
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        avi = findViewById(R.id.avi);
        avi.show();
        urlString = getResources().getString(R.string.connecturl);
        imgMsgs = new ArrayList();
        getView();
    }

    /**
     * 获取所有图片
     */
    public void getView() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String resultData = JsonUtil.getJsonString(urlString + "getAllImgMsg");
                    JSONArray data = new JSONArray(resultData);
                    int line = data.length();
                    System.out.println("line=" + line);
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject j = data.getJSONObject(i);

                        imgMsgs.add(
                                new ImgMsg(j.getInt("imgId"),
                                        j.getInt("imgType"),
                                        j.getString("imgTitle"),
                                        j.getString("imgDesc"),
                                        Base64Object.base64ToBitmap(Base64Object.base64ToString(j.getString("imgAssetName")))
                                ));
                        // imgMsgs.add(new ImgMsg(j.getInt("imgType"), j.getString("imgTitle"), j.getString("imgDesc"), Base64Object.base64ToBitmap(Base64Object.base64ToString(j.getString("imgAssetName")))));
                    }

                    Message message = handler.obtainMessage();
                    message.arg1 = 1;
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /**
     * 获取点赞情况
     */
    void getItemZanState() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zans=new HashMap();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("uid", LoginActivity.user.getUId());
                    String resultData = JsonUtil.getJsonString(urlString + "getAllZans", String.valueOf(jsonObject));
                    JSONArray data = new JSONArray(resultData);
                    int line = data.length();
                    System.out.println("line=" + line);
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject j = data.getJSONObject(i);
                        zans.put(j.getInt("imgid"), j.getBoolean("iszan"));
                    }

                    Message message = handler.obtainMessage();
                    message.arg1 = 3;
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
