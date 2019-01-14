package com.demo.controller;

import com.demo.entity.Inform;
import com.demo.entity.User;
import com.demo.entity.apiBody.NoteBody;
import com.demo.service.Impl.NoteServiceImpl;
import com.demo.service.TableService.Impl.InformService;
import com.demo.service.TableService.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private NoteServiceImpl noteServiceImpl = new NoteServiceImpl();
    @Autowired
    private InformService informService;

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }


    @RequestMapping(value = "/show", method = RequestMethod.GET)
    @ResponseBody
    public String show(@RequestParam(value = "name") String name) {
        User user = userService.findUserByName(name);
        if (null != user)
            return user.getId() + "/" + user.getUsername() + "/" + user.getPasswd();
        else return "null";
    }

    @RequestMapping(value = "/PhoneVerify")
    public ResponseEntity<?> phoneVerify(String Phone, HttpSession session) {
        if (Phone.equals("") || !Pattern.matches("(\\d{11})", Phone)) {
            HashMap map = new HashMap();
            map.put("error_code", 1);
            map.put("reason", "请输入有效电话号码！");
            return ResponseEntity.ok(map);
        }
        int code = (int) ((Math.random() * 9 + 1) * 1000);
        NoteBody note = noteServiceImpl.PhoneVerify(code, Phone);
        if (note.getError_code().equals("0"))
            session.setAttribute("PhoneVerifyCode", String.valueOf(code));
        return ResponseEntity.ok(note);
    }

    @RequestMapping(value = "/user/personal", method = RequestMethod.GET)
    public String personal(HttpServletRequest request, HttpSession session) {
        User u = userService.findUserByName((String) session.getAttribute("username"));
        request.setAttribute("User", u);
        return "Personal";
    }

    @PostMapping(value = "/user/change-password")
    public ResponseEntity changePsword(
            @Param("oldPassword") String oldPassword,
            @Param("Password1") String Password1,
            @Param("Password2") String Password2,
            HttpSession session
    ) {
        HashMap m = new HashMap();
        User u = userService.findUserByName((String) session.getAttribute("username"));
        if (Password1.equals("") || Password2.equals("") || oldPassword.equals("")) {
            m.put("msg", "内容不能为空！");
        } else if (!u.getPasswd().equals(oldPassword)) {
            m.put("msg", "旧密码输入错误！");
        } else if (!Password1.equals(Password2)) {
            m.put("msg", "两次输入新密码不一致！");
        } else if (oldPassword.equals(Password1)) {
            m.put("msg", "新密码不能和旧密码一样！");
        } else {
            u.setPasswd(Password1);
            userService.saveUser(u);
            m.put("state", "1");
            m.put("msg", "修改密码成功！");
        }
        return ResponseEntity.ok(m);
    }

    @PostMapping(value = "/user/change-info")
    public ResponseEntity changeInfo(
            @RequestParam("Name") String Name,
            @RequestParam("Phone") String Phone,
            @RequestParam("Email") String Email,
            @RequestParam(value = "Sex") String Sex,
            @RequestParam("Id") String Id,
            HttpSession session) {
        HashMap m = new HashMap();
        User u = userService.findUserByName((String) session.getAttribute("username"));
        if (Name.equals("") || Phone.equals("") || Email.equals("") || Sex.equals("") || Id.equals("")) {
            m.put("msg", "信息不能为空！");
        } else if (!Name.equals(u.getUsername()) && userService.findUserByName(Name) != null) {
            m.put("msg", "该用户名已存在！");
        } else if (!Id.matches("^[0-9]{17}[0-9]|X|x$")) {
            m.put("msg", "请输入正确的身份证号！");
        } else if (!Id.equals(u.getId()) && userService.findUserById(Id) != null) {
            m.put("msg", "该身份证号已被注册！");
        } else {
            u.setUsername(Name);
            u.setTelephone(Phone);
            u.setEmail(Email);
            u.setSex(Sex);
            userService.saveUser(u);
            m.put("state", "1");
            m.put("msg", "修改信息成功！");
        }
        return ResponseEntity.ok(m);
    }

    @RequestMapping(value = "/informs", method = RequestMethod.GET)
    public String getInforms(HttpServletRequest request) {
        List<Inform> informList = informService.getInformList();
        if (informList == null) {
            informList = Collections.emptyList();
        } else {
            informList.removeIf(r -> r.getState() != 1);
        }
        request.setAttribute("informList", informList);
        return "inform";
    }

    @RequestMapping(value = "/inform_detail", method = RequestMethod.GET)
    public String getInformDetails(@RequestParam("id") Long id, HttpServletRequest request) {
        Inform inform = informService.findInformById(id);
        request.setAttribute("inform", inform);
        return "inform_detail";
    }

    @RequestMapping(value = "/searchInform", method = RequestMethod.POST)
    public String searchInform(
            @RequestParam("searchText") String searchText,
            HttpServletRequest request
    ) {
        List<Inform> informList = informService.getInformList();
        if (!searchText.equals("")) {
            informList.removeIf(r -> !r.getTitle().contains(searchText));
        }
        request.setAttribute("informList", informList);
        return "inform";
    }
}
