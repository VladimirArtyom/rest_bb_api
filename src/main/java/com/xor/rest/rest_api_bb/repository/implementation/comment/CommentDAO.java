package com.xor.rest.rest_api_bb.repository.implementation.comment;

import com.xor.rest.rest_api_bb.entity.Comment;
import com.xor.rest.rest_api_bb.entity.Post;
import com.xor.rest.rest_api_bb.exception.http_exception.InternalServerErrorException;
import com.xor.rest.rest_api_bb.exception.http_exception.ResourceNotFoundException;
import com.xor.rest.rest_api_bb.repository.interfaces.comment.ICommentDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public class CommentDAO implements ICommentDAO {

    private final EntityManager entityManager;

    @Autowired
    public CommentDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Comment createComment(String post_id, Comment comment) {

        Post post = entityManager.find(Post.class, post_id);
        if (post == null) {
            throw new ResourceNotFoundException("post", "post_id", post_id);
        }
        //ユーサのコメントを作成する
        comment.setPost(post);

        entityManager.persist(comment);
        return comment;
    }

    @Override
    public Stream<Comment> getCommentsByPostId(String post_id) {

        // コメントのdatabaseを取得する// Dans JPQL, Tu muss utiliser the entity not table's name
        String jpql = "SELECT c FROM Comment c INNER JOIN Post p ON c.post.id=p.id WHERE p.id = :post_id";
        Stream<Comment> results =  entityManager.createQuery(jpql).setParameter("post_id", post_id).getResultStream();
        return results;
    }

    @Override
    public Comment getCommentById(String post_id, Long comment_id) {
        String jpql = "SELECT c " +
                      "FROM Comment c "+
                      "WHERE c.post.id = :post_id AND c.id=:comment_id";

        return (Comment) this.entityManager.createQuery(jpql)
                .setParameter("post_id", post_id)
                .setParameter("comment_id", comment_id)
                .getSingleResult();
    }

    @Override
    @Transactional
    public Comment updateComment(String post_id, Comment comment) {
        String jpql = "UPDATE Comment c " +
                      "SET c.name = :new_name, c.email = :new_email, c.body = :new_body " +
                      "WHERE c.post.id = :post_id AND c.id= :comment_id";
        int isSuccess = this.entityManager.createQuery(jpql)
                .setParameter("new_name", comment.getName())
                .setParameter("new_email",comment.getEmail())
                .setParameter("new_body", comment.getBody())
                .setParameter("post_id", post_id)
                .setParameter("comment_id", comment.getId())
                .executeUpdate();
        if (isSuccess < 1) {
            throw new InternalServerErrorException(String.format("Error while trying to update comment: %s with post-id: %s", comment.getId(), post_id));
        }
        return this.getCommentById(post_id, comment.getId());
    }

    @Override
    @Transactional
    public boolean deleteCommentById(String post_id, Long comment_id) {
        String jpql = "DELETE FROM Comment c where c.post.id = :post_id AND c.id = :comment_id";
        int isSuccess = entityManager.createQuery(jpql)
                .setParameter("post_id", post_id)
                .setParameter("comment_id", comment_id)
                .executeUpdate();

        return isSuccess > 0;
    }

    @Override
    @Transactional
    public boolean deleteAllComments(String post_id){
        String jpql = "DELETE FROM Comment c WHERE c.post.id=:post_id";
        int isSuccess = entityManager.createQuery(jpql)
                .setParameter("post_id", post_id)
                .executeUpdate();
        return isSuccess > 0;
};

}
