package com.stackfellows.stackfellows.repos;

import com.stackfellows.stackfellows.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {
}
