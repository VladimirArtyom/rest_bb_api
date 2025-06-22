package com.xor.rest.rest_api_bb.payload.post;

import com.xor.rest.rest_api_bb.entity.Comment;
import com.xor.rest.rest_api_bb.payload.comment.CommentDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private String id;

    @NotEmpty
    @Min(value = 2, message="Post title should have at least 2 characters")
    private String title;

    @NotEmpty(message = "Post content should not be empty ")
    private String content;

    @NotEmpty(message = "Post description should not be empty")
    @Min(value=10, message = "Post description should have at least 10 characters")
    private String description;
    private Set<CommentDTO> comments;
}
