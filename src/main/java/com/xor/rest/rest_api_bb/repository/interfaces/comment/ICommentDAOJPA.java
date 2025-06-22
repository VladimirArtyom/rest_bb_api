package com.xor.rest.rest_api_bb.repository.interfaces.comment;

import com.xor.rest.rest_api_bb.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentDAOJPA extends JpaRepository<Comment, Long> { }
