package com.demo.controller;

import com.demo.configuration.WebSecurityConfig;
import com.demo.entity.LoginTicket;
import com.demo.entity.User;
import com.demo.service.TableService.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
public class WelcomeController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/Logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpSession session) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("ticket")) {
                cookie.setMaxAge(0);
            }
        }
        User user = userService.findUserByName((String) session.getAttribute(WebSecurityConfig.SESSION_KEY));
        session.removeAttribute("username");
        return ResponseEntity.ok(user.getRole());
    }

    @RequestMapping(value = "/")
    public String welcome(HttpServletRequest request, HttpSession session) {
        Cookie[] cookies = request.getCookies();
        String role = "";
        String msg = (String) request.getAttribute("msg");
        if (msg != null) {
            request.setAttribute("msg", msg);
        } else {
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("ticket") && cookie.getMaxAge() > 0) {
                        System.out.println(cookie.getValue());
                        System.out.println(cookie.getMaxAge());
                        LoginTicket loginTicket = userService.findLoginTicketByTicket(cookie.getValue());
                        User user = userService.findUserById(loginTicket.getUserid());
                        role = user.getRole();
                        session.setAttribute(WebSecurityConfig.SESSION_KEY, user.getUsername());
                    }
                }
            }
        }
        if (session.getAttribute("username") != null) {
            User user = userService.findUserByName((String) session.getAttribute("username"));
            role = user.getRole();
        }

        System.out.println(role);
        if (role.equals("admin")) {
            System.out.println("Enter the admin view");
            return "redirect:/admin/lookUser";
        } else {
            return "Welcome";
        }
    }

    @PostMapping("/Login")
    public ResponseEntity<?> login(String Name, String Password, @RequestParam(value = "Check", required = false) String check, HttpSession session,
                                   HttpServletResponse response, HttpServletRequest request) {
        System.out.println(Name + " " + Password);
        Map<String, String> map = userService.login(Name, Password);

        if (map.containsKey("ticket")) {
            session.setAttribute(WebSecurityConfig.SESSION_KEY, Name);
            User user = userService.findUserByName(Name);
            map.put("stateCode", "200");
            map.put("role", user.getRole());
        } else {
            String msg = map.get("msg");
            System.out.println(msg);
            map.put("stateCode", "404");
            map.put("msg", msg);
        }
        if (check != null) {
            System.out.println("记住凭证");
            Cookie cookie = new Cookie("ticket", map.get("ticket"));
            cookie.setMaxAge(1000 * 3600 * 10);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return ResponseEntity.ok(map);
    }


    @PostMapping(value = "/Register")
    public ResponseEntity<?> register(String Name, String Password1, String Password2, String Phone, String Email, String Id,
                                      String Verification, HttpSession session, HttpServletRequest request, @RequestParam(value = "Sex") String radio) {
        User user = new User();
        user.setUsername(Name);
        Map map = new HashMap();
        if (Name.equals("")) {
            map.put("msg", "用户名不能为空！");
        } else if (Phone.equals("") || !Pattern.matches("(\\d{11})", Phone)) {
            map.put("msg", "请正确输入电话号码！");
        } else if (Email.equals("")) {
            map.put("msg", "请输入邮箱！");
        } else if (Id.equals("")) {
            map.put("msg", "用户身份证号不能为空！");
        } else if (!Pattern.matches("(\\d{17})(\\d|x|X)", Id)) {
            map.put("msg", "请输入合法身份证号!");
        } else if (Password1.equals("") || Password2.equals("")) {
            map.put("msg", "密码不能为空！");
        } else if (!Password1.equals(Password2)) {
            map.put("msg", "两次输入密码不一致！");
        } else if (Verification.equals("")) {
            map.put("msg", "请输入验证码！");
        } else if (!Verification.equals(session.getAttribute("PhoneVerifyCode"))) {
            map.put("msg", "验证码错误！");
        }

        if (map.get("msg") != null) {
            map.put("stateCode", 404);
            return ResponseEntity.ok(map);
        }
        user.setPasswd(Password1);
        user.setRole("user");
        user.setEmail(Email);
        user.setTelephone(Phone);
        if (radio.equals("男")) user.setSex("男");
        else user.setSex("女");
        user.setId(Long.parseLong(Id));
        System.out.println(user.getId());
        user.setState(0);
        Map<String, String> m = userService.register(user);
        if (m.containsKey("msg")) {
            map.put("msg", m.get("msg"));
            map.put("stateCode", 404);
        } else {
            map.put("stateCode", 200);
        }
        return ResponseEntity.ok(map);
    }
}
