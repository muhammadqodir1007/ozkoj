package com.alibou.security.payload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NewsDto {

    String title_uz;
    String title_ru;
    String title_en;
    String description_uz;
    String description_en;
    String description_ru;
    MultipartFile file;
}
