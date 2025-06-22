package com.xor.rest.rest_api_bb.service.implementation;

import com.xor.rest.rest_api_bb.entity.Comment;
import com.xor.rest.rest_api_bb.exception.http_exception.InternalServerErrorException;
import com.xor.rest.rest_api_bb.payload.comment.CommentDTO;
import com.xor.rest.rest_api_bb.payload.post.PostDTO;
import com.xor.rest.rest_api_bb.repository.interfaces.comment.ICommentDAO;
import com.xor.rest.rest_api_bb.service.interfaces.ICommentService;
import com.xor.rest.rest_api_bb.utils.comment_mapper.CommentMapper;
import com.xor.rest.rest_api_bb.utils.comment_mapper.CommentMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class CommentService implements ICommentService {

    private ICommentDAO commentRepository;
    private CommentMapper mapper;
    @Autowired
    public CommentService(ICommentDAO commentRepository,
                          CommentMapper mapper) {
        this.commentRepository =  commentRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDTO createComment(String post_id, CommentDTO commentDTO) {
        Comment en_comment = this.mapper.toEntity(commentDTO);
        en_comment = this.commentRepository.createComment(post_id, en_comment);
        return this.mapper.toDTO(en_comment);
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(String post_id) {
        Stream<Comment> results = this.commentRepository.getCommentsByPostId(post_id);
        List<CommentDTO> commentsDTO = results.map(comment -> this.mapper.toDTO(comment)).toList();
        return commentsDTO;
    }


    @Override
    public CommentDTO updatePost(String postId, CommentDTO commentDTO) {
        Comment en_comment = this.mapper.toEntity(commentDTO);
        Comment sortie = this.commentRepository.updateComment(postId, en_comment);
        return this.mapper.toDTO(sortie);
    }

    //Delete関数の開始
    @Override
    public void deleteAllComments(String post_id){
        boolean isSuccess = this.commentRepository.deleteAllComments(post_id);
        if (!isSuccess) {
            throw new InternalServerErrorException(String.format("Error when trying to delete the comments from post - %s", post_id));
        }
    }

    @Override
    public void deleteCommentDTO(String post_id, Long comment_id) {
        boolean isSuccess = this.commentRepository.deleteCommentById(post_id, comment_id);
        if (!isSuccess) {
            throw new InternalServerErrorException(String.format("Error while trying to delete the comment-%s from post-%s", comment_id, post_id));
        }
    }
    // Delete関数の終了

}
