package com.example.userspringboot.service.impl;

import com.example.userspringboot.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserServiceCallImpl {
    User createUser(User user);
    User updateUser(User user,Integer id);
    void deleteUser(Integer id);
    List<User> getListUser();
    User getUser(Integer id);

    //void delete(Integer id);

    List<User> addListUser(List<User>userList);
    void uploadFile(MultipartFile file);
    List<User> getUserHome(Integer id);
}
