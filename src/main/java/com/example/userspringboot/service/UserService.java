package com.example.userspringboot.service;

import com.example.userspringboot.entity.User;
import com.example.userspringboot.repository.UserRepository;
import com.example.userspringboot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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

    @Override
    public User getUser(Integer id) {
        return userServiceImpl.findById(id).orElse(null);
    }

    @Override
    public List<User> addListUser(List<User> userList) {
        for (User user:userList
             ) {
            userServiceImpl.save(user);
        }
        return getListUser();
    }


    public void uploadFile(MultipartFile file) {
        String line ="";
        try{
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(file.getInputStream()));
            if(bufferedReader==null){
                bufferedReader=new BufferedReader(new FileReader("C:\\Users\\tuyen\\Downloads\\demo.csv"));
            }
            while ((line = bufferedReader.readLine())!=null){
                  String[] arr= line.split(",");
                  User user=new User();
                  user.setId(Integer.valueOf(arr[0]));
                  user.setName(arr[1]);
                  user.setAdd(arr[3]);
                  user.setAge(Integer.parseInt(arr[2]));
                //System.out.println(user.toString());
                  userServiceImpl.save(user);

            }
        }catch (NullPointerException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
