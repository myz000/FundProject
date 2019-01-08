package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.AdminService;
import com.demo.service.TableService.Impl.InvestService;
import com.demo.service.TableService.Impl.TrendService;
import com.demo.service.TableService.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;
    @Autowired
    TrendService trendService;
    @Autowired
    InvestService investService;

    @RequestMapping(value = "/admin/lookUser")
    public String lookUsers(HttpSession session) {
        List<User> list = adminService.displayUsers("user");
        session.setAttribute("userlist", list);
        return "admin_lookUser";
    }

    @RequestMapping(value = "/admin/lookUnUser")
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

    @RequestMapping(value = "/admin/authorityServlet")
    public String reAuthor(String user, HttpSession session) {
        session.setAttribute("user", user);
        return "admin_authority";
    }

    @RequestMapping(value = "/admin/changePassword")
    public String rePassword(String user, HttpSession session) {
        session.setAttribute("user", user);
        return "admin_changePassword";
    }

    @PostMapping(value = "/admin/authority")
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
        return "redirect:/admin/lookUser";
    }

    @PostMapping(value = "/admin/adchange")
    public String adchange(HttpSession session, String password) {
        String username = (String) session.getAttribute("user");
        User user = userService.findUserByName(username);
        user.setPasswd(password);
        userService.saveUser(user);
        return "redirect:/admin/lookUser";
    }

    @PostMapping(value = "/admin/chauthor")
    public String chauthor(HttpSession session, String[] radio) {
        LinkedList<User> list = (LinkedList<User>) session.getAttribute("userlist");
        for (String a : radio) {
            User user = list.get(Integer.parseInt(a) - 1);
            user.setState(1);
            userService.saveUser(user);
        }
        return "redirect:/admin/lookUser";
    }

    @PostMapping(value = "/admin/deleteUser")
    public ResponseEntity deleteUser(
            @RequestParam("userName") String userName,
            HttpSession session
    ) {
        User u = userService.findUserByName(userName);
        User admin = userService.findUserByName((String) session.getAttribute("username"));
        HashMap m = new HashMap();
        if (u.getRole().equals("admin") || u.getRole().equals(admin.getRole())) {
            m.put("msg", "抱歉！您无权进行此操作！");
        } else {
            trendService.deleteTrendsByUserId(u.getId());
            investService.deleteInvestsByUserId(u.getId());
            userService.deleteUserById(u.getId());
            m.put("state", "1");
            m.put("msg", "删除成功！");
        }
        return ResponseEntity.ok(m);
    }

    @RequestMapping(value = "/admin/searchUser", method = RequestMethod.POST)
    public String searchUser(
            @RequestParam("searchText") String searchText,
            @RequestParam("Filter") String Filter,
            HttpSession session
    ) {
        List<User> userList = userService.findAllUsers();
        if (!searchText.equals("")) {
            if (Filter.equals("name")) {
                userList.removeIf(u -> !u.getUsername().contains(searchText));
            } else if (Filter.equals("id")) {
                userList.removeIf(u -> !u.getId().contains(searchText));
            } else if (Filter.equals("phone")) {
                userList.removeIf(u -> !u.getTelephone().contains(searchText));
            } else if (Filter.equals("email")) {
                userList.removeIf(u -> !u.getEmail().contains(searchText));
            }
        }
        session.setAttribute("userlist", userList);
        return "admin_lookUser";
    }
}
