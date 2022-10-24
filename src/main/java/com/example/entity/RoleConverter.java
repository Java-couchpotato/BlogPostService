package com.example.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Role role) {
        return role == null ? null : role.getRoleId();
    }

    @Override
    public Role convertToEntityAttribute(Integer integer) {
        return integer == null ? null : Role.findBy(integer);
    }
}
