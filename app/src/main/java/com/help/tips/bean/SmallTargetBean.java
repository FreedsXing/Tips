package com.help.tips.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class SmallTargetBean extends LitePalSupport {

    @Column(unique = true, defaultValue = "unknown", nullable = false)
    private int id;

    @Column(unique = true, defaultValue = "unknown", nullable = false)
    private String targetName;

    private int finishDay = 0; //看电影为周

    private int imgUrl;

    private boolean isLike = true;


    public int getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public int getFinishDay() {
        return finishDay;
    }

    public void setFinishDay(int finishDay) {
        this.finishDay = finishDay;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }
}
