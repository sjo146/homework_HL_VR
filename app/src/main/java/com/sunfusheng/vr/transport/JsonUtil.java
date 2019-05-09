package com.sunfusheng.vr.transport;

import android.graphics.Bitmap;
import android.os.Message;
import com.sunfusheng.vr.Base64Util.Base64Object;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public  class JsonUtil {

    public static String getJsonString(String urlString){
        String result=null;
        URL url;
        try {

            url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url
                    .openConnection();
            conn.setConnectTimeout(5000);
            conn.setDoOutput(true);// 设置允许输出
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8"); // 内容类型
            OutputStream os = conn.getOutputStream();
            InputStream in = null;

            os.close();
            System.out.println(conn.getResponseCode());
            if (conn.getResponseCode() == 200) {
                in = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(in);
                BufferedReader bufferReader = new BufferedReader(isr);
                String inputLine = "";
                String resultData = "";
                while ((inputLine = bufferReader.readLine()) != null) {
                    resultData += inputLine + "\n";
                }
                result=resultData;

            }
           return result;
        }catch (Exception e) {
            e.printStackTrace();

        }
        return result;
    }
}
