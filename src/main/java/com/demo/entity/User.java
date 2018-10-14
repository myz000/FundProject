package com.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_user")
public class User implements Serializable {
    @Id
    private long id;
    private String username;
    private String passwd;
    private String role;
    private String telephone;
    private String email;
    private int state;
    private String salt;
    private String sex;



    public User(long id,String username,String passwd,String role,
                String telephone,String email,int state,String salt,String sex){
           this.id=id; this.username=username; this.passwd=passwd;
           this.role=role; this.telephone=telephone; this.email=email;
           this.state=state; this.salt=salt;
           this.sex=sex;
    }
    public User(){}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
