package com.stackfellows.controllers;


import com.stackfellows.model.AppUser;
import com.stackfellows.repos.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.PublicKey;

@Controller
public class LoginController {

    @Autowired
    AppUserRepo appUserRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }


    @GetMapping("/signup")
    public String getSignInPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView signup(String username, String password, String firstName, String lastName, String email, Boolean isAlum, String bio) {
        String hashPass = passwordEncoder.encode(password);

        if(isAlum == null){
            AppUser newUser = new AppUser(username, hashPass, firstName, lastName, email, false, bio);
            appUserRepo.save(newUser);
        }
        else {
            AppUser newUser = new AppUser(username, hashPass, firstName, lastName, email, isAlum, bio);
            appUserRepo.save(newUser);
        }
        authWithHttpServerRequest(username, password);
        return new RedirectView("/");
    }

    public void authWithHttpServerRequest(String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            System.out.println("Error While Loggin In");

            e.printStackTrace();
        }

    }
}