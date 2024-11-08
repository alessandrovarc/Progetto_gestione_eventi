package alessandrovarchetta.Progetto_gestione_eventi.service;

import alessandrovarchetta.Progetto_gestione_eventi.entities.User;
import alessandrovarchetta.Progetto_gestione_eventi.exception.UnauthorizedException;
import alessandrovarchetta.Progetto_gestione_eventi.payloads.UserLoginDTO;
import alessandrovarchetta.Progetto_gestione_eventi.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService utenteService;

    @Autowired
    private JWT jwt;

    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentialsAndGenerateToken(UserLoginDTO body) {
        User found = this.utenteService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), found.getPassword())) {
            String accessToken = jwt.createToken(found);
            return accessToken;
        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }
    }
}
