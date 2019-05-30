package com.sunfusheng.vr.Comment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sunfusheng.vr.Load.StartActivity;
import com.sunfusheng.vr.MainActivity;
import com.sunfusheng.vr.R;
import com.sunfusheng.vr.model.ImgMsg;

public class CommentActivity extends AppCompatActivity {
    private TextView pinglunText;
    public static ImgMsg imgMsg;
    ImageView imageView;
    TextView tv_title;
    TextView tv_desc;
    TextView tv_comment;
    ImageView zan_img;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        InputTextMsgDialog inputTextMsgDialog = new InputTextMsgDialog(this, R.style.dialog_center);
        inputTextMsgDialog.setHint("说点什么吧！");
        int imgid = CommentLoading.imgid;
        for (ImgMsg i : StartActivity.imgMsgs) {
            if (i.imgid == imgid) {
                imgMsg = i;
                break;
            }
        }

        imageView = findViewById(R.id.imageView);
        tv_title = findViewById(R.id.tv_title);
        tv_desc = findViewById(R.id.tv_desc);
        tv_comment = findViewById(R.id.tv_comment);

        imageView.setImageBitmap(imgMsg.assetName);
        tv_title.setText(imgMsg.title);
        tv_desc.setText(imgMsg.desc);

        back = findViewById(R.id.backbtn);
        zan_img = findViewById(R.id.zan_img);
        pinglunText = findViewById(R.id.pingluntext);
        pinglunText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputTextMsgDialog.show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CommentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
