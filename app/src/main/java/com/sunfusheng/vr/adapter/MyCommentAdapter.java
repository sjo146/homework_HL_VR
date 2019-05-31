package com.sunfusheng.vr.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sunfusheng.vr.Comment.CommentLoading;
import com.sunfusheng.vr.Comment.MyCommentLoading;
import com.sunfusheng.vr.Load.LoadMyCommentActivity;
import com.sunfusheng.vr.Load.LoadMyCommentActivity;
import com.sunfusheng.vr.R;
import com.sunfusheng.vr.login.LoginActivity;
import com.sunfusheng.vr.model.ImgMsg;
import com.sunfusheng.vr.transport.JsonUtil;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class MyCommentAdapter extends RecyclerView.Adapter<MyCommentAdapter.ViewHolder> {
    public ArrayList<ImgMsg> imgMsgs;
    private Context context;
    public OnItemClickListener itemClickListener;
    private String url;
    private Map<Integer, Boolean> zans;


    public MyCommentAdapter(Context context) {
        super();
        this.imgMsgs = LoadMyCommentActivity.imgMsgs;
        url = context.getResources().getString(R.string.connecturl);
    }

    public MyCommentAdapter(Context context, ArrayList<ImgMsg> imgMsgs) {
        this.context = context;
        this.imgMsgs = LoadMyCommentActivity.imgMsgs;
        url = context.getResources().getString(R.string.connecturl);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv_title;
        TextView tv_desc;
        TextView tv_comment;
        TextView tv_mycomment;
        ImageView zan_img;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            tv_comment = itemView.findViewById(R.id.tv_comment);
            tv_mycomment=itemView.findViewById(R.id.tv_mycomment);
            zan_img = itemView.findViewById(R.id.zan_img);

        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_my_comment, null));
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImgMsg imgMsg = imgMsgs.get(position);
        holder.imageView.setImageBitmap(imgMsg.assetName);
        holder.tv_title.setText(imgMsg.title);
        holder.tv_desc.setText(imgMsg.desc);

        Boolean iszan =false;
        if (!LoadMyCommentActivity.zans.containsKey(imgMsg.imgid)){
            holder.zan_img.setImageResource(R.mipmap.zan_un);
            LoadMyCommentActivity.zans.put(imgMsg.imgid,false);
        }
        else {
            iszan=LoadMyCommentActivity.zans.get(imgMsg.imgid);
            if (iszan == true)
                holder.zan_img.setImageResource(R.mipmap.zan);
            else {
                holder.zan_img.setImageResource(R.mipmap.zan_un);
            }
        }
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (itemClickListener != null)
                    itemClickListener.onItemClick(view, position);
                return true;
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null)
                    itemClickListener.onItemClick(v, position);
            }
        });
        holder.tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null)
                    itemClickListener.onItemClick(v, position);
            }
        });
        holder.tv_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null)
                    itemClickListener.onItemClick(v, position);
            }
        });
        holder.zan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean z;

                if (LoadMyCommentActivity.zans.get(imgMsg.imgid) == true) {
                    z = false;
                    holder.zan_img.setImageResource(R.mipmap.zan_un);
                } else {
                    z = true;
                    holder.zan_img.setImageResource(R.mipmap.zan);
                }

                LoadMyCommentActivity.zans.put(imgMsg.imgid, z);
                updatezan(imgMsg.imgid);
            }
        });
        holder.tv_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, CommentLoading.class);
                intent.putExtra("imgid",imgMsg.imgid);
                context.startActivity(intent);
            }
        });
        holder.tv_mycomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, MyCommentLoading.class);
                intent.putExtra("imgid",imgMsg.imgid);
                intent.putExtra("uid",LoginActivity.user.getUId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return imgMsgs != null ? imgMsgs.size() : 0;
    }

    public void updatezan(int imgid) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("uid", LoginActivity.user.getUId());
                    jsonObject.put("imgid",imgid);
                    jsonObject.put("iszan",LoadMyCommentActivity.zans.get(imgid));
                    String resultData = JsonUtil.getJsonString(url + "updatezan", String.valueOf(jsonObject));
                    JSONObject j = new JSONObject(resultData);
                    String msg=j.getString("msg");
                    System.out.println(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}


