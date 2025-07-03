package com.xor.rest.rest_api_bb.repository.implementation.user;

import com.xor.rest.rest_api_bb.entity.Role;
import com.xor.rest.rest_api_bb.entity.User;
import com.xor.rest.rest_api_bb.repository.interfaces.user.IUserDAO;
import com.xor.rest.rest_api_bb.utils.generic.DatabaseHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAO implements IUserDAO {

    private EntityManager entityManager;
    public UserDAO(EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String query = "SELECT u FROM users u WHERE u.email=:p_email";
        TypedQuery<User> result =  this.entityManager
                .createQuery(query, User.class);
        result.setParameter("p_email", email);
        User out = result.getSingleResult();
        return Optional.ofNullable(out);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String query = "SELECT u FROM users u WHERE u.username=:p_username";
        TypedQuery<User> result = this.entityManager.createQuery(query, User.class);
        result.setParameter("p_username",username);
        User out = result.getSingleResult();
        return Optional.ofNullable(out);
    }

    @Override
    public Optional<User> findByUsernameOrEmail(String username, String email) {
        if (!username.isBlank())  {
            return this.findByUsername(username);
        }
        return this.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        String query = "SELECT u FROM User u INNER JOIN FETCH u.roles WHERE u.id=:p_id";
        TypedQuery<User> res = this.entityManager.createQuery(query, User.class);

        List<String> listOfKeys = List.of("p_id");
        List<Object> listOfValues = List.of(id);
        res = DatabaseHelper.set_parameters(res, listOfKeys, listOfValues );
        System.out.println(res.getSingleResult());
        return Optional.ofNullable(res.getSingleResult());
    }

    @Override
    public Boolean isExistByUsername(String username) {
        String query = "SELECT COUNT(u) FROM users u WHERE username=:p_username";
        TypedQuery<Long> result = this.entityManager.createQuery(query, Long.class);

        List<Object> listOfValues = List.of("p_username");
        List<String> listOfKeys = List.of(username);

        DatabaseHelper.set_parameters(result, listOfKeys, listOfValues);
        Long out = result.getSingleResult();

        return out > 0;
    }

    @Override
    public Boolean isExistByEmail(String email) {
        String query = "SELECT COUNT(u) FROM users u WHERE email=:p_email";
        TypedQuery<Long> result = this.entityManager.createQuery(query, Long.class);
        result.setParameter("p_email", email);
        Long out = result.getSingleResult();
        return out > 0;
    }

    @Override
    @Transactional
    public Boolean deleteUserById(String id) {
        String query = "DELETE FROM users WHERE id=:p_id";
        TypedQuery<Long> res = this.entityManager.createQuery(query, Long.class);
        Long out = res.setParameter("p_id", id).getSingleResult();
        return out > 0;
    }

    @Override
    @Transactional
    public Optional<User> createUser(User user) {
        this.entityManager.persist(user);
        return findById(user.getId());
    }


    @Override
    public Optional<User> updateUser(String userId, User newUser) {
        return Optional.empty();
    }


    @Override
    @Transactional
    public Optional<User> createUserNativeSQL(User user) {

        String query = "INSERT INTO users (username, name, email, password) VALUES (:p_username, :p_name, :p_email, :p_password)";
        Query res = this.entityManager.createNativeQuery(query);
        List<String> listOfKeys = List.of(
                "p_username",
                "p_name",
                "p_email",
                "p_password"
        );

        List<Object> listOfValues = List.of(
                user.getUsername(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
        res = DatabaseHelper.set_parameters(res, listOfKeys, listOfValues);
        res.executeUpdate();
        // The user's ID is not automatically inserted when using this
        // The code below will do the job
        Long userId;
        try {
            // The query will be executed successfully iff the insert operation was executed successfully.
            Object result = this.entityManager.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult();
            if (result instanceof BigInteger){
                userId = ((BigInteger) result).longValue();
            } else if (result instanceof Number) {
                userId = ((Number) result).longValue();
            } else {
                throw new IllegalStateException("Could not retrieve the generated ID");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve generated user ID:" + e.getMessage(),e);
        }

        user.setId(userId);

        return insertToUsersRolesTable(user);
    }
    @Transactional
    public Optional<User> insertToUsersRolesTable(User user) {
        for (Role userRole : user.getRoles()) {
            String sql = "INSERT INTO users_roles (user_id, role_id) VALUES(:u_id, :ru_id)";
            Query query = this.entityManager.createNativeQuery(sql);
            query.setParameter("u_id", user.getId());
            query.setParameter("ru_id", userRole.getId());
            query.executeUpdate();
        }
       return this.findById(user.getId());
    }
}
