package alessandrovarchetta.Progetto_gestione_eventi.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("Il record con id " + id + " non è stato trovato!");
    }
    public NotFoundException(String message) {

        super(message);
    }
}
