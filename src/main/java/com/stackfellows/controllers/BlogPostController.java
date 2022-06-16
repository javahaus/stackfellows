package com.stackfellows.controllers;

import com.stackfellows.model.AppUser;
import com.stackfellows.model.Comment;
import com.stackfellows.model.Post;
import com.stackfellows.repos.AppUserRepo;
import com.stackfellows.repos.CommentRepo;
import com.stackfellows.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.*;
import java.lang.module.ResolutionException;
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


    @GetMapping("/blogpost/{id}")
    public String getPostPage(Principal p, Model m, @PathVariable Long id){

        if(p != null) {
            String username = p.getName();
            m.addAttribute("sessionUsername", username);
        }
        Post post = postRepo.findById(id).orElseThrow();
            m.addAttribute("postInfo", post);

        List<Comment> commentList = post.getPostComments();
            m.addAttribute("commentList", commentList);

            return "blogpost";
    }

    @GetMapping("/editpost/{id}")
    public String getEditPage(Principal p, Model m, @PathVariable Long id){

        if(p != null) {
            String username = p.getName();
            m.addAttribute("sessionUsername", username);
        }
        Post post = postRepo.findById(id).orElseThrow();
        m.addAttribute("postInfo", post);

        List<Comment> commentList = post.getPostComments();
        m.addAttribute("commentList", commentList);

        return "blogpost";
    }

    @PostMapping("/postquestions")
    public RedirectView postAQuestion(RedirectAttributes error, String title, String body, String codesnippet, Principal p){
        String errorMessage = "Code snippet must be 255 characters or less.";
        if(codesnippet.length() > 255){
            error.addFlashAttribute("lengthError", errorMessage);

            return new RedirectView("/index");
        }


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

    // throw a 404 error.
//        throw new ResourceNotFoundException("This is a 404");


        return new RedirectView("/blogpost/" + newPost.getId());
    }


    // Custom 404 exception
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public static class ResourceNotFoundException extends RuntimeException{
        ResourceNotFoundException(String message) {super(message); }
    }

    @PutMapping("/editpost")
    public RedirectView editUserPost(Principal p, String title, String body, String codesnippet, Long postid){

        Post editedPost = postRepo.findById(postid).orElseThrow();
        editedPost.setTitle(title);
        StringBuilder bodyFullText = new StringBuilder();
        bodyFullText.append(body);

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

        editedPost.setBody(bodyFullText.toString());
        postRepo.save(editedPost);

        return new RedirectView("/blogpost/" + postid);

    }


    @PutMapping("/upvotePost")
    public RedirectView upvotePost(Long id){
        Post post = postRepo.findById(id).orElseThrow();
        post.setVotes(post.getVotes() + 1);
        postRepo.save(post);
        return new RedirectView("/blogpost/" + id);
    }

    @DeleteMapping("/deletePost")
    public RedirectView deletePost(Long id){
        postRepo.deleteById(id);
        return new RedirectView("/myProfile");
    }

}
