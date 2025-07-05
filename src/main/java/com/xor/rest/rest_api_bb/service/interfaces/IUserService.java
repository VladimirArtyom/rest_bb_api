package com.xor.rest.rest_api_bb.service.interfaces;

import com.xor.rest.rest_api_bb.entity.User;
import com.xor.rest.rest_api_bb.payload.user.UserDTO;

public interface IUserService {
    UserDTO getUserByEmail(String email);
    UserDTO getUserByUsername(String username);
    UserDTO getUserById(Long id);
    UserDTO createUser(UserDTO user);
    Boolean deleteUser(Long id);
    UserDTO updateUser(long id, UserDTO user);
}
