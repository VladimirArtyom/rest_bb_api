package com.xor.rest.rest_api_bb.controller;

import com.xor.rest.rest_api_bb.payload.comment.CommentDTO;
import com.xor.rest.rest_api_bb.payload.response.ApiResponse;
import com.xor.rest.rest_api_bb.service.interfaces.ICommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/")
public class CommentController {
    private final ICommentService commentService;

    @Autowired
    CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<ApiResponse<CommentDTO>> createComment(
            @PathVariable(name = "postId") String postId,
            @Valid @RequestBody(required = true) CommentDTO commentDTO
    ) {
        CommentDTO sortie = this.commentService.createComment(postId, commentDTO);
        ApiResponse<CommentDTO> apiResponse = new ApiResponse<>("success", HttpStatus.CREATED.value(), "outputs", sortie);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<ApiResponse<List<CommentDTO>>> getComments(
            @PathVariable(name="postId") String postId) {
        List<CommentDTO> commentDTOS = this.commentService.getCommentsByPostId(postId);
        ApiResponse<List<CommentDTO>> apiResponse = new ApiResponse<>("success", HttpStatus.OK.value(), "outputs", commentDTOS);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }



    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<ApiResponse<CommentDTO>> updateComment(
            @PathVariable(name="postId") String postId,
            @PathVariable(name="commentId") Long commentId,
            @Valid @RequestBody(required = true) CommentDTO commentDTO
            ) {
        commentDTO.setId(commentId);
        CommentDTO sortie = this.commentService.updatePost(postId, commentDTO);
        ApiResponse<CommentDTO> apiResponse = new ApiResponse<>("success", HttpStatus.OK.value(), "Comments",sortie);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    // Delete関数の開始
    @DeleteMapping("/posts/{postId}/comments")
    public ResponseEntity<ApiResponse<String>> deleteAllComments(
        @PathVariable(name = "postId") String postId
    ) {
        this.commentService.deleteAllComments(postId);
        ApiResponse<String> apiResponse = new ApiResponse<>("success", HttpStatus.OK.value(), "Comments", "The comments of this post has been deleted");
        return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<ApiResponse<String>> deleteComment(
            @PathVariable(name="postId") String postId,
            @PathVariable(name="commentId") Long commentId
    ) {
        this.commentService.deleteCommentDTO(postId, commentId);
        ApiResponse<String> apiResponse = new ApiResponse<>("success", HttpStatus.OK.value(), "Comment", "The comment has been deleted");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    // Delete関数の終了

}
