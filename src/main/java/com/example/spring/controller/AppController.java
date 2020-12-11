package com.example.spring.controller;

import com.example.spring.model.User;
import com.example.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AppController {


    private UserService userService;

    public AppController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/admin")
    public String homePage(Model model) {
        model.addAttribute("user", userService.userList());
        return "admin";
    }

    @GetMapping(value = "/admin/{id}")
    public String show(@PathVariable("id") int id, Model model) {
//      получим юзера по id из DAO и передадим на представление
//        User user = userService.show(id);
        model.addAttribute("user", userService.show(id));
        return "show";
    }

    @PostMapping("/admin/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
