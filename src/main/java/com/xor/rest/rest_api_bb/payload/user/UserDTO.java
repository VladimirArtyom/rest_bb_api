package com.xor.rest.rest_api_bb.payload.user;


import com.xor.rest.rest_api_bb.entity.Role;
import com.xor.rest.rest_api_bb.payload.role.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String password;
    private Set<RoleDTO> roles;
}

