package com.alibou.security.user;

import com.alibou.security.payload.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        repository.save(user);
    }


    public List<UserDto> findAll() {

        List<User> all = repository.findAll();
        List<UserDto> list = new ArrayList<>();
        for (User user : all) {
            UserDto userDto = UserDto.builder().enabled(user.isEnabled())
                    .role(user.getRole()).email(user.getEmail())
                    .lastname(user.getLastname())
                    .id(user.getId())
                    .firstname(user.getFirstname())
                    .build();
            list.add(userDto);
        }
        return list;

    }

    public void delete(int id) {
        repository.deleteById(id);
    }


}
