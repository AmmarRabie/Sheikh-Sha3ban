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
    private String phone;


    public UserModel(String id, String name, String email, String phone, String profileImage) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.profileImage = profileImage;
    }

    public UserModel(UserModel user) {
        this.id = user.id;
        this.name = user.name;
        this.email = user.email;
        this.phone = user.phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
