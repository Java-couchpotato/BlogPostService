package com.example.entity.converter;

import com.example.entity.types.PostStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PostStatusConverter implements AttributeConverter<PostStatus, String> {

    @Override
    public String convertToDatabaseColumn(PostStatus postStatus) {
        return postStatus == null ? null : postStatus.getName();    }

    @Override
    public PostStatus convertToEntityAttribute(String str) {
        return str == null ? null : PostStatus.findByName(str);
    }
}
