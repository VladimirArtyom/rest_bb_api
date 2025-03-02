package com.xor.rest.rest_api_bb.utils.post_mapper;

import com.xor.rest.rest_api_bb.entity.Post;
import com.xor.rest.rest_api_bb.payload.PostDTO;
import com.xor.rest.rest_api_bb.utils.generic.GenericMapper;

public interface PostMapper extends GenericMapper<Post, PostDTO> {
    Post toEntity(PostDTO post);
    PostDTO toDTO(Post post);
}
