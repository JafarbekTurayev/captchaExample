package com.example.captchaexample.controller;

import cn.apiclub.captcha.Captcha;
import com.example.captchaexample.entity.User;
import com.example.captchaexample.service.UserService;
import com.example.captchaexample.util.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/register")
    public String registerUser(Model model) {
        User user = new User();
        getCaptcha(user);
        model.addAttribute("user", user);
        return "registerUser";
    }

    @PostMapping("/save")
    public String saveUser(
            @ModelAttribute User user,
            Model model
    ) {
        if (user.getCaptcha().equals(user.getHiddenCaptcha())) {
            service.createUser(user);
            model.addAttribute("message", "User Registered successfully!");
            return "redirect:allUsers";
        } else {
            model.addAttribute("message", "Captchani to'gri to'ldiring");
            getCaptcha(user);
            model.addAttribute("user", user);
        }
        return "registerUser";
    }

    @GetMapping("/allUsers")
    public String getAllUsers(Model model) {
        List<User> userList = service.getAllUsers();
        model.addAttribute("userList", userList);
        return "listUsers";
    }

    private void getCaptcha(User user) {
        Captcha captcha = CaptchaUtil.createCaptcha(100, 100);
        //captchani realniy javobi bo'lishi kerak
        user.setHiddenCaptcha(captcha.getAnswer());
        user.setCaptcha(""); // value entered by the User
        //captchadan string oldi
        user.setRealCaptcha(CaptchaUtil.encodeCaptcha(captcha));
    }
}
