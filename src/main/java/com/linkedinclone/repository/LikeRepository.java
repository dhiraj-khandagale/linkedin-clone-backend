package com.linkedinclone.repository;

import com.linkedinclone.entity.Like;
import com.linkedinclone.entity.Post;
import com.linkedinclone.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserAndPost(User user, Post post);
}
