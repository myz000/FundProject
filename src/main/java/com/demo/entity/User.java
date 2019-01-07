package com.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tbl_user")
@Data
public class User implements Serializable {
    @Id
    private String id;
    private String username;
    private String passwd;
    private String role;
    private String telephone;
    private String email;
    private int state;
    private String salt;
    private String sex;


    public User(String id, String username, String passwd, String role,
                String telephone, String email, int state, String salt, String sex) {
        this.id = id;
        this.username = username;
        this.passwd = passwd;
        this.role = role;
        this.telephone = telephone;
        this.email = email;
        this.state = state;
        this.salt = salt;
        this.sex = sex;
    }

    public User() {
    }
}
