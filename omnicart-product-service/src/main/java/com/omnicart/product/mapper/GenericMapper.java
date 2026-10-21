package com.omnicart.product.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Created by Ranjit Soni on 21-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Component
public class GenericMapper {

    public <T,R>  R toEntity(T dto, Class<R> entityClass) {
        R entity = BeanUtils.instantiateClass(entityClass);
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public <T,R>  R toEntity(T dto, R existingEntityClass, String... ignoreProperties) {
        BeanUtils.copyProperties(dto, existingEntityClass, ignoreProperties);
        return existingEntityClass;
    }

    public <D, E> D toDto(E entity, Class<D> dtoClass) {
        D dto = BeanUtils.instantiateClass(dtoClass);
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public <D, E> D toDto(E entity, D dto, String... ignoreProperties) {
        BeanUtils.copyProperties(entity, dto, ignoreProperties);
        return dto;
    }
}
