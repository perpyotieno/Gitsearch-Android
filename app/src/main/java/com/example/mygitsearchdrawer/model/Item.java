package com.example.mygitsearchdrawer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel

public class Item {
    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;

    @SerializedName("html_url")
    @Expose
    private String htmlUrl;



    @SerializedName("followers")
    @Expose
    private int followers;
    @SerializedName("following")
    @Expose
    private int following;
    @SerializedName("public_repos")
    @Expose
    private int repositories;

public Item(){}

    public Item(String login, String avatarUrl, String htmlUrl, int followers, int following, int repositories){
        this.login = login;
        this.avatarUrl= avatarUrl;
        this.htmlUrl=htmlUrl;
        this.repositories=repositories;
        this.followers=followers;
        this.following=following;
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

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getRepositories() {
        return repositories;
    }

    public void setRepositories(int repositories) {
        this.repositories = repositories;
    }
}
