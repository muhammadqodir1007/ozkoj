package uz.fazo.user;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.fazo.payload.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final UserMapper userMapper;


    public List<UserDto> getAll() {
        List<User> all = repository.findAll();
        return all.stream().map(userMapper::userToUserDto).collect(Collectors.toList());
    }

    public UserDto create(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        user.setRole(Role.DISTRICT);
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User save = repository.save(user);
        return userMapper.userToUserDto(save);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }


}