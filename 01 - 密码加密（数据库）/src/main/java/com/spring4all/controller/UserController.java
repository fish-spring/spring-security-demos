package com.spring4all.controller;

import com.spring4all.repository.UserRepository;
import com.spring4all.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @GetMapping("/users")
    public Object user(@AuthenticationPrincipal Principal principal, Model model){

        return userRepository.findAll();
    }

}
