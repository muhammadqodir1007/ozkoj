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
    @Column(length = 1000)
    private String title_uz;
    @Column(length = 1000)
    private String title_en;
    @Column(length = 1000)
    private String title_ru;
    @Column(length = 1000)
    private String description_uz;
    @Column(length = 1000)
    private String description_en;
    @Column(length = 1000)
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
