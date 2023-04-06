package com.example.userspringboot.service;

import com.example.userspringboot.entity.User;
import com.example.userspringboot.service.impl.UserServiceCallImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UseServiceCall implements UserServiceCallImpl {
    private static DataSource dataSource=new DataSource();
    private static Logger LOG= LogManager.getLogger(UseServiceCall.class.getName());
    public static class DataSource {
        private final HikariDataSource ds;

        public DataSource() {
            HikariConfig config = new HikariConfig();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Class.forName("com.zaxxer.hikari.HikariConfig");
                String connectString = "jdbc:mysql://localhost/user?autoReconnect=true&useSSL=false";
                System.out.println(connectString);
                config.setJdbcUrl(connectString);
                config.setUsername("root");
                config.setPassword("");
                config.setMaximumPoolSize(10);
                config.addDataSourceProperty("cachePrepStmts", "true");
                config.addDataSourceProperty("prepStmtCacheSize", "250");
                config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
                config.setConnectionTimeout(1000);
                config.setConnectionTimeout(1000);
                config.setLeakDetectionThreshold(10);
                config.setAutoCommit(true);
                ds = new HikariDataSource(config);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }


        public Connection getConnection() throws SQLException {

            return ds.getConnection();
        }

        public void close() {
            this.ds.close();
        }

    }

    @Override
    public User createUser(User user) {
        String query = "insert into `user`(id,name,age,add) values (?,?,?,?);";

        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getAdd());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User updateUser(User user, Integer id) {
        try {
            Connection connection = dataSource.getConnection();
            String query = "update user set `name`=?, `age`=?,`add`=? where id=" + id + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setString(3, user.getAdd());
            preparedStatement.executeUpdate();
            dataSource.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

//    @Override
//    public void deleteUser(Integer id) {
//        try {
//            Connection connection= dataSource.getConnection();
//            String query= "delete from user where id="+id+";";
//            PreparedStatement preparedStatement=connection.prepareStatement(query);
//            preparedStatement.executeUpdate();
//            dataSource.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public List<User> getListUser() {
        List<User> listUser = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from `user`");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String add = rs.getString("add");
                LOG.debug("get id user: ",id);
                LOG.debug("get name user: ",name);
                LOG.debug("get age user: ",age);
                LOG.debug("get add user: ",add);
                System.out.println("address"+add);
                User user=new User(id,name,age,add);
                listUser.add(user);

            }
            dataSource.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listUser;
    }

    @Override
    public User getUser(Integer id) {
        User user = null;
        String query = "{CALL get_user_by_id(?)}";
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)) {
            callableStatement.setInt(1, id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String add = rs.getString("add");
                user = new User(id, name, age, add);
            }
            dataSource.close();
        } catch (SQLException e) {
        }
        return user;
    }
    @Override
    public void deleteUser(Integer id) {
        try {
            Connection connection = dataSource.getConnection();
            String query = "{CALL delete_user_by(?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();
            dataSource.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public User getUser(Integer id) {
//        User user;
//        try {
//            Connection connection= dataSource.getConnection();
//            String query= "select * from user where id="+id+";";
//            PreparedStatement preparedStatement=connection.prepareStatement(query);
//            ResultSet rs= preparedStatement.executeQuery();
//            user=new User(rs.getInt("id"),rs.getString("name"),rs.getInt("age"),rs.getString("add"));
//            dataSource.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return user;
//    }

    @Override
    public List<User> addListUser(List<User> userList) {
        String query = "insert into `user`(id,name,age,add) values (?,?,?,?);";
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (User user : userList
            ) {
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setInt(3, user.getAge());
                preparedStatement.setString(4, user.getAdd());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    @Override
    public void uploadFile(MultipartFile file) {
           //todo
    }

    @Override
    public List<User> getUserHome(Integer id) {
        return null;
    }
}
