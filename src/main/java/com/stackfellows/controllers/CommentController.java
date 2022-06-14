package com.stackfellows.controllers;

import com.stackfellows.model.AppUser;
import com.stackfellows.model.Comment;
import com.stackfellows.model.Post;
import com.stackfellows.repos.AppUserRepo;
import com.stackfellows.repos.CommentRepo;
import com.stackfellows.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class CommentController {

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    PostRepo postRepo;

    @Autowired
    AppUserRepo appUserRepo;

    @PostMapping("/postComment")
    public RedirectView postAComment(String body, Principal p, Long id){
        if(p != null) {
            String sessionUser = p.getName();
            AppUser user = appUserRepo.findByUsername(sessionUser);
            Post post = postRepo.findById(id).orElseThrow();
            Comment newComment = new Comment(body, post, user);
            commentRepo.save(newComment);
        }

            return new RedirectView("/blogpost/" + id);
    }
}
