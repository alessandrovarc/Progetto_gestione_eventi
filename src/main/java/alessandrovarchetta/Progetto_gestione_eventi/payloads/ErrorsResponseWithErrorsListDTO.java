package alessandrovarchetta.Progetto_gestione_eventi.payloads;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorsResponseWithErrorsListDTO(String message, LocalDateTime timestamp, Map<String, String> errors) {
}
