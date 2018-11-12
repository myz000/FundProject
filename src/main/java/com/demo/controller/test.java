package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.UserService;

public class test {

    public static void main(String[] args) throws Exception {
        UserService userService=new UserService();
        String username = "myz";
        long userid=11;
        User user=userService.findUserByName("11");
       // User user=userService.findUserById(userid);
        if(user==null)
        System.out.println("null");

    }
}
