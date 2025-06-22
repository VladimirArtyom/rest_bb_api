package com.xor.rest.rest_api_bb.utils.comment_mapper;


import com.xor.rest.rest_api_bb.entity.Comment;
import com.xor.rest.rest_api_bb.payload.comment.CommentDTO;
import org.springframework.stereotype.Component;

@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment toEntity(CommentDTO dto) {
        Comment en_comment = new Comment();
        en_comment.setId(dto.getId());

        if (en_comment.getId() != null) {
            en_comment.setId(dto.getId());
        }
        en_comment.setEmail(dto.getEmail());
        en_comment.setName(dto.getName());
        en_comment.setBody(dto.getBody());
        return en_comment;
    }

    @Override
    public CommentDTO toDTO(Comment entity) {
        CommentDTO dto = new CommentDTO();
        dto.setId(entity.getId());
        dto.setBody(entity.getBody());
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        return dto;
    }
}
