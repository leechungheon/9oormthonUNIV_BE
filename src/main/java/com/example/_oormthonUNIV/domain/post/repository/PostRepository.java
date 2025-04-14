package com.example._oormthonUNIV.domain.post.repository;

import com.example._oormthonUNIV.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    // Custom query methods can be defined here if needed
    // For example, findByTitle, findByAuthor, etc.
}
