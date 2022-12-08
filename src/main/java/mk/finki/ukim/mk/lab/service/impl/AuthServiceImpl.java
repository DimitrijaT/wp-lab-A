package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.UserFullname;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.mk.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.mk.lab.model.exceptions.UsernameAlreadyExistsException;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab.service.AuthService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository inMemoryUserRepository) {
        this.userRepository = inMemoryUserRepository;
    }


    @Override
    public User login(String username, String password) {

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUserCredentialsException::new);

    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, LocalDate dateOfBirth) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentsException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if (this.userRepository.findByUsername(username).isPresent() || !this.userRepository.findByUsername(username).isEmpty())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username, new UserFullname(name, surname), password, dateOfBirth);
        return userRepository.save(user);
    }
}