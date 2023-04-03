package com.example.userspringboot.service.impl;

import com.example.userspringboot.entity.User;

import java.util.List;

public interface UserServiceImpl {
    User createUser(User user);
    User updateUser(User user,Integer id);
    void deleteUser(Integer id);
    List<User> getListUser();

}
