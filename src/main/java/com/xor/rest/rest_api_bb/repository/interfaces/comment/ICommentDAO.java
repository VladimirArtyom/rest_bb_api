package com.xor.rest.rest_api_bb.repository.interfaces.comment;

import com.xor.rest.rest_api_bb.entity.Comment;
import com.xor.rest.rest_api_bb.entity.Post;

import java.util.List;
import java.util.stream.Stream;

public interface ICommentDAO {

    Comment createComment(String post_id, Comment comment);
    Stream<Comment> getCommentsByPostId(String post_id);
    Comment getCommentById(String post_id, Long comment_id);
    Comment updateComment(String id, Comment comment);
    boolean deleteCommentById(String id, Long comment_id);
    boolean deleteAllComments(String id);
}
