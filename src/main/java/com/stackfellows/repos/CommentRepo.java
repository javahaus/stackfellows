package com.stackfellows.repos;

import com.stackfellows.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    // TODO: Add CRUD functionality as needed
}
