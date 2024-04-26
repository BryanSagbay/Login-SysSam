package com.sam.LoginSysSam.Model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="user")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_user", length = 100)
    private String name_user;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "password", length = 100)
    private String password;

}

