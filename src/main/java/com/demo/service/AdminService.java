package com.demo.service;

import com.demo.entity.User;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    UserRepository userRepository;

    public List<User> displayUsers(String role){
        List<User> list=null;
        list=userRepository.findUsers(role);
        return list;
    }
}
