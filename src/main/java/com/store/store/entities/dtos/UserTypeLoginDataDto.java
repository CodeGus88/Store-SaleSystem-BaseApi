package com.store.store.entities.dtos;

import com.store.store.entities.UserType;

public class UserTypeLoginDataDto extends UserType {

    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
