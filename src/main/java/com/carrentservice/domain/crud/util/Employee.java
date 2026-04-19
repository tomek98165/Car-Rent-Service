package com.carrentservice.domain.crud.util;

import jakarta.persistence.*;

import java.util.Set;

@Entity
class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastName;
    @Column(unique = true)
    private String username;
    private String password;
    private Set<Authorities> authorities;

    protected Employee() {
    }

     Employee(String name, String lastName, String username, String password) {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;

    }

     Employee(Long id, String name, String lastName, String username, String password, Set<Authorities> authorities) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

     Long getId() {
        return id;
    }

     void setId(Long id) {
        this.id = id;
    }

     String getName() {
        return name;
    }

     void setName(String name) {
        this.name = name;
    }

     String getLastName() {
        return lastName;
    }

     void setLastName(String lastName) {
        this.lastName = lastName;
    }

     String getUsername() {
        return username;
    }

     void setUsername(String username) {
        this.username = username;
    }

     String getPassword() {
        return password;
    }

     void setPassword(String password) {
        this.password = password;
    }

     Set<Authorities> getAuthorities() {
        return authorities;
    }

     void setAuthorities(Set<Authorities> authorities) {
        this.authorities = authorities;
    }

}
