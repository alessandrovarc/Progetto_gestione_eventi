package alessandrovarchetta.Progetto_gestione_eventi.repository;

import alessandrovarchetta.Progetto_gestione_eventi.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByAvailableSeatsGreaterThan(int seats);
}
