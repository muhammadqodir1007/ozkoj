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
public class Accounting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String number;
    @ManyToOne
    private User user;
    private String type;
    private String year;
    private String period;
    private String address;
    private String file1;
    private String file2;
    private String file3;
    private String file4;


}
