package com.alibou.security.entity;

import jakarta.persistence.*;
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
    private String link; //image link
    @Column(length = 1000)
    private String title_uz;
    @Column(length = 1000)
    private String title_ru;
    @Column(length = 1000)
    private String title_en;
    private String url;  //website url
}
