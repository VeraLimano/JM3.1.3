package com.example.spring.service;


import com.example.spring.model.Role;
import com.example.spring.model.User;

import java.util.List;

public interface UserService {

    User getUserByName(String name);

    public User getUserByEmail(String name);

    public Role getRole(String role);

    public List<User> index();

    public User show(int id);

    public void save(User user);

    public void update(User updateUser);

    public void delete(int id);

    public void getByName(String name);
}
