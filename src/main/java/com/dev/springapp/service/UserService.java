package com.dev.springapp.service;

import com.dev.springapp.model.User;

import java.util.List;

public interface UserService {

    void add(User user);

    List<User> listUsers();
}
