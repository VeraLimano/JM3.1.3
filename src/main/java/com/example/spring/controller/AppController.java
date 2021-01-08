package com.example.spring.controller;

import com.example.spring.model.Role;
import com.example.spring.model.User;
import com.example.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class AppController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = "/user")
    public String printWelcome(Model model, Principal principal ) {
        String name = principal.getName();//get logged in username
        User user = userService.getUserByName(name);
        model.addAttribute("username", user);
        return "user";
    }

    @GetMapping(value = "/admin")
    public String printWelcomeAdmin(Model model, Principal principal ) {
        String name = principal.getName();//get logged in username
        User user = userService.getUserByName(name);
        model.addAttribute("userAuth", user);
        model.addAttribute("user", userService.index());
        return "admin";
    }

    @GetMapping(value = "/adminin")
    public String printAdmin(Model model, Principal principal ) {
        String name = principal.getName();//get logged in username
        User user = userService.getUserByName(name);
        model.addAttribute("username", user);
        return "adminin";
    }
}

