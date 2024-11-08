package alessandrovarchetta.Progetto_gestione_eventi.controllers;

import alessandrovarchetta.Progetto_gestione_eventi.entities.User;
import alessandrovarchetta.Progetto_gestione_eventi.exception.BadRequestException;
import alessandrovarchetta.Progetto_gestione_eventi.payloads.UserDTO;
import alessandrovarchetta.Progetto_gestione_eventi.payloads.UserLoginDTO;
import alessandrovarchetta.Progetto_gestione_eventi.payloads.UserLoginResponseDTO;
import alessandrovarchetta.Progetto_gestione_eventi.service.AuthService;
import alessandrovarchetta.Progetto_gestione_eventi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService usersService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        return new UserLoginResponseDTO(this.authService.checkCredentialsAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Validated UserDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return this.usersService.save(body);
    }
}