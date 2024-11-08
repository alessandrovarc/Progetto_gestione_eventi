package alessandrovarchetta.Progetto_gestione_eventi.service;

import alessandrovarchetta.Progetto_gestione_eventi.entities.Event;
import alessandrovarchetta.Progetto_gestione_eventi.entities.User;
import alessandrovarchetta.Progetto_gestione_eventi.exception.BadRequestException;
import alessandrovarchetta.Progetto_gestione_eventi.exception.NotFoundException;
import alessandrovarchetta.Progetto_gestione_eventi.payloads.EventoDTO;
import alessandrovarchetta.Progetto_gestione_eventi.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {
    @Autowired
    EventRepository eventoRepository;

    @Autowired
    UserService utenteService;

    public List<Event> findAll() {
        return this.eventoRepository.findAll();
    }

    public Event findById(long id) {
        return this.eventoRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    //POST
    public Event save(EventoDTO body, User utente) {
        Event newEvento = new Event(body.titolo(), body.descrizione(), body.date(), body.luogo(), body.posti(), utente);
        return this.eventoRepository.save(newEvento);
    }

    //PUT
    public Event findByIdAndUpdate(long id, EventoDTO body, User utente) {
        Event found = this.findById(id);

        if(found.getOrganizer().getId() != utente.getId())
            throw new BadRequestException("NOn hai i permessi per modificare questo evento");

        found.setTitle(body.titolo());
        found.getDescription(body.descrizione());
        found.setDate(body.date());
        found.setLocation(body.luogo());
        found.setAvailableSeats(body.posti());

        return this.eventoRepository.save(found);
    }

    //DELETE
    public void findByIdAndDelete(long id, User utente) {
        Event found = this.findById(id);
        if(found.getOrganizer().getId() != utente.getId())
            throw new BadRequestException("NOn hai i permessi per eliminare questo evento");

        this.eventoRepository.delete(found);
    }

}
