package com.main.pubmanagement.dao;

import com.main.pubmanagement.model.User;

public interface UserDAO {
    User login(String username , String password);

    long     register(User user);
}
