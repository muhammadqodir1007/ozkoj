package uz.fazo.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StatisticDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    private String type;
    private String year;
    private String period;
    private String location;
    private String KTUT;
    private String MHOBT;
    private String XXTUT;
    private String link;
    private int userId;

}
