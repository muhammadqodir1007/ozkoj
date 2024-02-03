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


}
