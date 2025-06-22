package com.xor.rest.rest_api_bb.utils.post_mapper;

import com.xor.rest.rest_api_bb.entity.Comment;
import com.xor.rest.rest_api_bb.entity.Post;
import com.xor.rest.rest_api_bb.payload.comment.CommentDTO;
import com.xor.rest.rest_api_bb.payload.post.PostDTO;
import com.xor.rest.rest_api_bb.utils.comment_mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PostMapperImpl implements PostMapper {

    private CommentMapper cm;
    @Autowired
    public PostMapperImpl(CommentMapper cm) {
        this.cm = cm;
    }
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
        if (post.getComments() != null) {
            postDTO.setComments(post.getComments().stream().map(cm::toDTO).collect(Collectors.toSet()));
        }

        return postDTO;
    }
}