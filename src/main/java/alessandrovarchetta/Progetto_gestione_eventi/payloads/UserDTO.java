package alessandrovarchetta.Progetto_gestione_eventi.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserDTO(
        @NotEmpty(message = "L' username è obbligatorio")
        String username,
        @NotEmpty(message = "Il nome è obbligatorio")
        String nome,
        @NotEmpty(message = "Il cognome è obbligatorio")
        String cognome,
        @NotEmpty(message = "L'email è obbligatoria")
        @Email(message = "L'email deve essere valida")
        String email,
        @NotEmpty(message = "La password è obbligatoria")
        String password
) {
}
