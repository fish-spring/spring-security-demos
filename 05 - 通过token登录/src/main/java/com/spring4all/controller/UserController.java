package com.spring4all.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Slf4j
@RestController
public class UserController {

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal Principal principal){
        return String.format("你好 %s", principal.getName());
    }

    @GetMapping("/vip")
    public String vip(@AuthenticationPrincipal Principal principal){
        return String.format("你好vip %s", principal.getName());
    }

    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal Principal principal){
        return String.format("你好admin %s", principal.getName());
    }

}
