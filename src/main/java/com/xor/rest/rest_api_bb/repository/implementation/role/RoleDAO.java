package com.xor.rest.rest_api_bb.repository.implementation.role;

import com.xor.rest.rest_api_bb.entity.Role;
import com.xor.rest.rest_api_bb.repository.interfaces.role.IRoleDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleDAO implements IRoleDAO  {

    private EntityManager entityManager;

    @Autowired
    public RoleDAO(EntityManager en) {
        this.entityManager = en;
    }

    @Override
    public Optional<Role> findByName(String name) {
        String query = "SELECT r FROM Role r WHERE name=:p_name";
        TypedQuery<Role> res = this.entityManager.createQuery(query, Role.class);
        res.setParameter("p_name", name);
        return Optional.ofNullable(res.getSingleResult());
    }
}
