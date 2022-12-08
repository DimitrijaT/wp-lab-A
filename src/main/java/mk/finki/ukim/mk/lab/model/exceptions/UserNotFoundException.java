package mk.finki.ukim.mk.lab.model.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String username) {
        super(String.format("%s Username already exists exception thrown!!!", username));
    }
}
