package mk.finki.ukim.mk.lab.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException(String username) {
        super(String.format("%s Username already exists exception thrown!!!", username));
    }
}
