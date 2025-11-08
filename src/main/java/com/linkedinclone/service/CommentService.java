package com.linkedinclone.service;

import com.linkedinclone.entity.Comment;
import com.linkedinclone.entity.Post;
import com.linkedinclone.entity.User;
import com.linkedinclone.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment addComment(Post post, User user, String content) {
        Comment comment = new Comment(post, user, content);
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostIdOrderByCreatedAtDesc(postId);
    }
}
