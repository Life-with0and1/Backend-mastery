package com.example.post.service;


import com.example.post.dto.CreatePostDTO;
import com.example.post.model.Post;
import com.example.post.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public Post createPost(CreatePostDTO postDTO,long userId){
        Post post = new Post();

        post.setDescription(postDTO.getDescription());
        post.setCategory(postDTO.getCategory());
        post.setLatitude(postDTO.getLatitude());
        post.setLongitude(postDTO.getLongitude());

        post.setUserId(userId);

        Post savedPost = postRepository.save(post);
        return savedPost;
    }


}
