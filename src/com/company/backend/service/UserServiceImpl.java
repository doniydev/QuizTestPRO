package com.company.backend.service;

import com.company.backend.enums.Role;

public class UserServiceImpl implements UserService {
    private static int size = 0;

    static {
        list[size++] = new String[]{"Doniyor","12345", Role.TEACHER.name()};
        list[size++] = new String[]{"Doniy","12345", Role.STUDENT.name()};
        list[size++] = new String[]{"Doni","12345", Role.STUDENT.name()};
        list[size++] = new String[]{"Don","12345", Role.STUDENT.name()};
        list[size++] = new String[]{"Do","12345", Role.TEACHER.name()};

    }

    @Override
    public String register(String username, String password) {
        if (size == list.length) {
            return "User list is full";
        }
        if (username == null || username.isBlank()) {
            return "Username is required";
        }
        if (password == null || password.isBlank()) {
            return "Password is required";
        }
        for (int i = 0; i < size; i++) {
            if (list[i][0].equalsIgnoreCase(username)) {
                return "User already exist";
            }
        }
        list[size++] = new String[]{username, password, Role.STUDENT.name()};

        return "User successfully registered";
    }

    @Override
    public String login(String username, String password) {
        if (username == null || username.isBlank()) {
            return "Username is required";
        }
        if (password == null || password.isBlank()) {
            return "Password is required";
        }
        for (int i = 0; i < size; i++) {
            if (list[i][0].equalsIgnoreCase(username) &&
                    list[i][1].equals(password)) {
                return list[i][2];
            }
        }
        return "Username or password is wrong";
    }
}
