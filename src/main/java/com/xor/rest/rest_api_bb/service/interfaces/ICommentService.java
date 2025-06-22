package com.xor.rest.rest_api_bb.service.interfaces;

import com.xor.rest.rest_api_bb.payload.comment.CommentDTO;

import java.util.List;

public interface ICommentService {

    CommentDTO createComment(String postId, CommentDTO commentDTO);

    List<CommentDTO> getCommentsByPostId(String id);

    void deleteCommentDTO(String postId, Long comment_id);
    void deleteAllComments(String postId);

    CommentDTO updatePost(String postId, CommentDTO commentDTO);
}

