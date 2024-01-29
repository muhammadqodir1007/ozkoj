package com.alibou.security.payload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class ResourceDto {

    String description_uz;
    String description_en;
    String description_ru;
    MultipartFile multipartFile;

}
