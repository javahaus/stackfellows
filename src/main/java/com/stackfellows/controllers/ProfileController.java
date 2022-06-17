package com.stackfellows.controllers;

import com.stackfellows.model.AppUser;
import com.stackfellows.model.Comment;
import com.stackfellows.model.Post;
import com.stackfellows.repos.AppUserRepo;
import com.stackfellows.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.view.RedirectView;

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
            List<Comment> commentList = user.getUserComments();
            m.addAttribute("sessionUsername", sessionUsername);
            m.addAttribute("user", user);
            m.addAttribute("postList", postList);
            m.addAttribute("commentList", commentList);
        }
        return "profile";
    }

    @PutMapping("/updateAccount")
    public RedirectView updateAccount(Principal p, String firstName, String lastName, String email, String bio, Boolean isAlum){
        if(p != null){
            String username = p.getName();
            AppUser appUser = appUserRepo.findByUsername(username);
            appUser.setFirstName(firstName);
            appUser.setLastName(lastName);
            appUser.setEmail(email);
            appUser.setBio(bio);
            if (isAlum == null) {
                appUser.setIsAlum(false);
            } else {
                appUser.setIsAlum(isAlum);
            }
            appUserRepo.save(appUser);
        }
        return new RedirectView("myProfile");
    }
}
