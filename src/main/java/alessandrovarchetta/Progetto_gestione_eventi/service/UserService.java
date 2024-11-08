package alessandrovarchetta.Progetto_gestione_eventi.service;


import alessandrovarchetta.Progetto_gestione_eventi.entities.User;
import alessandrovarchetta.Progetto_gestione_eventi.exception.BadRequestException;
import alessandrovarchetta.Progetto_gestione_eventi.exception.NotFoundException;
import alessandrovarchetta.Progetto_gestione_eventi.payloads.UserDTO;
import alessandrovarchetta.Progetto_gestione_eventi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    public User findById(long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con email " + email + " non è stato trovato!!"));
    }

    //GET
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    //POST
    public User save(UserDTO body) {
        //controllo se ci sta già un altro con la stessa email
        this.userRepository.findByEmail(body.email()).ifPresent(
                user -> {
                    throw new BadRequestException("Email " + body.email() + " già in uso!");
                }
        );
        //controllo anche dell'username, deve essere univoco
        this.userRepository.findByUsername(body.username()).ifPresent(
                user -> {
                    throw new BadRequestException("Username " + body.username() + " è già in uso!");
                }
        );
        User newUtente = new User(body.username(), body.nome(), body.cognome(), body.email(), bcrypt.encode(body.password()));
        return this.userRepository.save(newUtente);
    }

    //PUT --------------------------------------------
    public User findByIdAndUpdate(long id, UserDTO body) {
        User found = this.findById(id);

        if (!found.getEmail().equals(body.email())) {
            this.userRepository.findByEmail(body.email()).ifPresent(
                    user -> {
                        throw new BadRequestException("Email " + body.email() + " già in uso!");
                    }
            );
        }
        found.setUsername(body.username());
        found.setName(body.nome());
        found.setSurname(body.cognome());
        found.setEmail(body.email());

        return this.userRepository.save(found);
    }

    //DELETE --------------------------------------------
    public void findByIdAndDelete(long id) {
        User found = this.findById(id);
        this.userRepository.delete(found);
    }
}

