package com.xor.rest.rest_api_bb.payload.comment;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;

    @NotEmpty(message = "Body should not be empty")
    @Min(value = 10, message = "Your message is not long enough, it should be at least 10 characters")
    private String body;
}
