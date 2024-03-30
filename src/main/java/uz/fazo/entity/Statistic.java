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
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;
    private String year;
    private String period;
    private String location;
    private String KTUT;
    private String MHOBT;
    private String XXTUT;
    private String link;
    @ManyToOne
    private User user;

}
