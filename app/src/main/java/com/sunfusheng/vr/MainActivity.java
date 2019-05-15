package com.sunfusheng.vr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import com.sunfusheng.vr.about.AboutActivity;
import com.sunfusheng.vr.adapter.RecycleAdapter;
import com.sunfusheng.vr.model.ImgMsg;
import com.sunfusheng.vr.utils.ImageUtil;


import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView ivMine;
    private TextView tvTitle;
    public VrPanoramaView vrPanoramaView;
    private RecyclerView recyclerView;
    private int currPosition = 0;
    private RecycleAdapter recycleAdapter;
    private String urlString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        ivMine = findViewById(R.id.iv_mine);
        tvTitle = findViewById(R.id.tv_title);
        ImageUtil.colorImageViewDrawable(ivMine, R.color.transparent60_white);

        vrPanoramaView = findViewById(R.id.vrPanoramaView);
        vrPanoramaView.setTouchTrackingEnabled(true);
        vrPanoramaView.setFullscreenButtonEnabled(true);
        vrPanoramaView.setInfoButtonEnabled(false);
        vrPanoramaView.setStereoModeButtonEnabled(false);

        currPosition = new Random().nextInt(StartActivity.imgMsgs.size());
        ImgMsg imgMsg = StartActivity.imgMsgs.get(currPosition);

        loadImgMsg(imgMsg);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView = findViewById(R.id.recyclerView);
        recycleAdapter = new RecycleAdapter(this,StartActivity.imgMsgs);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);


    }


    private void initListener() {
        ivMine.setOnClickListener(v -> {
            startActivity(new Intent(this, AboutActivity.class));
        });

        recycleAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (currPosition == position) return;
                currPosition = position;
                ImgMsg imgMsg = StartActivity.imgMsgs.get(currPosition);
                loadImgMsg(imgMsg);
            }
        });

    }

    private void loadImgMsg(ImgMsg model) {
        loadPanoramaImage(model.assetName);
        tvTitle.setText(model.title);
    }

    private void loadPanoramaImage(Bitmap bitmap) {
        if (bitmap == null) return;
        VrPanoramaView.Options options = new VrPanoramaView.Options();
        options.inputType = VrPanoramaView.Options.TYPE_MONO;
        vrPanoramaView.loadImageFromBitmap(bitmap, options);
    }

    @Override
    protected void onResume() {
        super.onResume();
        vrPanoramaView.resumeRendering();
    }

    @Override
    protected void onPause() {
        super.onPause();
        vrPanoramaView.pauseRendering();
    }

    @Override
    protected void onDestroy() {
        vrPanoramaView.shutdown();
        super.onDestroy();
    }
}
