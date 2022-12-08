package mk.finki.ukim.mk.lab.model.exceptions;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(Long ballonId) {
        super(String.format("Balloon with %d not found!",ballonId));
    }
}
