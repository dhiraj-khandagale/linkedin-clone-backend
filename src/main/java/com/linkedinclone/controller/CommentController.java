package com.linkedinclone.controller;

import com.linkedinclone.entity.Comment;
import com.linkedinclone.entity.Post;
import com.linkedinclone.entity.User;
import com.linkedinclone.repository.UserRepository;
import com.linkedinclone.service.CommentService;
import com.linkedinclone.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostService postService;

    @PostMapping("/add")
    public Comment addComment(@RequestParam Long postId, @RequestParam Long userId, @RequestParam String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postService.getPostById(postId);
        if (post == null) {
            throw new RuntimeException("Post not found");
        }

        return commentService.addComment(post, user, content);
    }

    @GetMapping("/post/{postId}")
    public List<Comment> getCommentsByPost(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }
}
