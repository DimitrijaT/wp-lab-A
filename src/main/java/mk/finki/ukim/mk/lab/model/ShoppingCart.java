package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;
    //    @Column(columnDefinition = "DATE")
//    private LocalDate date;
//    @Column(columnDefinition = "TIMESTAMP")
//    private LocalDateTime dateTime;
//    @Column(columnDefinition = "TIME")
//    private LocalTime localTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateCreated;

    @OneToMany
    @JoinColumn(name = "orders_id")
    private List<Order> orders;

    public ShoppingCart() {
    }

    public ShoppingCart(User user) {
        this.user = user;
        orders = new ArrayList<>();
        dateCreated = LocalDateTime.now();
    }
}
