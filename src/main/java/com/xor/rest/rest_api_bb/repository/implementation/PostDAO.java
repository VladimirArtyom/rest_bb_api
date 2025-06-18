package com.xor.rest.rest_api_bb.repository.implementation;

import com.xor.rest.rest_api_bb.entity.Post;
import com.xor.rest.rest_api_bb.repository.interfaces.IPostDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PostDAO implements IPostDAO {

    private final EntityManager entityManager;

    @Autowired
    public PostDAO(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    // Transactional methods start
    @Override
    @Transactional
    public Post createPost(Post post) {
        this.entityManager.persist(post);
        return post;
    }

    @Override
    @Transactional
    public Post updatePost(String id, Post newPost) {
        Post oldPost = this.entityManager.find(Post.class, id);
        if (!Optional.ofNullable(newPost.getDescription()).orElse("").isEmpty()) {
            oldPost.setDescription(newPost.getDescription());
        }

        if (!Optional.ofNullable(newPost.getTitle()).orElse("").isEmpty()) {
            oldPost.setTitle(newPost.getTitle());
        }

        if (!Optional.ofNullable(newPost.getContent()).orElse("").isEmpty()) {
            oldPost.setContent(newPost.getContent());
        }

       return this.entityManager.merge(oldPost);
    }

    @Override
    @Transactional
    public void deletePostById(String id) {
        String query = "DELETE FROM Post p WHERE p.id = :id";
        Query delete_query = this.entityManager.createQuery(query);
        delete_query.setParameter("id", id);
        delete_query.executeUpdate();
    }

    // Transactional methods end

    @Override
    public Post getPostById(String id) {

        String query = "SELECT p from Post p WHERE p.id = :id";
        TypedQuery<Post> typedQuery = this.entityManager.createQuery(query, Post.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
    }


}
