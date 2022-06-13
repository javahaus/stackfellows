package com.stackfellows.model;

import javax.persistence.*;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String username;
    public String password;

// For when we connect user to posts
// @OneToMany(mappedBy = "appUser")
// List<Post> userPosts;


    public AppUser() {
    }

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AppUser(String username, String hashPass, String firstName, String lastName, String email, Boolean isAlum, String bio) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}