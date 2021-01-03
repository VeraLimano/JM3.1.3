package com.example.spring.controller;

import com.example.spring.model.Role;
import com.example.spring.model.User;
import com.example.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import org.springframework.boot.actuate.trace.http.HttpTrace;

import java.util.*;

@RestController
public class RestContr {

    @Autowired
    UserService service;

    @GetMapping("/rest")
    public List<User> list() {
        return service.index();
    }


    @GetMapping("/rest/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        try {
            User user = service.show(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        try {
            service.update(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "/newUser")
    public ResponseEntity<User> create(@RequestBody User user) {
        Set<Role> roleList = new HashSet<Role>();
        for (Role r : user.getRoles()) {
            roleList.add(r);
        }
        user.setRoles(roleList);
        service.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PutMapping("/rest/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer id) {
        try {
            User existUser = service.show(id);
            service.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/rest/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

//    @PutMapping("/rest")
//    public ResponseEntity<User> putUser(@RequestBody User user){
//        Set<Role> l = new HashSet<>();
//        for (Role role : user.getRoles()) {
//            l.add(service.getRole(role.getRole()));
//        }
//        user.setRoles(l);
//        service.update(user);
//        return new ResponseEntity<User>(user, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/rest/{id}")
//    public ResponseEntity deleteUser(@PathVariable int id){
//        service.delete(id);
//        return new ResponseEntity("Removed user with id: " + id, HttpStatus.OK);
//    }
}
