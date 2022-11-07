package com.example.entity.converter;

import com.example.entity.role.Permission;
import com.example.entity.role.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Map<Role, Permission>, String> {


    @Override
    public String convertToDatabaseColumn(Map<Role, Permission> rolePermissionMap) {
return null;
    }

    @Override
    public Map<Role, Permission> convertToEntityAttribute(String s) {
        return null;
    }


}
