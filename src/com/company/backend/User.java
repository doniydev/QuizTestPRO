package com.company.backend;

import com.company.backend.enums.Role;

public class User extends GenericClass{
    private String id;
    private String name;
    private String password;
    private Role role;


    public User(String name, String password, Role role) {
        this.id = uuid.toString();
        this.name = name;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                "} ";
    }
}
