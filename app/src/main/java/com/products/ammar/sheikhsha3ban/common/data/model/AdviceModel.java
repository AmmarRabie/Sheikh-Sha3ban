package com.products.ammar.sheikhsha3ban.common.data.model;

import java.util.Date;

public class AdviceModel {
    private String id;
    private UserModel creator;
    private String body;
    private Date date;

    public AdviceModel(String id, UserModel creator, String body, Date date) {
        this.id = id;
        this.creator = creator;
        this.body = body;
        this.date = date;
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

    public String getId() {
        return id;
    }
}

