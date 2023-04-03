package com.example.userspringboot.service;

import com.example.userspringboot.entity.User;
import com.example.userspringboot.repository.UserRepository;
import com.example.userspringboot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceImpl {
    @Autowired
    private UserRepository userServiceImpl;
    @Override
    public User createUser(User user) {
        if(user.getName().isEmpty()||user.getName()==null){
            return null;
        }
        return userServiceImpl.save(user);
    }

    @Override
    public User updateUser(User user, Integer id) {
        User user1=userServiceImpl.findById(id).orElse(null);
        if (user1==null){
            return null;
        }
        user1.setAge(user.getAge());
        user1.setName(user.getName());
        user1.setAdd(user.getAdd());
        return userServiceImpl.save(user1);
    }

    @Override
    public void deleteUser(Integer id) {
        userServiceImpl.deleteById(id);
    }

    @Override
    public List<User> getListUser() {
        return userServiceImpl.findAll();
    }
}
