package com.sunfusheng.vr.Comment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import com.sunfusheng.vr.R;
import com.sunfusheng.vr.login.LoginActivity;
import com.sunfusheng.vr.model.Comment_name;
import com.sunfusheng.vr.transport.JsonUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 在该页面获取该图片的所有评论
 */
public class MyCommentLoading extends AppCompatActivity {
    public static ArrayList<Comment_name> comment_names;
    public static Boolean isgetPinglun = false;
    public static int imgid;
    public static int uid;
    private String url;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.arg1 == 1) {
                if (comment_names.size() >0)isgetPinglun=true;
                else isgetPinglun=false;
                Intent intent=new Intent(MyCommentLoading.this,myCommentActivity.class);
                startActivity(intent);
            }
            if (msg.arg2 == 2) {
                Toast.makeText(MyCommentLoading.this, "网络错误", Toast.LENGTH_SHORT);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_loading);
        imgid = getIntent().getIntExtra("imgid", -1);
        uid = getIntent().getIntExtra("uid", -1);
        System.out.println("now my comment loading");

        url=getResources().getString(R.string.connecturl);
        getImgComment();

    }

    public void getImgComment() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("imgid", imgid);
                    jsonObject.put("uid", uid);
                    String resultData = JsonUtil.getJsonString(url + "/getMyComment", String.valueOf(jsonObject));
                    JSONArray data;
                    System.out.println("resultData.size="+resultData.length());
                    if(resultData.length()!=3){
                        int length=resultData.length()-1;
                        data = new JSONArray(resultData.substring(1,length));}
                    else
                        data = new JSONArray(resultData);
                    System.out.println("Data.size="+data.length());
                    comment_names = new ArrayList();
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject j = data.getJSONObject(i);
                        Comment_name comment_name = new Comment_name();
                        comment_name.setCid(j.getInt("cid"));
                        comment_name.setImgid(j.getInt("imgid"));
                        comment_name.setPinglun(j.getString("pinglun"));
                        comment_name.setUid(j.getInt("uid"));
                        comment_name.setUUsername(LoginActivity.user.getUUsername());
                        comment_names.add(comment_name);
                    }

                    Message message = handler.obtainMessage();
                    message.arg1 = 1;
                    handler.sendMessage(message);
                } catch (Exception e) {
                    Message message = handler.obtainMessage();
                    message.arg1 = 2;
                    handler.sendMessage(message);
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
