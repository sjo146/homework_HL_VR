package com.sunfusheng.vr.Comment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sunfusheng.vr.R;
import com.sunfusheng.vr.model.Comment;
import com.sunfusheng.vr.model.Comment_name;

import java.util.ArrayList;

public class CommentRecycleViewAdapter extends RecyclerView.Adapter<CommentRecycleViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Comment_name> comment_names;

    public CommentRecycleViewAdapter(Context context, ArrayList<Comment_name> comment_names) {
        this.context = context;
        this.comment_names = comment_names;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentRecycleViewAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.pinglunitem, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment_name comment_name = comment_names.get(position);
        holder.user.setText("用户："+comment_name.getUUsername());
        holder.piece.setText(comment_name.getPinglun());
    }

    @Override
    public int getItemCount() {
        return comment_names.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView user;
        TextView piece;


        public ViewHolder(View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.username_text);
            piece = itemView.findViewById(R.id.pinglun_piece);

        }
    }

}
