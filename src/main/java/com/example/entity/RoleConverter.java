package com.example.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<RoleName, Integer> {
    @Override
    public Integer convertToDatabaseColumn(RoleName roleName) {
        return roleName == null ? null : roleName.getRoleId();
    }

    @Override
    public RoleName convertToEntityAttribute(Integer integer) {
        return integer == null ? null : RoleName.findBy(integer);
    }
}
