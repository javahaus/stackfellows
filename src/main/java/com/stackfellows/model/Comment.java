package com.stackfellows.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Comment extends Post {

    public Comment(String body) {
        super(body);
        // TODO: uncomment following line after merge POST class changes
        // this.votes = 0;
    }

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    @ManyToMany(cascade=CascadeType.ALL, mappedBy = "appUser")
    private Set<AppUser> appUser;

}
