package com.rogear.cloud.admineurekaclient.pojo;

import java.io.Serializable;

/**
 * Created by Rogear on 2020/3/5
 **/
public class User implements Serializable {

    private static final long serialVersionUID = 6137030878868321258L;
    private Long id;
    private String username;
    private String passwod;

    public User(Long id, String username, String passwod) {
        this.id = id;
        this.username = username;
        this.passwod = passwod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswod() {
        return passwod;
    }

    public void setPasswod(String passwod) {
        this.passwod = passwod;
    }
}
