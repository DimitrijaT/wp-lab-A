package mk.finki.ukim.mk.lab.model.exceptions;

public class OrderAlreadyInShoppingCartException extends RuntimeException{

    public OrderAlreadyInShoppingCartException(Long id, String message) {
        super(message);
    }
}
