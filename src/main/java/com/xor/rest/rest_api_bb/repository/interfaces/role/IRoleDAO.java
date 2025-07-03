package com.xor.rest.rest_api_bb.repository.interfaces.role;

import com.xor.rest.rest_api_bb.entity.Role;

import java.util.Optional;

public interface IRoleDAO {
    Optional<Role> findByName(String name);
}
