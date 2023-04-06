package com.example.userspringboot.controller;

import com.example.userspringboot.entity.User;
import com.example.userspringboot.service.impl.UserServiceCallImpl;
import com.example.userspringboot.service.impl.UserServiceImpl;
import org.apache.commons.csv.CSVRecord;
import org.apache.coyote.Response;
import org.apache.juli.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    //static Logger logger=Logger.getLogger(UserController.class.getName());
    static Logger LOG= LogManager.getLogger(UserController.class.getName());

    @Autowired
    private UserServiceCallImpl userServiceImpl;

    @PostMapping
    public User crateUser(@RequestBody User user) {
        LOG.info("create user done!");
        LOG.debug("demo",user.toString());
        LOG.warn("create user",user.toString());
//        userServiceImpl.createUser(user);
        return userServiceImpl.createUser(user);
//        if(user1==null){
//            return new ResponseEntity<>("Failed", HttpStatus.FAILED_DEPENDENCY);
//        }
//        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        LOG.info("parameter id: ",id);
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

    }
    @GetMapping("/home/{id}")
    public ResponseEntity<?> getUserHome(@PathVariable Integer id){
        List<User> listUser=userServiceImpl.getUserHome(id);
        if(listUser.size()>0){
            return new ResponseEntity<List<User>>(listUser,HttpStatus.OK);
        }else {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }

    }

}
