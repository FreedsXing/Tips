package com.help.tips.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class SmallTargetBean extends LitePalSupport {

    @Column(unique = true, defaultValue = "unknown", nullable = false)
    private Long id;

    @Column(unique = true, defaultValue = "unknown", nullable = false)
    private String userName;

    private String content;

    private int imgUrl;


    public int getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
