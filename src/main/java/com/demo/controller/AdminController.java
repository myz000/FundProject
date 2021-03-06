package com.demo.controller;

import com.demo.entity.Inform;
import com.demo.entity.User;
import com.demo.service.TableService.Impl.InformService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    TrendService trendService;
    @Autowired
    InvestService investService;
    @Autowired
    InformService informService;

    @RequestMapping(value = "/admin/lookUser")
    public String lookUsers(HttpSession session) {
        List<User> list = userService.findAllUsers();
        User u = userService.findUserByName((String) session.getAttribute("username"));
        if (!u.getRole().equals("admin")) {
            list.removeIf(r -> r.getRole().equals("admin"));
        }
        session.setAttribute("userlist", list);
        return "admin_lookUser";
    }

    @RequestMapping(value = "/admin/lookUnUser")
    public String lookUnUser(HttpSession session) {
        List<User> list = userService.findAllUsers();
        list.removeIf(u -> u.getState() != 0);
        session.setAttribute("userlist", list);
        return "admin_check";
    }

    @RequestMapping(value = "/admin/to-authority")
    public String reAuthor(String username, HttpServletRequest request) {
        User u = userService.findUserByName(username);
        request.setAttribute("user", u);
        return "admin_authority";
    }

    @RequestMapping(value = "/admin/to-changePassword")
    public String rePassword(String username, HttpServletRequest request) {
        User u = userService.findUserByName(username);
        request.setAttribute("user", u);
        return "admin_changePassword";
    }

    @PostMapping(value = "/admin/authority")
    public String author(
            @RequestParam("username") String username,
            @RequestParam("Role") String Role,
            @RequestParam("State") String State
    ) {
        User user = userService.findUserByName(username);
        user.setRole(Role);
        user.setState(Integer.parseInt(State));
        userService.saveUser(user);
        return "redirect:/admin/lookUser";
    }

    @PostMapping(value = "/admin/changePassword")
    public ResponseEntity adchange(
            @RequestParam("username") String username,
            @RequestParam("password1") String password1,
            @RequestParam("password2") String password2
    ) {
        HashMap m = new HashMap();
        if (password1.equals("") || password2.equals("")) {
            m.put("msg", "密码不能为空!");
        } else if (!password1.equals(password2)) {
            m.put("msg", "两次输入密码不一致!");
        } else {
            User user = userService.findUserByName(username);
            user.setPasswd(password1);
            userService.saveUser(user);
            m.put("state", 1);
            m.put("msg", "修改成功！");
        }
        return ResponseEntity.ok(m);
    }

    @PostMapping(value = "/admin/checkUser")
    public String chauthor(HttpSession session, String[] radio) {
        ArrayList<User> list = (ArrayList<User>) session.getAttribute("userlist");
        if (radio != null && radio.length > 0) {
            for (String a : radio) {
                User user = list.get(Integer.parseInt(a) - 1);
                user.setState(1);
                userService.saveUser(user);
            }
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

    @RequestMapping(value = "/admin/searchUnUser", method = RequestMethod.POST)
    public String searchUnUser(
            @RequestParam("searchText") String searchText,
            @RequestParam("Filter") String Filter,
            HttpSession session
    ) {
        List<User> userList = userService.findAllUsers();
        userList.removeIf(u -> u.getState() != 0);
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
        return "admin_check";
    }

    @RequestMapping(value = "/admin/informs")
    public String getInforms(HttpServletRequest request) {
        List<Inform> informList = informService.getInformList();
        if (informList == null) {
            informList = Collections.emptyList();
        }
        request.setAttribute("informList", informList);
        return "admin_inform";
    }

    @RequestMapping(value = "/admin/to-add-inform", method = RequestMethod.GET)
    public String toAddInform() {
        return "admin_add_inform";
    }

    @RequestMapping(value = "/admin/inform-add", method = RequestMethod.POST)
    public ResponseEntity addInfrom(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "state") String state,
            HttpSession session
    ) {
        Inform inform = new Inform();
        inform.setTitle(title);
        inform.setContent(content);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        inform.setTime(df.format(date));
        inform.setAuthor((String) session.getAttribute("username"));
        inform.setState(Integer.parseInt(state));
        informService.saveInform(inform);
        return ResponseEntity.ok(true);
    }

    @RequestMapping(value = "/admin/inform_detail", method = RequestMethod.GET)
    public String getInformDetails(@RequestParam("id") Long id, HttpServletRequest request) {
        Inform inform = informService.findInformById(id);
        request.setAttribute("inform", inform);
        return "admin_inform_detail";
    }

    @RequestMapping(value = "/admin/to_updateInform", method = RequestMethod.GET)
    public String toUpdateInform(@RequestParam("id") Long id, HttpServletRequest request) {
        Inform inform = informService.findInformById(id);
        request.setAttribute("inform", inform);
        return "admin_update_inform";
    }

    @RequestMapping(value = "/admin/inform-update", method = RequestMethod.POST)
    public ResponseEntity updateInfrom(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "state") String state,
            HttpSession session
    ) {
        Inform inform = new Inform();
        inform.setId(id);
        inform.setTitle(title);
        inform.setContent(content);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        inform.setTime(df.format(date));
        inform.setAuthor((String) session.getAttribute("username"));
        inform.setState(Integer.parseInt(state));
        informService.saveInform(inform);
        return ResponseEntity.ok(true);
    }

    @RequestMapping(value = "/admin/delete-inform", method = RequestMethod.POST)
    public ResponseEntity deleteInform(@RequestParam("id") Long id) {
        informService.deleteInformById(id);
        return ResponseEntity.ok(true);
    }

    @RequestMapping(value = "/admin/searchInform", method = RequestMethod.POST)
    public String searchInform(
            @RequestParam("searchText") String searchText,
            @RequestParam("Filter") String Filter,
            HttpServletRequest request
    ) {
        List<Inform> informList = informService.getInformList();
        if (!searchText.equals("")) {
            if (Filter.equals("title")) {
                informList.removeIf(r -> !r.getTitle().contains(searchText));
            } else if (Filter.equals("author")) {
                informList.removeIf(r -> !r.getAuthor().contains(searchText));
            }
        }
        request.setAttribute("informList", informList);
        return "admin_inform";
    }
}
