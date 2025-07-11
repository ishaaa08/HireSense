package com.smartscreen.HireSense.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="UserEntity")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(unique=true)
    private String email;
    private String password;

}
