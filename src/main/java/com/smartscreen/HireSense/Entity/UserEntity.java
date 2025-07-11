package com.smartscreen.HireSense.Entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="UserEntity")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(unique=true)
    private String email;
    private String password;

    @OneToMany(mappedBy = "user" , orphanRemoval = true
            ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ResumeEntity> resume=new ArrayList<>();

    public void addResume(ResumeEntity resume) {
        resume.setUser(this);
        this.resume.add(resume);
    }
    public List<ResumeEntity> getResume() {
        return resume;
    }

    public long getId() {
        return id;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
}
