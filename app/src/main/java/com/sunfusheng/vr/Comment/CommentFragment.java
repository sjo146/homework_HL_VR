package com.sunfusheng.vr.Comment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sunfusheng.vr.R;
import com.sunfusheng.vr.login.LoginActivity;
import com.sunfusheng.vr.transport.JsonUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class CommentFragment extends Fragment {
    private String url;
    private OnFragmentInteractionListener mListener;

    public CommentFragment() {
        // Required empty public constructor
    }

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


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
/*
    public void getImgComment() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zans=new HashMap();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("uid", LoginActivity.user.getUId());
                    String resultData = JsonUtil.getJsonString(url + "getAllZans", String.valueOf(jsonObject));
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

 */
}
