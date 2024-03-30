package uz.fazo.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private String username;
    private String password;
}
