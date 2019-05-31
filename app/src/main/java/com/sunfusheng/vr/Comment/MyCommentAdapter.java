package com.sunfusheng.vr.Comment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.sunfusheng.vr.R;
import com.sunfusheng.vr.model.Comment;
import com.sunfusheng.vr.model.Comment_name;

import java.util.ArrayList;

public class MyCommentAdapter extends RecyclerView.Adapter<MyCommentAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Comment_name> comment_names;
    public OnItemClickListener itemClickListener;

    public MyCommentAdapter(Context context, ArrayList<Comment_name> comment_names) {
        this.context = context;
        this.comment_names = comment_names;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyCommentAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.my_pinglun_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment_name comment_name = comment_names.get(position);
        holder.user.setText("我说：");
        holder.piece.setText(comment_name.getPinglun());
      /*  holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null)
                    itemClickListener.onItemClick(v, position);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return comment_names.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView user;
        TextView piece;
        ImageButton cancel;


        public ViewHolder(View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.username_text);
            piece = itemView.findViewById(R.id.pinglun_piece);
            //cancel=itemView.findViewById(R.id.pinglun_cancel);

        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

}
