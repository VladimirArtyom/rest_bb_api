package com.xor.rest.rest_api_bb.service.interfaces;

import com.xor.rest.rest_api_bb.payload.PostDTO;

import java.util.List;

public interface IPostService {

    // Needs DTO to communicate with external!
    PostDTO createPost(PostDTO postDTO);
    PostDTO getPostById(String id);
    void deletePostById(String id);
    PostDTO updatePost(PostDTO postDTO);
    List<PostDTO> getAllPosts();


}
