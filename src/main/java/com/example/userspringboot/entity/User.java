package com.example.userspringboot.entity;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.springframework.data.annotation.Id;

import javax.persistence.*;

//import org.springframework.data.annotation.Id;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="USERS")
public class User {
//    @Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private int age;
    private String add;


}
