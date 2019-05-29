package com.sunfusheng.vr.model;

public class Zan {
private int Imagid;
private Boolean Iszan;
private int Uid;


    public Zan(int imagid, Boolean iszan, int uid) {
        Imagid = imagid;
        Iszan = iszan;
        Uid=uid;
    }
    public int getUid() {
        return Uid;
    }

    public void setUid(int uid) {
        Uid = uid;
    }

    public int getImagid() {
        return Imagid;
    }

    public void setImagid(int imagid) {
        Imagid = imagid;
    }

    public Boolean getIszan() {
        return Iszan;
    }

    public void setIszan(Boolean iszan) {
        Iszan = iszan;
    }
}
