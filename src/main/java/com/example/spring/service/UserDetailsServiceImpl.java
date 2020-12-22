package com.example.spring.service;

import com.example.spring.dao.UserDao;
import com.example.spring.model.Role;
import com.example.spring.model.User;
import com.example.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

    private final UserDao userDao;
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.getUserByEmail(email);
        return user;
    }

    @Override
    @Transactional
    public User getUserByName(String name) { return userDao.getUserByName(name); }

    @Override
    public User getUserByEmail(String name) {
        return userDao.getUserByEmail(name);
    }

    @Override
    @Transactional
    public Role getRole(String role) {
        return userDao.getRole(role);
    }

    @Override
    public List<User> index() {
        return userRepository.findAll();
    }

    @Override
    public User show(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User updateUser) { userRepository.saveAndFlush(updateUser);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
