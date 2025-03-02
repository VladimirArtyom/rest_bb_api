package com.xor.rest.rest_api_bb.utils.post_mapper;

import com.xor.rest.rest_api_bb.entity.Post;
import com.xor.rest.rest_api_bb.payload.PostDTO;
import org.springframework.stereotype.Component;

@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Post toEntity(PostDTO postDTO) {
        Post post = new Post();
        if (postDTO.getId() != null) {
            post.setId(postDTO.getId());
        }
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setDescription(postDTO.getDescription());

        return post;
    }

    @Override
    public PostDTO toDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setDescription(post.getDescription());

        return postDTO;
    }
}
