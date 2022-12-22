package mk.finki.ukim.mk.lab.model.exceptions;

public class ManufacturerNotFoundException extends RuntimeException {
    public ManufacturerNotFoundException(Long id) {
        super(String.format("Manufacturer with id: %l doesn't exist :(", id));
    }

    public ManufacturerNotFoundException() {
        super(String.format("Manufacturer doesn't exist :("));
    }
}
