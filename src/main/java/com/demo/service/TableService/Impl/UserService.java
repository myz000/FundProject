package com.demo.service.TableService.Impl;

import com.demo.entity.LoginTicket;
import com.demo.entity.User;
import com.demo.repository.TicketRepository;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketRepository ticketRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserByName(String username) {
        System.out.println("findUserByName");
        User user = null;
        try {
            user = userRepository.findByUserName(username);
            System.out.println("findUserByName");
            System.out.println("user:" + user);
        } catch (Exception e) {
            System.out.println(e + "");
        }
        return user;
    }

    public User findUserById(String id) {
        User user = null;
        try {
            user = userRepository.findById(id);
        } catch (Exception e) {
        }
        return user;
    }

    public User findUserByPhone(String tel) {
        User user = null;
        try {
            user = userRepository.findByTel(tel);
        } catch (Exception e) {
        }
        return user;
    }

    public LoginTicket findLoginTicketByTicket(String ticket) {
        LoginTicket loginTicket = null;
        try {
            loginTicket = ticketRepository.findByTicket(ticket);
        } catch (Exception e) {
        }
        return loginTicket;
    }

    public String addLoginTicket(String userId) {
        Random random = new Random();
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserid(userId);
        Date date = new Date();
        date.setTime(date.getTime() + 1000 * 3600 * 10);
        loginTicket.setExpired(date);
        loginTicket.setStatus(0);
        loginTicket.setTicket(UUID.randomUUID().toString().replace(",", ""));
        loginTicket.setId(random.nextLong());
        ticketRepository.save(loginTicket);
        return loginTicket.getTicket();
    }

    public Map<String, String> register(User user) {
        Map<String, String> map = new HashMap<String, String>();
        User u = userRepository.findByUserName(user.getUsername());
        if (u != null) {
            map.put("msg", "该用户已存在！");
            return map;
        }
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));//可以写加密
        saveUser(user);
        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);
        return map;
    }

    public Map<String, String> login(String username, String passwd) {
        Map<String, String> map = new HashMap<String, String>();
        if (username.isEmpty() && passwd.isEmpty()) {
            map.put("msg", "用户名、密码不能为空");
            return map;
        }


        if (username.isEmpty()) {
            map.put("msg", "用户名不能为空");
            return map;
        }

        if (passwd.isEmpty()) {
            map.put("msg", "密码不能为空");
            return map;
        }

        User u = userRepository.findByUserName(username);

        if (u == null) {
            map.put("msg", "用户名不存在");
            return map;
        }
        if (u.getState() == 0) {
            map.put("msg", "用户正在审核中！");
            return map;
        }
        if (u.getState() == 2) {
            map.put("msg", "用户账号已被冻结，请尽快与管理员联系！");
            return map;
        }

        if (!u.getPasswd().equals(passwd)) {
            map.put("msg", "密码错误");
            return map;
        }

        String ticket = addLoginTicket(u.getId());
        map.put("ticket", ticket);
        return map;
    }


    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void saveLoginTicket(LoginTicket loginTicket) {
        ticketRepository.save(loginTicket);
    }

    @Transactional
    public void deleteUserById(String id) {
        userRepository.delete(id);
    }
}
