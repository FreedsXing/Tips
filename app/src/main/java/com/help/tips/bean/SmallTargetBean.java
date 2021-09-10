package com.help.tips.bean;

public class SmallTargetBean {

    private Long id;

    private String userId;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
