package com.alibou.security.payload;

import com.alibou.security.user.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {


    private String firstname;
    private String lastname;
    private String email;
    private Integer id;
    private boolean enabled;
    private Role role;
    public static UserDto fromUser(com.alibou.security.user.User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .enabled(user.isEnabled())
                .role(user.getRole())
                .build();
    }


}
