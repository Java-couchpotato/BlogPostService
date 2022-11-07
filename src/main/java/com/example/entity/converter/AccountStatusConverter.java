package com.example.entity.converter;

import com.example.entity.types.AccountStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AccountStatusConverter implements AttributeConverter<AccountStatus, String> {

    @Override
    public String convertToDatabaseColumn(AccountStatus accountStatus) {
        return accountStatus == null ? null : accountStatus.getName();
    }

    @Override
    public AccountStatus convertToEntityAttribute(String str) {
        return str == null ? null : AccountStatus.getByName(str);
    }
}
