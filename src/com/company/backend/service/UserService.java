package com.company.backend.service;

public interface UserService {
    String[][] list =new String[10][3];

    String register(String username, String password);
    String login(String username, String password);

}
