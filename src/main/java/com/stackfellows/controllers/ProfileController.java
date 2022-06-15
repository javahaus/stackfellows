package com.stackfellows.controllers;

import com.stackfellows.model.AppUser;
import com.stackfellows.model.Post;
import com.stackfellows.repos.AppUserRepo;
import com.stackfellows.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    AppUserRepo appUserRepo;

    @Autowired
    PostRepo postRepo;

    @GetMapping("/myProfile")
    public String getProfile(Principal p, Model m){
        if(p != null){
            String sessionUsername = p.getName();
            AppUser user = appUserRepo.findByUsername(sessionUsername);
            List<Post> postList = user.getUserPosts();
            m.addAttribute("sessionUsername", sessionUsername);
            m.addAttribute("user", user);
            m.addAttribute("postList", postList);
        }
        return "profile";
    }

//    @PutMapping("/updateAccount")
//    public String updateAccount(Principal p, Model m, String firstName, String lastName, String email, String bio, Boolean isAlum){
//
//        return "/login";
//    }
}
