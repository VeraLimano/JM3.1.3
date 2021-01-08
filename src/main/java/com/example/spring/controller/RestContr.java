package com.example.spring.controller;

import com.example.spring.model.User;
import com.example.spring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import org.springframework.boot.actuate.trace.http.HttpTrace;

import java.security.Principal;
import java.util.*;

@RestController
public class RestContr {

    private UserService service;

    public RestContr(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> list() {
        return service.index();
    }


    @PostMapping("/usersAdd")
    public void add(@RequestBody User user) {
        service.save(user);
    }

    @PutMapping("/usersEdit")
    public void update(@RequestBody User user) {
        service.save(user);
    }

    @DeleteMapping("/usersDelete/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping("/userGet")
    public void getCurrentUser(Principal principal) {
        service.getByName(principal.getName());
    }

}
