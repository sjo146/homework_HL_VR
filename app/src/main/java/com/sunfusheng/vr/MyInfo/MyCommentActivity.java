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
import com.sunfusheng.vr.Load.LoadMyCommentActivity;
import com.sunfusheng.vr.Load.StartActivity;
import com.sunfusheng.vr.R;
import com.sunfusheng.vr.about.AboutActivity;
import com.sunfusheng.vr.adapter.MyCommentAdapter;
import com.sunfusheng.vr.model.ImgMsg;
import com.sunfusheng.vr.utils.ImageUtil;

import java.util.Random;

public class MyCommentActivity extends AppCompatActivity {

    private ImageView ivMine;
    private TextView tvTitle;
    public VrPanoramaView vrPanoramaView;
    private RecyclerView recyclerView;
    private int currPosition = 0;
    private MyCommentAdapter tAdapter;
    private ViewPager pager;
    private PagerSlidingTabStrip tabs;
    private Button seeAll;
    private ImageButton back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comment);
        initView();
        initListener();
    }

    private void initView() {
        ivMine = findViewById(R.id.iv_mine);
        tvTitle = findViewById(R.id.tv_title);
        back=findViewById(R.id.btn_back);
        seeAll=findViewById(R.id.seeAll);
        ImageUtil.colorImageViewDrawable(ivMine, R.color.transparent60_white);
        vrPanoramaView = findViewById(R.id.vrPanoramaView);
        vrPanoramaView.setTouchTrackingEnabled(true);
        vrPanoramaView.setFullscreenButtonEnabled(true);
        vrPanoramaView.setInfoButtonEnabled(false);
        vrPanoramaView.setStereoModeButtonEnabled(false);
        //seeAll=findViewById(R.id.button3);
        currPosition = new Random().nextInt(LoadMyCommentActivity.imgMsgs.size());
        ImgMsg imgMsg = LoadMyCommentActivity.imgMsgs.get(currPosition);
        loadImgMsg(imgMsg);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);



        recyclerView = findViewById(R.id.recyclerView);
        tAdapter = new MyCommentAdapter(this,LoadMyCommentActivity.imgMsgs);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(tAdapter);

        /*seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MyCommentActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });*/


    }


    private void initListener() {
        ivMine.setOnClickListener(v -> {
            startActivity(new Intent(this, MyInfoActivity.class));
        });
        back.setOnClickListener(v -> {
            startActivity(new Intent(this, MyInfoActivity.class));
        });
        seeAll.setOnClickListener(v -> {
            startActivity(new Intent(this, StartActivity.class));
        });

        tAdapter.setOnItemClickListener(new MyCommentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (currPosition == position) return;
                currPosition = position;
                ImgMsg imgMsg = LoadMyCommentActivity.imgMsgs.get(currPosition);
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
