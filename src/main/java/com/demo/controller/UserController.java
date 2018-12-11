package com.demo.controller;

import com.demo.entity.apiBody.NoteBody;
import com.demo.entity.User;
import com.demo.service.Impl.NoteServiceImpl;
import com.demo.service.TableService.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.regex.Pattern;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private NoteServiceImpl noteServiceImpl = new NoteServiceImpl();

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

}
