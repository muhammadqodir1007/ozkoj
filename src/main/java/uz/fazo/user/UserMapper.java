package uz.fazo.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import uz.fazo.payload.UserDto;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "password", target = "password")
    })
    UserDto userToUserDto(User user);

    // If you need to map UserDto to User, you can define the reverse mapping as well
    @Mappings({
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "password", target = "password")
    })
    User userDtoToUser(UserDto userDto);
}
