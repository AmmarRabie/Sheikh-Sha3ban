package com.products.ammar.sheikhsha3ban.common.data.model;


import java.io.Serializable;

/**
 * Created by AmmarRabie on 02/03/2018.
 */

/**
 * Data representation of a user in the system (name-email-id-profileImage)
 */
public class UserModel implements Serializable {

    private String id;
    private String name;
    private String email;
    private String profileImage;


    public UserModel(String id, String name, String email, String profileImage) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
    }

    public UserModel(String name, String email, String id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public UserModel(UserModel user) {
        this.id = user.id;
        this.name = user.name;
        this.email = user.email;
        this.profileImage = user.profileImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
