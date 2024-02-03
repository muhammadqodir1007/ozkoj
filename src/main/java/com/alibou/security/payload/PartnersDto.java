package com.alibou.security.payload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PartnersDto {

    private String title_uz;
    private String title_ru;
    private String title_en;
    private String url;
    private MultipartFile file;
}
