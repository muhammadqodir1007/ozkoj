package com.alibou.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 500)
    private String title_uz;
    @Column(length = 500)
    private String title_ru;
    @Column(length = 500)
    private String title_en;
    @Column(length = 1000)
    private String description_uz;
    @Column(length = 1000)
    private String description_en;
    @Column(length = 1000)
    private String description_ru;
    private String link;
    private LocalDateTime createdDate;
}
