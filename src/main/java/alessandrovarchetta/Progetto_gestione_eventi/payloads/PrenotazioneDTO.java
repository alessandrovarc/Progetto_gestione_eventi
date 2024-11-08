package alessandrovarchetta.Progetto_gestione_eventi.payloads;

import jakarta.validation.constraints.NotNull;

public record PrenotazioneDTO(
        @NotNull(message = "L' id dell' evento è obbligatorio")
        long id_evento
) {
}