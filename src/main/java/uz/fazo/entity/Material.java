package uz.fazo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import uz.fazo.user.User;

import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String link;
    @ManyToOne
    private User user;
    private LocalDateTime createdAt;
}
