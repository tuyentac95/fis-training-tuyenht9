package com.example.userspringboot.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "`name`")
    private String name;
    @Column(name = "`age`")
    private int age;
    @Column(name = "`add`")
    private String add;
//    @Column(name = "`idhome`")
//    private int idhome;
//    @ManyToOne
//    @JoinColumn(name = "`idhome`")
//    private Home home;
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", add='" + add + '\'' +
//                ", idhome=" + idhome +
                '}';
    }
}
