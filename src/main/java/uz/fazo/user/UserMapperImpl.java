package uz.fazo.user;

import org.springframework.stereotype.Component;
import uz.fazo.payload.UserDto;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto userToUserDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setId(user.getId());
        userDto.setPassword(user.getPassword());
        userDto.setRegion(user.getRegion());
        return userDto;
    }

    @Override
    public User userDtoToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setId(userDto.getId());
        user.setRole(Role.DISTRICT);
        user.setPassword(userDto.getPassword());
        user.setRegion(userDto.getRegion());
        return user;
    }
}
