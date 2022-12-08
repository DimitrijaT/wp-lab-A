package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "balloon_shop_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;


    //    private String name;
    //    private String surname;

    @Convert(converter = UserFullnameConverter.class)
    private UserFullname userFullname;
    private String password;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<ShoppingCart> carts;

    public User(String username, UserFullname userFullname, String password, LocalDate dateOfBirth) {
        this.username = username;
        this.userFullname = userFullname;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
//        this.carts = new ArrayList<>();
    }

    public User(UserFullname userFullname) {
        this.userFullname = userFullname;
    }

    public User() {
    }
}
