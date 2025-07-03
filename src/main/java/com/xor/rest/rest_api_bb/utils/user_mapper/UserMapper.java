package com.xor.rest.rest_api_bb.utils.user_mapper;

import com.xor.rest.rest_api_bb.entity.Role;
import com.xor.rest.rest_api_bb.entity.User;
import com.xor.rest.rest_api_bb.payload.role.RoleDTO;
import com.xor.rest.rest_api_bb.payload.user.UserDTO;
import com.xor.rest.rest_api_bb.utils.generic.GenericMapper;

import javax.annotation.processing.RoundEnvironment;

public interface UserMapper extends GenericMapper<User, UserDTO> {

    public Role toEntityRole(RoleDTO role);
    public RoleDTO toDTORole(Role role);
}
