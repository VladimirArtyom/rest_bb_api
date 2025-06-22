package com.xor.rest.rest_api_bb.repository.interfaces.post;

import com.xor.rest.rest_api_bb.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostDAOJPA extends JpaRepository<Post, String> {

}
