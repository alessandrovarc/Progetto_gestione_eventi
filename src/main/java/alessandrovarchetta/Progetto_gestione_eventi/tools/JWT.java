package alessandrovarchetta.Progetto_gestione_eventi.tools;

import alessandrovarchetta.Progetto_gestione_eventi.entities.User;
import alessandrovarchetta.Progetto_gestione_eventi.exception.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.security.cert.Extension;
import java.util.Date;

@Component
public class JWT {
    @Value("${jwt.secret}")
    private String secret;



    public String createToken(User user) {
        Extension utente;
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .subject(String.valueOf(user.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public void verifyToken(String accessToken) {
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build().parse(accessToken);

        } catch (Exception ex) {
            throw new UnauthorizedException("Problemi con il token! Per favore effettua di nuovo il login!");
        }
    }

    public String getIdFromToken(String accessToken) {
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parseSignedClaims(accessToken)
                .getPayload().getSubject();
    }

}
