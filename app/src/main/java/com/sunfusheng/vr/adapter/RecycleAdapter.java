package com.sunfusheng.vr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.vr.sdk.widgets.common.TouchTracker;
import com.sunfusheng.vr.R;
import com.sunfusheng.vr.StartActivity;
import com.sunfusheng.vr.model.ImgMsg;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    public ArrayList<ImgMsg> imgMsgs;
    private Context context;
    public OnItemClickListener itemClickListener;

    public RecycleAdapter(Context context) {
        super();
        this.imgMsgs = StartActivity.imgMsgs;
    }
    public RecycleAdapter(Context context,ArrayList<ImgMsg> imgMsgs) {
        this.context=context;
        this.imgMsgs = StartActivity.imgMsgs;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv_title;
        TextView tv_desc;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_desc = itemView.findViewById(R.id.tv_desc);

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
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_panorana_image, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImgMsg imgMsg = imgMsgs.get(position);
        holder.imageView.setImageBitmap(imgMsg.assetName);
        holder.tv_title.setText(imgMsg.title);
        holder.tv_desc.setText(imgMsg.desc);
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

    }

    @Override
    public int getItemCount() {
        return imgMsgs != null ? imgMsgs.size() : 0;
    }


}


