package com.xor.rest.rest_api_bb.payload;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private String id;
    private String title;
    private String content;
    private String description;

}
