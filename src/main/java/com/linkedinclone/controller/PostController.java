package com.linkedinclone.controller;

import com.linkedinclone.entity.Post;
import com.linkedinclone.entity.User;
import com.linkedinclone.repository.UserRepository;
import com.linkedinclone.service.PostService;
import com.linkedinclone.service.LikeService;  // Import LikeService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikeService likeService; // Autowire LikeService

    @PostMapping("/create")
    public Post createPost(@RequestParam Long userId, @RequestParam String content) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return postService.createPost(user, content);
    }

    @GetMapping("/all")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
    
    @PostMapping("/{postId}/like")
    public String likePost(@PathVariable Long postId, @RequestParam Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postService.getPostById(postId);
        if (post == null) {
            throw new RuntimeException("Post not found");
        }

        return likeService.likePost(user, post);
    }
}
