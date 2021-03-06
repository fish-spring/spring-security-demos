package com.spring4all.controller;

import com.spring4all.entity.User;
import com.spring4all.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@AllArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping({"/", "/index", "/home"})
    public String root(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(User user){
        log.info("用户尝试注册" + user.toString());
        // 此处省略校验逻辑
        userService.insert(user);
        return "redirect:register?success";
    }

}
