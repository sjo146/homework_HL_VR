package com.sunfusheng.vr.Comment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import com.sunfusheng.vr.R;

public class CommentActivity extends AppCompatActivity {
    private EditText pinglunText;
    public static int imgid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        InputTextMsgDialog inputTextMsgDialog = new InputTextMsgDialog(this, R.style.dialog_center);
        imgid=getIntent().getIntExtra("imgid",-1);
        pinglunText = findViewById(R.id.pingluntext);
        pinglunText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputTextMsgDialog.show();
            }
        });
    }
}
