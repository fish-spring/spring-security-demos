package com.spring4all.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal Principal principal){
        return String.format("你好 %s, 欢迎回来！", principal.getName());
    }

    @GetMapping("/vip")
    public String vip(){
        return String.format("尊敬的VIP，很高兴为您服务");
    }

}
