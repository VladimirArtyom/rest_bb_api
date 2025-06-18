package com.xor.rest.rest_api_bb.utils.constant.post;
import org.springframework.stereotype.Component;

@Component
public class PostEnumMapper {

    public PostConstants mapToEnum(String input){
        if (input.equalsIgnoreCase("id")) {return PostConstants.ID;}
        else if(input.equalsIgnoreCase("content")) {return PostConstants.CONTENT;}
        else if(input.equalsIgnoreCase("title")) {return PostConstants.TITLE;}
        return PostConstants.DESCRIPTION;
    }

    public String mapToString(PostConstants input) {
        return switch (input) {
            case PostConstants.ID -> "id";
            case PostConstants.CONTENT -> "content";
            case PostConstants.TITLE -> "title";
            case PostConstants.DESCRIPTION -> "description";
        };
    }

}
