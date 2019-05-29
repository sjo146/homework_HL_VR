package com.sunfusheng.vr.model;

import android.graphics.Bitmap;
import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ImgMsg  implements MultiItemEntity {
    public int imgid;
    public int type;
    public String title;
    public String desc;
    public Bitmap assetName;

    public ImgMsg(int imgid,int type, String title, String desc, Bitmap assetName) {
        this.imgid=imgid;
        this.type = type;
        this.title = title;
        this.desc = desc;
        this.assetName = assetName;
    }
    @Override
    public int getItemType() {
        return type;
    }
}
