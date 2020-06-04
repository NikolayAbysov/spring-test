package com.dev.springapp.dao;

import com.dev.springapp.model.User;
import java.util.List;

public interface UserDao {
    
    User get(Long id);

    void add(User user);

    List<User> listUsers();

}
