package com.example.post.controller;

import com.example.post.dto.CreatePostDTO;
import com.example.post.model.Post;
import com.example.post.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody CreatePostDTO dto) {

        // To be completed

        return postService.createPost(dto,111);
    }
}
