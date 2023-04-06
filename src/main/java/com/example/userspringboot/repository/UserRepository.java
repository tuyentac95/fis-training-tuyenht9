package com.example.userspringboot.repository;

import com.example.userspringboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
//    @Query(value = "SELECT * FROM user WHERE idhome = :idhome", nativeQuery = true)
//    List<User> getUser(@Param("`idhome`") Integer idhome);
}
