package com.xor.rest.rest_api_bb.service.interfaces;

import com.xor.rest.rest_api_bb.payload.interfaces.IPaginationResponse;
import com.xor.rest.rest_api_bb.payload.post.PostDTO;
import com.xor.rest.rest_api_bb.utils.constant.post.PostConstants;
import org.springframework.data.domain.Sort;

public interface IPostService {

    // Needs DTO to communicate with external!
    PostDTO createPost(PostDTO postDTO);
    PostDTO getPostById(String id);
    void deletePostById(String id);
    PostDTO updatePost(PostDTO postDTO);
    IPaginationResponse<PostDTO> getAllPosts(int pageNo, int pageSize,
                                             PostConstants sortBy, Sort.Direction sortDir);

}
