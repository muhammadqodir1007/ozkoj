package uz.fazo.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    private String fullName;
    private String address;
    private String birthDate;
    private String state;
    private String passportSeries;
    private String passportNumber;
    private String phoneNumber;
    private int groupNumber;
}
