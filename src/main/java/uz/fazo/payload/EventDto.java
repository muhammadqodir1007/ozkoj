package uz.fazo.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EventDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    private String name;
    private String type;
    private int attendeeCount;
    private String comment;
    private String photo;
    private String file;
    private int userId;
    private String status;
}
