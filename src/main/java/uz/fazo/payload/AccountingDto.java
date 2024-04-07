package uz.fazo.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Getter
@Builder
@Setter
@AllArgsConstructor
public class AccountingDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    private String number;
    private int userId;
    private String type;
    private String year;
    private String period;
    private String address;
    private String file1;
    private String file2;
    private String file3;
    private String file4;
}
