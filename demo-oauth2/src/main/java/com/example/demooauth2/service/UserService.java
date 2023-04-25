package com.example.demooauth2.service;

import com.example.demooauth2.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findByUsername(String username);

    User save(User user);

    Boolean notExistEmail(String email);

    void changePassword(User u, String password);

    User findByEmail(String email);
}
