package com.xor.rest.rest_api_bb.utils.comment_mapper;

import com.xor.rest.rest_api_bb.entity.Comment;
import com.xor.rest.rest_api_bb.payload.comment.CommentDTO;
import com.xor.rest.rest_api_bb.utils.generic.GenericMapper;

public interface CommentMapper extends GenericMapper<Comment, CommentDTO> {
    @Override
    Comment toEntity(CommentDTO dto);

    @Override
    CommentDTO toDTO(Comment entity);
}
