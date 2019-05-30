package com.sunfusheng.vr.model;

public class User {
    int UId;
    String UUsername;
    String UPassword;
    String UPersonal;

    public int getUId() {
        return UId;
    }

    public void setUId(int UId) {
        this.UId = UId;
    }

    public String getUUsername() {
        return UUsername;
    }

    public void setUUsername(String UUsername) {
        this.UUsername = UUsername;
    }

    public void setUPassword(String UPassword) {
        this.UPassword = UPassword;
    }

    public String getUPassword() {
        return UPassword;
    }

    public String getUPersonal() {
        return UPersonal;
    }

    public void setUPersonal(String UPersonal) {
        this.UPersonal = UPersonal;
    }
}
