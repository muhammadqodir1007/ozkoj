package uz.fazo.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.fazo.user.User;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullName;
    private String address;
    private String birthDate;
    private String state;
    private String passportSeries;
    private String passportNumber;
    private String phoneNumber;
    private int groupNumber;

    @ManyToOne
    private User user;

}
