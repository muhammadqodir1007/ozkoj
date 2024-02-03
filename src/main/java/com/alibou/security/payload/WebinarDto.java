package com.alibou.security.payload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class WebinarDto {

    private String title_uz;
    private String title_en;
    private String title_ru;
    private String description_uz;
    private String description_en;
    private String description_ru;
    private MultipartFile file;
    private String city;
    private String field;
    private LocalDateTime time;
    private Boolean online;
    private List<Integer> speakers;
}
