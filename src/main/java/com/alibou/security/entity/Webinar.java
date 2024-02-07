package com.alibou.security.entity;

import com.alibou.security.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Webinar {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title_uz;
    private String title_en;
    private String title_ru;
    private String description_uz;
    private String description_en;
    private String description_ru;
    private String link;
    private String field;
    private String city;
    private Boolean online;
    private LocalDateTime time;
    @ManyToMany
    private Set<Speakers> speakers;
    @ManyToMany
    private Set<User> user;


}
