package uz.fazo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import uz.fazo.payload.UserDto;
import uz.fazo.user.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "region", target = "region")
    })
    UserDto userToUserDto(User user);

    // If you need to map UserDto to User, you can define the reverse mapping as well
    @Mappings({
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "region", target = "region")
    })
    User userDtoToUser(UserDto userDto);
}
