package alessandrovarchetta.Progetto_gestione_eventi.repository;

import alessandrovarchetta.Progetto_gestione_eventi.entities.Prenotazione;
import alessandrovarchetta.Progetto_gestione_eventi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PrenotazioneRepo extends JpaRepository<Prenotazione,Long> {
    Optional<Prenotazione> findByUserAndDataEvent(User user, LocalDate dateEvent);
}