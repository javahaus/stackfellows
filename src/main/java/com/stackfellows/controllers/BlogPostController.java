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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.view.RedirectView;

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

        List<Comment> commentList = post.getPostComments();
            m.addAttribute("commentList", commentList);

            return "blogpost";
    }

    @PostMapping("/postquestions")
    public RedirectView postAQuestion(String title, String body, Principal p){
        String username = p.getName();
        AppUser user = appUserRepo.findByUsername(username);
        Post newPost = new Post(title, body, user);
        postRepo.save(newPost);

        return new RedirectView("/blogpost/" + newPost.getId());
    }

    @PutMapping("/editpost")
    public RedirectView editUserPost(Principal p, String title, String body, Long postid){

        Post editedPost = postRepo.findById(postid).orElseThrow();
        editedPost.setTitle(title);
        editedPost.setBody(body);
        postRepo.save(editedPost);

        return new RedirectView("/blogpost/" + id);

    }


    @PutMapping("/upvotePost")
    public RedirectView upvotePost(Long id){
        Post post = postRepo.findById(id).orElseThrow();
        post.setVotes(post.getVotes() + 1);
        postRepo.save(post);
        return new RedirectView("/blogpost/" + id);
    }

}
