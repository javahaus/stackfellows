package com.stackfellows.controllers;

import com.stackfellows.model.AppUser;
import com.stackfellows.model.Post;
import com.stackfellows.repos.AppUserRepo;
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
            return "blogpost";
    }

    @PostMapping("/postquestions")
    public RedirectView postAQuestion(String title, String body, String codesnippet, Principal p){
        StringBuilder bodyFullText = new StringBuilder();
        bodyFullText.append(body);
        String username = p.getName();
        AppUser user = appUserRepo.findByUsername(username);

        try {
            // https://www.baeldung.com/convert-string-to-input-stream
            if (codesnippet.length() > 1) {
                InputStream codesnipStream = new ByteArrayInputStream(codesnippet.getBytes());
                BufferedReader reader = new BufferedReader(new InputStreamReader(codesnipStream));
                bodyFullText.append("/n");
                bodyFullText.append("##### code #####");
                bodyFullText.append("/n");

                while (reader.ready()) {
                    String codeline = codesnipStream.read();
                    bodyFullText.append(codeline);
                }

                bodyFullText.append("/n");
                bodyFullText.append("##### code #####");
            }
        } catch (IOException ioex) {
            // add a logging function to catch this error
            System.out.println("Exception thrown reading code snippet: " + ioex.getMessage());
        }

        Post newPost = new Post(title, bodyFullText.toString(), user);
        postRepo.save(newPost);

        return new RedirectView("/blogpost/" + newPost.getId());
    }

}
