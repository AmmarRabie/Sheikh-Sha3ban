package com.products.ammar.sheikhsha3ban.common.data.model;

import java.util.Date;

public class PostModel {
    private String id;
    private UserModel creator;
    private String body;
    private Date date;

    public PostModel(String id, UserModel creator, String body, Date date) {
        this.id = id;
        this.creator = creator;
        this.body = body;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserModel getCreator() {
        return creator;
    }

    public void setCreator(UserModel creator) {
        this.creator = creator;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
