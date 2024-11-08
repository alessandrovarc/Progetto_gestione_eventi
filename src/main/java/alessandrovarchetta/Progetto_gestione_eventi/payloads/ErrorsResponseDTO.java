package alessandrovarchetta.Progetto_gestione_eventi.payloads;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ErrorsResponseDTO(String message, LocalDateTime timestamp) {
}
