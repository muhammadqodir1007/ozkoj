package com.alibou.security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Partners {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String link;  //image link
    private String title_uz;
    private String title_ru;
    private String title_en;
    private String url;  //website url
}
