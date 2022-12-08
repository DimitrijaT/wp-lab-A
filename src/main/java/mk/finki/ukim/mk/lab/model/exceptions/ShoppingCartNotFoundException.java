package mk.finki.ukim.mk.lab.model.exceptions;

public class ShoppingCartNotFoundException extends RuntimeException{
    public ShoppingCartNotFoundException(Long cartId) {
        super(String.format("%d shopping cart not found",cartId));
    }
}
