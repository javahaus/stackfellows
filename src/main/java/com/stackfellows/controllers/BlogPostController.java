package com.stackfellows.controllers;

import com.stackfellows.model.AppUser;
import com.stackfellows.model.Comment;
import com.stackfellows.model.Post;
import com.stackfellows.repos.AppUserRepo;
import com.stackfellows.repos.CommentRepo;
import com.stackfellows.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
public class BlogPostController {

    @Autowired
    AppUserRepo appUserRepo;

    @Autowired
    PostRepo postRepo;


    //TODO: add comment list as attribute.
    // create a comment controller - @postMapping (Comment Controller)
    @GetMapping("/blogpost/{id}")
    public String getPostPage(Principal p, Model m, @PathVariable Long id){

        if(p != null) {
            String username = p.getName();
            m.addAttribute("sessionUsername", username);
        }
        Post post = postRepo.findById(id).orElseThrow();
            m.addAttribute("postInfo", post);

//      Comment comment = commentRepo.findById(id);
        List<Comment> commentList = post.getPostComments();
            m.addAttribute("commentList", commentList);

            return "blogpost";
    }

    @PostMapping("/postquestions")
    public RedirectView postAQuestion(String title, String body, String codesnippet, Principal p){
        StringBuilder bodyFullText = new StringBuilder();
        bodyFullText.append(body);
        String username = p.getName();
        AppUser user = appUserRepo.findByUsername(username);

        if (codesnippet.length() > 0) {
            bodyFullText.append("\r\n");
            bodyFullText.append("\r\n");
            bodyFullText.append("----- code snippet -----");
            bodyFullText.append("\r\n");
            bodyFullText.append("\r\n");
            bodyFullText.append(codesnippet);
            bodyFullText.append("\r\n");
            bodyFullText.append("----- code snippet -----");
        }

        Post newPost = new Post(title, bodyFullText.toString(), user);
        postRepo.save(newPost);

        return new RedirectView("/blogpost/" + newPost.getId());
    }

}
