package com.example.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Set<String>, String> {
    @Override
    public String convertToDatabaseColumn(Set<String> strings) {
        return strings == null ? null : String.join(",", strings);
    }

    @Override
    public Set<String> convertToEntityAttribute(String s) {
        return s == null ? null : new HashSet<>(List.of(s.split(",")));
    }
}
