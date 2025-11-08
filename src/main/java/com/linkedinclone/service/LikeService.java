package com.linkedinclone.service;

import com.linkedinclone.entity.Like;
import com.linkedinclone.entity.Post;
import com.linkedinclone.entity.User;
import com.linkedinclone.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    public String likePost(User user, Post post) {
        // Prevent duplicate likes
        if (likeRepository.existsByUserAndPost(user, post)) {
            return "User already liked this post.";
        }
        Like like = new Like(user, post);
        likeRepository.save(like);
        return "Liked";
    }
}
