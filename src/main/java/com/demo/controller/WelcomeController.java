package com.demo.controller;

import com.demo.configuration.WebSecurityConfig;
import com.demo.entity.LoginTicket;
import com.demo.entity.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
public class WelcomeController {
       @Autowired
       private UserService userService;

       @RequestMapping(value="/Logout")
       public String logout(HttpServletRequest request,HttpSession session){
              Cookie[] cookies=request.getCookies();
              for(Cookie cookie:cookies){
                     if(cookie.getName().equals("ticket")){
                          cookie.setMaxAge(0);
                     }
              }
              session.removeAttribute("username");
              return welcome(request,session);
       }

       @RequestMapping(value="/")
       public String welcome(HttpServletRequest request,HttpSession session){
              Cookie[] cookies=request.getCookies();
              String role="";
              String msg= (String) request.getAttribute("msg");
              if(msg!=null){
                     request.setAttribute("msg",msg);
              }
              for(Cookie cookie:cookies){
                     if(cookie.getName().equals("ticket")&&cookie.getMaxAge()>0){
                     System.out.println(cookie.getValue());
                     System.out.println(cookie.getMaxAge());
                     LoginTicket loginTicket=userService.findLoginTicketByTicket(cookie.getValue());
                     User user=userService.findUserById(loginTicket.getUserid());
                     role=user.getRole();
                     session.setAttribute(WebSecurityConfig.SESSION_KEY,user.getUsername());
                  }
              }
              if(session.getAttribute("username")!=null){
                     User user = userService.findUserByName((String) session.getAttribute("username"));
                     role=user.getRole();
              }

              System.out.println(role);
              if(role.equals("admin")) {
                     System.out.println("Enter the admin view");
                     return "redirect:/admin_lookUser";
              }else{
                     return "Welcome";
              }
       }

       @PostMapping("/LoginVerify")
       public String loginVerify(String Name, String Password, @RequestParam(value = "Check",required = false ) String check, HttpSession session,
                                 HttpServletResponse response,HttpServletRequest request){
              System.out.println(Name+" "+Password);
              Map<String,String> map=userService.login(Name,Password);
              if(map.containsKey("ticket")) {
                     session.setAttribute(WebSecurityConfig.SESSION_KEY,Name);

              }
              else{
                     String msg=map.get("msg");
                  //   System.out.println(msg);
                     request.setAttribute("msg",msg);
              }
              if(check!=null){
                     System.out.println("记住凭证");
                     Cookie cookie=new Cookie("ticket",map.get("ticket"));
                     cookie.setMaxAge(1000*3600*10);
                     cookie.setPath("/");
                     response.addCookie(cookie);
              }
             return welcome(request,session);
       }


       @PostMapping(value="/Register")
       public String register(String Name, String Password1, String Password2,String Phone, String Email, String Id,
                              HttpSession session,HttpServletRequest request,@RequestParam(value = "Sex") String radio){
              User user=new User();
              user.setUsername(Name);
              if(Name.equals("")){
                     request.setAttribute("msg","用户名不能为空！");
                     return welcome(request,session);
              }
              else if(Id.equals("")){
                     request.setAttribute("msg","用户身份证号不能为空！");
                     return welcome(request,session);
              }
              if(Password1.equals("")||Password2.equals(""))
              {
                    request.setAttribute("msg","密码不能为空！");
                    return welcome(request,session);
              }
              else if(!Password1.equals(Password2)){
                     request.setAttribute("msg","两次输入密码不一致！");
                     return welcome(request,session);
              }
              user.setPasswd(Password1);
              user.setRole("user");
              user.setEmail(Email);
              user.setTelephone(Phone);
              if(radio.equals("男")) user.setSex("男");
              else user.setSex("女");
              user.setId(Long.parseLong(Id));
              System.out.println(user.getId());
              user.setState(0);
              Map<String,String> map=userService.register(user);
              if(map.containsKey("msg")){
                     request.setAttribute("msg",map.get("msg"));
              }
              else{
                     request.setAttribute("msg","注册成功！");
                //     session.setAttribute(WebSecurityConfig.SESSION_KEY,Name);
              }
              return welcome(request,session);
       }
}
