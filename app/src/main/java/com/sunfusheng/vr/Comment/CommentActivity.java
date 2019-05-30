package com.sunfusheng.vr.Comment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.sunfusheng.vr.Load.StartActivity;
import com.sunfusheng.vr.R;
import com.sunfusheng.vr.model.ImgMsg;

public class CommentActivity extends AppCompatActivity {
    private TextView pinglunText;
    public static ImgMsg imgMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        InputTextMsgDialog inputTextMsgDialog = new InputTextMsgDialog(this, R.style.dialog_center);
        /*
        int imgid=getIntent().getIntExtra("imgid",-1);
       for(ImgMsg i:StartActivity.imgMsgs){
           if(i.imgid==imgid){imgMsg=i;break;}
       }

         */
        pinglunText = findViewById(R.id.pingluntext);
        pinglunText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputTextMsgDialog.show();
            }
        });
    }
}
