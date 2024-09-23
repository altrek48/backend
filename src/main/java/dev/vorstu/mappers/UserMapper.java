package dev.vorstu.mappers;

import dev.vorstu.dto.UserShow;
import dev.vorstu.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "role", constant = "STUDENT")
    @Mapping(target = "enable", constant = "true")
    UserEntity toUserEntity(UserShow userShow);

    UserShow toUserShow(UserEntity userEntity);


}
