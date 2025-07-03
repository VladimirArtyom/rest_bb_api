package com.xor.rest.rest_api_bb.repository.interfaces.user;

import com.xor.rest.rest_api_bb.entity.User;

import java.util.Optional;

public interface IUserDAO {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findById(Long id);

    Boolean isExistByUsername(String username);

    Boolean isExistByEmail(String email);

    Boolean deleteUserById(String id);

    Optional<User> createUser(User user);

    Optional<User> updateUser(String userId, User newUser);

    // NATIVE SQL For Educational usage only
    Optional<User> createUserNativeSQL(User user);
}