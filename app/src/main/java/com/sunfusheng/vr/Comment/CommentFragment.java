package com.sunfusheng.vr.Comment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.sunfusheng.vr.Load.StartActivity;
import com.sunfusheng.vr.MainActivity;
import com.sunfusheng.vr.R;
import com.sunfusheng.vr.model.Comment;
import com.sunfusheng.vr.transport.JsonUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CommentFragment extends Fragment {
    private String url;
    private  ArrayList<Comment> comments;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.arg1 == 1) {

            }
            if (msg.arg2 == 2) {

            }

        }};


        @Override
        public void onCreate(Bundle savedInstanceState) {
            url = getResources().getString(R.string.connecturl);
            super.onCreate(savedInstanceState);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            //return inflater.inflate(R.layout.nocomment, container, false);
            return inflater.inflate(R.layout.fragment_comment_display, container, false);
        }



        @Override
        public void onAttach(Context context) {
            super.onAttach(context);

        }

        @Override
        public void onDetach() {
            super.onDetach();

        }

        public interface OnFragmentInteractionListener {
            // TODO: Update argument type and name
            void onFragmentInteraction(Uri uri);
        }

        public void getImgComment() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("imgid", CommentActivity.imgMsg.imgid);
                        String resultData = JsonUtil.getJsonString(url + "getAllCommentByimgid", String.valueOf(jsonObject));
                        JSONArray data = new JSONArray(resultData);
                        int line = data.length();
                        comments = new ArrayList();
                        System.out.println("line=" + line);
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject j = data.getJSONObject(i);
                            Comment comment = new Comment();
                            comment.setCid(j.getInt("cid"));
                            comment.setImgid(j.getInt("imgid"));
                            comment.setPinglun(j.getString("pinglun"));
                            comment.setUid(j.getInt("uid"));
                            comments.add(comment);
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
