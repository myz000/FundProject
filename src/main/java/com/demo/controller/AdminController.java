package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.AdminService;
import com.demo.service.TableService.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin_lookUser")
    public String lookUsers(HttpSession session) {
        List<User> list = adminService.displayUsers("user");
        session.setAttribute("userlist", list);
        return "admin_lookUser";
    }

    @RequestMapping(value = "/admin_lookUnUser")
    public String lookUnUser(HttpSession session) {
        List<User> list = adminService.displayUsers("user");
        List<User> list1 = new LinkedList<User>();
        for (User user : list) {
            if (user.getState() == 0) {
                list1.add(user);
            }
        }
        session.setAttribute("userlist", list1);
        return "admin_check";
    }

    @RequestMapping(value = "/authorityServlet")
    public String reAuthor(String user, HttpSession session) {
        session.setAttribute("user", user);
        return "admin_authority";
    }

    @RequestMapping(value = "/changePassword")
    public String rePassword(String user, HttpSession session) {
        session.setAttribute("user", user);
        return "admin_changePassword";
    }

    @PostMapping(value = "/authority")
    public String author(HttpSession session, String option) {
        String username = (String) session.getAttribute("user");
        User user = userService.findUserByName(username);
        if (option.equals("2")) {
            user.setState(2);
        }
        if (option.equals("1")) {
            user.setState(1);
        }
        userService.saveUser(user);
        return "redirect:/admin_lookUser";
    }

    @PostMapping(value = "/adchange")
    public String adchange(HttpSession session, String password) {
        String username = (String) session.getAttribute("user");
        User user = userService.findUserByName(username);
        user.setPasswd(password);
        userService.saveUser(user);
        return "redirect:/admin_lookUser";
    }

    @PostMapping(value = "/chauthor")
    public String chauthor(HttpSession session, String[] radio) {
        LinkedList<User> list = (LinkedList<User>) session.getAttribute("userlist");
        for (String a : radio) {
            User user = list.get(Integer.parseInt(a) - 1);
            user.setState(1);
            userService.saveUser(user);
        }
        return "redirect:/admin_lookUser";
    }
}
