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
public class Resource {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    String description_uz;
    String description_en;
    String description_ru;
    String fileLink;

}
