package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String balloonColor;
    private String balloonSize;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreated;

    public Order(String balloonColor, String balloonSize, LocalDateTime dateCreated) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.dateCreated = dateCreated;
    }

    public Order() {

    }
}
