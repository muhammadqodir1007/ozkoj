package com.alibou.security.payload;

import com.alibou.security.entity.Speakers;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class WebinarDtoResponse {

    private int id;
    private String title_uz;
    private String title_en;
    private String title_ru;
    private String description_uz;
    private String description_en;
    private String description_ru;
    private String file;
    private String city;
    private String field;
    private LocalDateTime time;
    private Boolean online;
    private List<Speakers> speakers;
    private List<UserDto> userDtos;
}
