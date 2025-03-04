package com.xor.rest.rest_api_bb.controller;

import com.xor.rest.rest_api_bb.payload.PostDTO;
import com.xor.rest.rest_api_bb.payload.response.ApiResponse;
import com.xor.rest.rest_api_bb.service.implementation.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/posts")
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<PostDTO>>> getPosts() {
        logger.error("ngntd");
        List<PostDTO> posts = this.postService.getAllPosts();
        ApiResponse<List<PostDTO>> api = new ApiResponse<>("Success", HttpStatus.OK.value(), "posts", posts);
        return new ResponseEntity<>(api, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDTO>> getPostById(
            @PathVariable String id
    ){
        logger.error(id);
        PostDTO post = this.postService.getPostById(id);
        ApiResponse<PostDTO> api = new ApiResponse<>("Success", HttpStatus.OK.value(), "post", post);
        return new ResponseEntity<>(api, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PostDTO>> createPost(@RequestBody PostDTO postDTO) {
        postDTO = this.postService.createPost(postDTO);

        ApiResponse<PostDTO> api =  new ApiResponse<PostDTO>("Success", HttpStatus.CREATED.value(),"post", postDTO);
        return new ResponseEntity<>(api, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<PostDTO>> updatePost(@RequestBody PostDTO postDTO) {
        postDTO = this.postService.updatePost(postDTO);
        ApiResponse<PostDTO> api = new ApiResponse<PostDTO>("Success", HttpStatus.OK.value(), "post", postDTO);
        return new ResponseEntity<>(api, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<String>> deletePost(@RequestBody PostDTO postDTO) {
        this.postService.deletePostById(postDTO.getId());
        ApiResponse<String> api = new ApiResponse<String>("Success", HttpStatus.OK.value(), "post", "Post is deleted");
        return new ResponseEntity<>(api, HttpStatus.OK);
    }

}
