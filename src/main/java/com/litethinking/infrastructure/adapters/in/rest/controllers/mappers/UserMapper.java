package com.litethinking.infrastructure.adapters.in.rest.controllers.mappers;

import com.litethinking.domain.User;
import com.litethinking.infrastructure.adapters.in.rest.controllers.requests.UserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDomain(UserRequest userRequest);

    UserRequest toRequest(User user);
}
