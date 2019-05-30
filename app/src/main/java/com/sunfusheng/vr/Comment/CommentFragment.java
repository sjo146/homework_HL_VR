package com.sunfusheng.vr.Comment;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sunfusheng.vr.R;


public class CommentFragment extends Fragment {
    private RecyclerView recyclerView;
    private CommentRecycleViewAdapter commentRecycleViewAdapter;
    private View rootview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (CommentLoading.isgetPinglun == false)
            rootview = inflater.inflate(R.layout.nocomment, container, false);
        else {
            rootview = inflater.inflate(R.layout.fragment_comment_display, container, false);
            recyclerView = rootview.findViewById(R.id.pinglundisplay);
            commentRecycleViewAdapter = new CommentRecycleViewAdapter(rootview.getContext(), CommentLoading.comment_names);
            LinearLayoutManager layoutManager = new LinearLayoutManager(rootview.getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(commentRecycleViewAdapter);
        }
        return rootview;
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


}
