package com.xor.rest.rest_api_bb.service.implementation;

import com.xor.rest.rest_api_bb.entity.Role;
import com.xor.rest.rest_api_bb.entity.User;
import com.xor.rest.rest_api_bb.exception.http_exception.InternalServerErrorException;
import com.xor.rest.rest_api_bb.payload.user.UserDTO;
import com.xor.rest.rest_api_bb.repository.implementation.user.UserDAO;
import com.xor.rest.rest_api_bb.repository.interfaces.role.IRoleDAO;
import com.xor.rest.rest_api_bb.repository.interfaces.user.IUserDAO;
import com.xor.rest.rest_api_bb.service.interfaces.IUserService;
import com.xor.rest.rest_api_bb.utils.user_mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements IUserService {

    private IUserDAO userRepository;
    private IRoleDAO roleRepository;
    private UserMapper userMapper;

    public UserService(IUserDAO userRepository,
                       IRoleDAO roleRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }
    @Override
    public UserDTO getUserByEmail(String email) {
        Optional<User> opt = this.userRepository.findByEmail(email);
        if (opt.isPresent()){
            return this.userMapper.toDTO(opt.get());
        }
        throw new InternalServerErrorException("Cannot get the user by email");
    }

    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> opt = this.userRepository.findById(id);
        if (opt.isPresent()) {
            return this.userMapper.toDTO(opt.get());
        }
        throw new InternalServerErrorException("Cannot get the user by id");
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return null;
    }

    @Override
    public UserDTO createUser(UserDTO userDto) {

        Set<Role> roles = new HashSet<>();
        User user = this.userMapper.toEntity(userDto);
        Optional<Role> role = this.roleRepository.findByName("ROLE_USER");
        if (role.isEmpty()) {
            throw new InternalServerErrorException("Internal Server Error, Role is empty!");
        }
        roles.add(role.get());
        user.setRoles(roles);
        Optional<User> opt = this.userRepository.createUserNativeSQL(user);
        if(opt.isPresent()) {
            return this.userMapper.toDTO(opt.get());
        }
        // Needs to be changed
        throw new InternalServerErrorException("Cannot create user");
    }



    @Override
    public Boolean deleteUser(String id) {
        return null;
    }
}
