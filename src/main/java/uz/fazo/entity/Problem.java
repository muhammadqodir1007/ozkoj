package uz.fazo.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.fazo.user.User;

@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String fileName;
    @ManyToOne
    private User user;


}
