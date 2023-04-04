package com.example.userspringboot.controller;

import com.example.userspringboot.entity.User;
import com.example.userspringboot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @PostMapping
    public User crateUser(@RequestBody User user){
        return userServiceImpl.createUser(user);
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user){
        return userServiceImpl.updateUser(user,id);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        userServiceImpl.deleteUser(id);
    }
    @GetMapping
    public List<User> getListUser(){
        return userServiceImpl.getListUser();
    }
    @PostMapping("/loadfile")
    public void updateUserFromFile(){
        userServiceImpl.uploadFile();
    }

}
