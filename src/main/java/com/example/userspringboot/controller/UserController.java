package com.example.userspringboot.controller;

import com.example.userspringboot.entity.User;
import com.example.userspringboot.service.impl.UserServiceImpl;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping
    public User crateUser(@RequestBody User user) {
        return userServiceImpl.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        return userServiceImpl.updateUser(user, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userServiceImpl.deleteUser(id);
    }

    @GetMapping
    public List<User> getListUser() {
        return userServiceImpl.getListUser();
    }
    @PostMapping("/updatelist")
    public List<User> updateList(@RequestBody List<User>userList){
        return userServiceImpl.addListUser(userList);
    }
    //    @PostMapping("/loadfile")
//    public void updateUserFromFile(){
//        userServiceImpl.uploadFile();
//    }
    @PostMapping("/uploadcsv")
    public void uploadCSV(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return;
        }
        userServiceImpl.uploadFile(file);
//        File file1 = new File("C:\\Users\\tuyen\\AppData\\Local\\Temp\\tomcat.8080.5249333685723584792\\work\\Tomcat\\localhost\\ROOT");
//        file1.delete();
    }

}
