package com.example.mygitsearchdrawer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel

public class Item {
    @SerializedName("login")
    @Expose
    String login;

    @SerializedName("avatar_url")
    @Expose
    String avatarUrl;

    @SerializedName("html_url")
    @Expose
    String htmlUrl;

    private String pushId;
    String index;




    public Item(){}

    public Item(String login, String avatarUrl, String htmlUrl){
        this.login = login;
        this.avatarUrl= getLargeImageUrl(avatarUrl);
        this.htmlUrl=htmlUrl;
        this.index = "not_specified";
    }
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl()
    {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getLargeImageUrl(String avatarUrl) {
        String largeImageUrl = avatarUrl.substring(0, avatarUrl.length() - 6).concat("o.jpg");
        return largeImageUrl;
    }
    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }


}
