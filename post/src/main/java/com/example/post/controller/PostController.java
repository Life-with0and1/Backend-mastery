package com.example.post.controller;

import com.example.post.dto.CreatePostDTO;
import com.example.post.model.Post;
import com.example.post.security.CustomUserPrincipal;
import com.example.post.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public  PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@AuthenticationPrincipal CustomUserPrincipal user, @Valid @RequestBody CreatePostDTO dto) {
        return ResponseEntity.ok(postService.createPost(dto, user.getUserId()));
    }
}
