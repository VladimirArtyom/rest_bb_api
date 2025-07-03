package com.xor.rest.rest_api_bb.payload.role;


import com.xor.rest.rest_api_bb.payload.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    String name;
    Long id;
}
