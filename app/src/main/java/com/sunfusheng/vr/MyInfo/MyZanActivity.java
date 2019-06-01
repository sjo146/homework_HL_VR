package com.sunfusheng.vr.MyInfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.library.tabstrip.PagerSlidingTabStrip;
import com.sunfusheng.vr.Load.LoadZanActivity;
import com.sunfusheng.vr.Load.StartActivity;
import com.sunfusheng.vr.R;
import com.sunfusheng.vr.about.AboutActivity;
import com.sunfusheng.vr.adapter.MyZanAdapter;
import com.sunfusheng.vr.model.ImgMsg;
import com.sunfusheng.vr.utils.ImageUtil;

import java.util.Random;

public class MyZanActivity extends AppCompatActivity {

    private ImageView ivMine;
    private TextView tvTitle;
    public VrPanoramaView vrPanoramaView;
    private RecyclerView recyclerView;
    private int currPosition = 0;
    private MyZanAdapter tAdapter;
    private ViewPager pager;
    private PagerSlidingTabStrip tabs;
    private ImageButton back;

    private Button seeAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_zan);
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
        seeAll=findViewById(R.id.button2);
        back=findViewById(R.id.btn_back);
        currPosition = new Random().nextInt(LoadZanActivity.imgMsgs.size());
        ImgMsg imgMsg = LoadZanActivity.imgMsgs.get(currPosition);
        loadImgMsg(imgMsg);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);



        recyclerView = findViewById(R.id.recyclerView);
        tAdapter = new MyZanAdapter(this,LoadZanActivity.imgMsgs);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(tAdapter);

        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MyZanActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });


    }


    private void initListener() {
        ivMine.setOnClickListener(v -> {
            startActivity(new Intent(this, MyInfoActivity.class));
        });

        back.setOnClickListener(v -> {
            startActivity(new Intent(this, MyInfoActivity.class));
        });
        tAdapter.setOnItemClickListener(new MyZanAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (currPosition == position) return;
                currPosition = position;
                ImgMsg imgMsg = LoadZanActivity.imgMsgs.get(currPosition);
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
