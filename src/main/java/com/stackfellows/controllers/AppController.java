package com.stackfellows.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.stackfellows.model.AppUser;
import com.stackfellows.model.Post;
import com.stackfellows.repos.AppUserRepo;
import com.stackfellows.repos.PostRepo;
import org.springframework.security.core.parameters.P;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
public class AppController {

    @Autowired
    AppUserRepo appUserRepo;

    @Autowired
    PostRepo postRepo;

    @GetMapping("/blogposts")
    public String getPosts(Principal principal, Model springModel) {
        AppUser user = appUserRepo.findByUsername(principal.getName());
        List<Post> posts = postRepo.findAll(); // TODO: consider implementing SORT in this params list
        springModel.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/index")
    public String getHome(){
        return "index";
    }

}
