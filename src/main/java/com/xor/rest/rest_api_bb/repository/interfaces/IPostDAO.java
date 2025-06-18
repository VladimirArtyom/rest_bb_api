package com.xor.rest.rest_api_bb.repository.interfaces;

import com.xor.rest.rest_api_bb.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostDAO {

    Post createPost(Post post);

    Post getPostById(String id);

    void deletePostById(String id);

    Post updatePost(String id, Post newPost);
}
