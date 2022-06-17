package com.stackfellows.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.stackfellows.model.AppUser;
import com.stackfellows.model.Post;
import com.stackfellows.repos.AppUserRepo;
import com.stackfellows.repos.PostRepo;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    AppUserRepo appUserRepo;

    @Autowired
    PostRepo postRepo;

    @GetMapping("/index")
    public String getPosts(Principal principal, Model springModel) {
        AppUser user = appUserRepo.findByUsername(principal.getName());
        List<Post> posts = postRepo.findAll(); // TODO: consider implementing SORT in this params list
        springModel.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/")
    public String getHome(Principal principal, Model springModel) {
        AppUser user = appUserRepo.findByUsername(principal.getName());
        List<Post> posts = postRepo.findAll(); // TODO: consider implementing SORT in this params list
        springModel.addAttribute("posts", posts);
        return "index";
    }
}
