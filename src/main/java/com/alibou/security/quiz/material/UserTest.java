package com.alibou.security.quiz.material;

import com.alibou.security.quiz.material.UserTestAnswer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_test")
public class UserTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "material_id")
    private Long materialId;

    @Column(name = "test_id")
    private Long testId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTest")
    private List<UserTestAnswer> answers;

    // getters and setters
}
