package com.xor.rest.rest_api_bb.service.implementation;

import com.xor.rest.rest_api_bb.entity.Post;
import com.xor.rest.rest_api_bb.payload.interfaces.IPaginationResponse;
import com.xor.rest.rest_api_bb.payload.post.PostDTO;
import com.xor.rest.rest_api_bb.repository.interfaces.IPostDAO;
import com.xor.rest.rest_api_bb.repository.interfaces.IPostDAOJPA;
import com.xor.rest.rest_api_bb.service.interfaces.IPostService;
import com.xor.rest.rest_api_bb.utils.constant.post.PostConstants;
import com.xor.rest.rest_api_bb.utils.post_mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService {

    private IPostDAO postRepository;
    private IPostDAOJPA postRepositoryJPA;
    private PostMapper postMapper;

    @Autowired
    public PostService(IPostDAO postRepository,
                       IPostDAOJPA postRepositoryJPA,
                       PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper =  postMapper;
        this.postRepositoryJPA = postRepositoryJPA;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = this.postMapper.toEntity(postDTO);
        post = this.postRepository.createPost(post);
        return this.postMapper.toDTO(post);
    }

    @Override
    public PostDTO getPostById(String id) {
        Post post = this.postRepository.getPostById(id);
        return this.postMapper.toDTO(post);
    }

    @Override
    public void deletePostById(String id) {
        this.postRepository.deletePostById(id);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO) {
        Post post = this.postMapper.toEntity(postDTO);
        post = this.postRepository.updatePost(post.getId(), post);
        return this.postMapper.toDTO(post);
    }

    @Override
    public IPaginationResponse<PostDTO> getAllPosts(int pageNo, int pageSize,
                                                    PostConstants sortBy, Sort.Direction sortDir) {
        Sort sort = sortDir.name().equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy.name().toLowerCase(), PostConstants.ID.name().toLowerCase()).ascending() :
                Sort.by(sortBy.name().toLowerCase(), PostConstants.ID.name().toLowerCase()).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepositoryJPA.findAll(pageable);
        List<PostDTO> contents = posts.stream().map(entity_post -> this.postMapper.toDTO(entity_post)).toList();
        return setPaginationItem(posts, contents);
    }


    private IPaginationResponse<PostDTO> setPaginationItem(Page<Post> posts, List<PostDTO> contents ) {
        IPaginationResponse<PostDTO> sortie = new IPaginationResponse<PostDTO>();
        sortie.setContent(contents);
        sortie.setPageNo(posts.getNumber());
        sortie.setPageSize(posts.getSize());
        sortie.setTotalElements(posts.getTotalElements());
        sortie.setTotalPages(posts.getTotalPages());
        sortie.setLast(posts.isLast());
        return sortie;
    }
}


