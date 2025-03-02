package com.xor.rest.rest_api_bb.utils.generic;

public interface GenericMapper<E,D> {
    E toEntity(D dto);
    D toDTO(E entity);
}