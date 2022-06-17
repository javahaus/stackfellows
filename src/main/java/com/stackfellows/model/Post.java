package com.stackfellows.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String title;
    @Column(length = 65535)
    private String body;
    private boolean answered;
    private int votes = 0;

    public Post() {
    }

    @ManyToOne
    AppUser appUser;

    //TODO:Connect
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    List<Comment> postComments;


    public Post(String title, String body, AppUser appUser) {
        this.title = title;
        this.body = body;
        this.appUser = appUser;
    }

    public Post(String title, String body, boolean answered, int votes, AppUser appUser) {
        this.title = title;
        this.body = body;
        this.answered = answered;
        this.votes = votes;
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public List<Comment> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<Comment> postComments) {
        this.postComments = postComments;
    }
}
