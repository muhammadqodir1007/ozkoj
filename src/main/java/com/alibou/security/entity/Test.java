package com.alibou.security.entity;

import com.alibou.security.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1000)
    private String question;
    @ElementCollection
    @CollectionTable(name = "test_options", joinColumns = @JoinColumn(name = "test_id"))
    @Column(name = "option")
    private List<String> options;

    private String correct;
    @ManyToOne
    private Material material;
    private LocalDateTime createdDate;
}
