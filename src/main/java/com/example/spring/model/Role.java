package com.example.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// Этот класс реализует интерфейс GrantedAuthority, в котором необходимо переопределить только один метод getAuthority() (возвращает имя роли).
// Имя роли должно соответствовать шаблону: «ROLE_ИМЯ», например, ROLE_USER.
@Entity
@Table(name = "roles_tab2")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column(name = "role_name")
    private String role;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<User> users;


    public Role() {

    }

    public Role(String role, Set<User> users) {
        this.role = role;
        this.users = users;
    }

    public Role(Integer id, String role, Set<User> users) {
        this.id = id;
        this.role = role;
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public String toString() {
        return role;
    }


//    @Override
//    public String toString() {
//        String ad = "ADMIN";
//        String us = "USER";
//        String str = null;
//        if (role.equals("ROLE_USER"))
//            str = us;
//        else if (role.equals("ROLE_ADMIN"))
//            str = ad;
//        else if(role.equals("ROLE_USER") & role.equals("ROLE_ADMIN"))
//            str = ad + ", " + us;
//        return str;
//    }
}
