package com.example.post.controller;

import com.example.post.dto.CreatePostDTO;
import com.example.post.model.Post;
import com.example.post.security.CustomUserPrincipal;
import com.example.post.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Post> createPost(@AuthenticationPrincipal CustomUserPrincipal user, @Valid @RequestPart("post") CreatePostDTO dto, @RequestPart("media") List<MultipartFile> media) throws IOException {
        return ResponseEntity.ok(postService.createPost(dto, user.getUserId(), media));
    }
}
