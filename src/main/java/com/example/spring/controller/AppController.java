package com.example.spring.controller;

import com.example.spring.model.Role;
import com.example.spring.model.User;
import com.example.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AppController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = "/user")
    public String printWelcome(Model model, Principal principal ) {
        String name = principal.getName();//get logged in username
        User user = userService.getUserByName(name);
        model.addAttribute("username", user);
        return "use";
    }

    @GetMapping(value = "/admin")
    public String printWelcomeAdmin(Model model, Principal principal ) {
        String name = principal.getName();//get logged in username
        User user = userService.getUserByName(name);
        model.addAttribute("userAuth", user);
        model.addAttribute("user", userService.index());
        return "admi";
    }

    @GetMapping(value = "/adminin")
    public String printAdmin(Model model, Principal principal ) {
        String name = principal.getName();//get logged in username
        User user = userService.getUserByName(name);
        model.addAttribute("username", user);
        return "adminin";
    }



//    @GetMapping("/admin")
//    public String getAdminPage(Model model) {
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User userAuthenficated = userService.getUserByName(userDetails.getUsername());
//        model.addAttribute("userAuthenficated", userAuthenficated);
//        model.addAttribute("user", userService.index());
//        return "admi";
//    }


//    @GetMapping(value = "/admin")
//    public String getAdminPage(Model model) {
//        //        получим всех юзеров из DAO и передадим на представление
//        model.addAttribute("user", userService.index());
//        return "admi";
//    }

//    @GetMapping(value = "/admin/{id}")
//    public String show(@PathVariable("id") int id, Model model) {
////      получим юзера по id из DAO и передадим на представление
//        User user = userService.show(id);
////        model.addAttribute("user", user);
//        model.addAttribute("user1", user);
////        return "show";
//        return "admi";
//    }

    @GetMapping(value = "/admin/new")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
//      возвращает html форму для создания нового юзера
//        return "new";
        return "admi";
    }


    @PostMapping(value = "/admin/new")
    public String create(@ModelAttribute("user") User user, @RequestParam("editRoles") String[] roles){
//        принимать на вход post запрос, создавать нового юзера, и добавлять в БД
        Set<Role> roleList = new HashSet<Role>();
        for(String r : roles) {
            roleList.add(userService.getRole(r));
        }
        user.setRoles(roleList);
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.show(id));
//        return "edit";
        return "admi";
    }

    @PostMapping(value = "/admin/{id}/edit")
    public String update(@ModelAttribute("user") User user, @RequestParam("editRoles") String[] roles) {
        Set<Role> roleList = new HashSet<Role>();;
        for(String r : roles) {
            roleList.add(userService.getRole(r));
        }
        user.setRoles(roleList);
        userService.update(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
