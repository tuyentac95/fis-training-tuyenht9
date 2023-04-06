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
        //user.setFavorite(user.getFavorite().toString());
        //System.out.println(user.getFavorite());
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
        //user1.setIdhome(user.getIdhome());
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
        userServiceImpl.saveAll(userList);
        return getListUser();
    }


    public void uploadFile(MultipartFile file) {
        String line ="";

        try{
            File convFile = new File(file.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
            BufferedReader bufferedReader=new BufferedReader(new FileReader(convFile));
//            if(bufferedReader==null){
//                bufferedReader=new BufferedReader(new FileReader("C:\\Users\\tuyen\\Downloads\\demo.csv"));
//            }
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
            //lưu 1 list
//            File file1 = new File("C:\\Users\\tuyen\\AppData\\Local\\Temp\\tomcat.8080.5249333685723584792\\work\\Tomcat\\localhost\\ROOT");
//            file1.delete();
        }catch (NullPointerException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public List<User> getUserHome(Integer id) {
//        List<User> listUser=userServiceImpl.getUser(id);
//        return listUser;
//    }
    //Log4j / Sf4j
    //Format output in success/error
    //Call procedure in DB (in & out param)
    //Connection DB: new Conn -> query -> Close; create conn pools

}
