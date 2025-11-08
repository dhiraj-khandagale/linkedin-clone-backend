package com.linkedinclone.service;

import com.linkedinclone.entity.Post;
import com.linkedinclone.entity.User;
import com.linkedinclone.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post createPost(User user, String content) {
        Post post = new Post(user, content);
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Corrected: Only one method getPostById 
    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }
}
