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

import java.util.ArrayList;

public class CommentRecycleViewAdapter extends RecyclerView.Adapter<CommentRecycleViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Comment> comments;

    public CommentRecycleViewAdapter(Context context, ArrayList<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentRecycleViewAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.pinglunitem, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.user.setText("热心网友" + comment.getUid());
        holder.piece.setText(comment.getPinglun());
    }

    @Override
    public int getItemCount() {
        return comments.size();
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
