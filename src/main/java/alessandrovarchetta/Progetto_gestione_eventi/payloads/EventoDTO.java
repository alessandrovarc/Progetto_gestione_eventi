package alessandrovarchetta.Progetto_gestione_eventi.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EventoDTO(
        @NotEmpty(message = "Il titolo è obbligatorio")
        String titolo,
        @NotEmpty(message = "La descrizione è obbligatoria")
        String descrizione,
        @NotNull(message = "La data di prenotazione è obbligatoria!")
        LocalDate date,
        @NotEmpty(message = "Il luogo è obbligatorio")
        String luogo,
        @NotNull(message = "Il numero di posti è obbligatorio!")
        int posti
) {

}