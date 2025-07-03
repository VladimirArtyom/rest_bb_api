package com.xor.rest.rest_api_bb.utils.user_mapper;


import com.xor.rest.rest_api_bb.entity.Role;
import com.xor.rest.rest_api_bb.entity.User;
import com.xor.rest.rest_api_bb.payload.role.RoleDTO;
import com.xor.rest.rest_api_bb.payload.user.UserDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User toEntity(UserDTO dto) {
        User newUser = new User();
        newUser.setEmail(dto.getEmail());
        newUser.setName(dto.getName());
        newUser.setUsername(dto.getUsername());
        newUser.setPassword(dto.getPassword());
        return newUser;
    }

    @Override
    public UserDTO toDTO(User en) {
        UserDTO dto = new UserDTO();
        dto.setId(en.getId());
        dto.setName(en.getName());
        dto.setPassword(en.getPassword());
        dto.setEmail(en.getEmail());
        dto.setUsername(en.getUsername());
        dto.setRoles(en.getRoles().stream().map(each_role -> this.toDTORole(each_role) ).collect(Collectors.toSet()));
        return dto;
    }

    @Override
    public Role toEntityRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        role.setId(roleDTO.getId());
        return role;
    }

    @Override
    public RoleDTO toDTORole(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }
}
