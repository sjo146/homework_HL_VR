package com.sunfusheng.vr.model;

public class Comment_name {
    private int Cid;
    private int Imgid;
    private int Uid;
    private String Pinglun;
    private String UUsername;

    public String getUUsername() {
        return UUsername;
    }

    public void setUUsername(String UUsername) {
        this.UUsername = UUsername;
    }

    public int getCid() {
        return Cid;
    }

    public void setCid(int cid) {
        Cid = cid;
    }

    public int getImgid() {
        return Imgid;
    }

    public void setImgid(int imgid) {
        Imgid = imgid;
    }

    public int getUid() {
        return Uid;
    }

    public void setUid(int uid) {
        Uid = uid;
    }

    public String getPinglun() {
        return Pinglun;
    }

    public void setPinglun(String pinglun) {
        Pinglun = pinglun;
    }
}
