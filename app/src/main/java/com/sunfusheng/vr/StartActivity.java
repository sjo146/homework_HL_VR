package com.sunfusheng.vr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import com.sunfusheng.vr.Base64Util.Base64Object;
import com.sunfusheng.vr.model.ImgMsg;
import com.sunfusheng.vr.transport.JsonUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;


public class StartActivity extends Activity {

    private Button button;
    private ImageView imageView;
    private String urlString;
    private URL url;
    public static ArrayList<ImgMsg> imgMsgs;


    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.arg1 == 1) {
                Log.println(Log.WARN,"msg","成功得到图片信息");

            }
        }

        ;
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        button = (Button) findViewById(R.id.btn);
        urlString = getResources().getString(R.string.url);
        imgMsgs=new ArrayList();
        getView();
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }


    public void getView() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String resultData = JsonUtil.getJsonString(urlString);
                    System.out.println("post方法取回内容：" + resultData);
                    JSONArray data = new JSONArray(resultData);
                    System.out.println(data.length());
                    for (int i = 0; i < data.length() - 1; i++) {
                        JSONObject j = data.getJSONObject(i);
                        System.out.println(j.getString("imgTitle"));
                        imgMsgs.add(
                                new ImgMsg(j.getInt("imgType"),
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
}
