package uz.fazo.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private int id;
    private String username;
    private String password;
    private String region;
}
