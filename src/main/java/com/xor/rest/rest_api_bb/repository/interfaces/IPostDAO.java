package com.xor.rest.rest_api_bb.repository.interfaces;

import com.xor.rest.rest_api_bb.entity.Post;

import java.util.List;

public interface IPostDAO {

    Post createPost(Post post);

    List<Post> getAllPosts();
    Post getPostById(String id);

    void deletePostById(String id);

    Post updatePost(String id, Post newPost);

}
