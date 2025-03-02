package com.xor.rest.rest_api_bb.service.implementation;

import com.xor.rest.rest_api_bb.entity.Post;
import com.xor.rest.rest_api_bb.payload.PostDTO;
import com.xor.rest.rest_api_bb.repository.implementation.PostDAO;
import com.xor.rest.rest_api_bb.service.interfaces.IPostService;
import com.xor.rest.rest_api_bb.utils.post_mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService {

    private PostDAO postRepository;
    private PostMapper postMapper;

    @Autowired
    public PostService(PostDAO postRepository,
                       PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper =  postMapper;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = this.postMapper.toEntity(postDTO);
        post = this.postRepository.createPost(post);
        return this.postMapper.toDTO(post);
    }

    @Override
    public PostDTO getPostById(String id) {
        Post post = this.postRepository.getPostById(id);
        return this.postMapper.toDTO(post);
    }

    @Override
    public void deletePostById(String id) {
        this.postRepository.deletePostById(id);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO) {
        Post post = this.postMapper.toEntity(postDTO);
        post = this.postRepository.updatePost(post.getId(), post);
        return this.postMapper.toDTO(post);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> posts = this.postRepository.getAllPosts();
        return posts.stream().map(ent_post -> this.postMapper.toDTO(ent_post)).toList();
    }


}


