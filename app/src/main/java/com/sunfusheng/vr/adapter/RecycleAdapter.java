package com.sunfusheng.vr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sunfusheng.vr.R;
import com.sunfusheng.vr.model.ImgMsg;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private ArrayList<ImgMsg> imgMsgs;
    private Context context;


    public RecycleAdapter(ArrayList<ImgMsg> imgMsgs, Context context) {
        this.imgMsgs = imgMsgs;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tv_title;
        TextView  tv_desc;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            tv_title=itemView.findViewById(R.id.tv_title);
            tv_desc=itemView.findViewById(R.id.tv_desc);

        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_panorana_image,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImgMsg imgMsg=imgMsgs.get(position);
        holder.imageView.setImageBitmap(imgMsg.assetName);
        holder.tv_title.setText(imgMsg.title);
        holder.tv_desc.setText(imgMsg.desc);
    }

    @Override
    public int getItemCount() {
        return imgMsgs!=null?imgMsgs.size():0;
    }



}


