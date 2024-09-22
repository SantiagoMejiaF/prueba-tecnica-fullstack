package com.litethinking.infrastructure.adapters.out.database.mappers;

import com.litethinking.domain.User;
import com.litethinking.infrastructure.adapters.out.database.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDomain (UserEntity userEntity);

    UserEntity toEntity (User user);
}
