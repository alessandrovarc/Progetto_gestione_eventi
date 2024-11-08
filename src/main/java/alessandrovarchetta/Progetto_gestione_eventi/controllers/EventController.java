package alessandrovarchetta.Progetto_gestione_eventi.controllers;

import alessandrovarchetta.Progetto_gestione_eventi.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // Endpoint per creare un nuovo evento
    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    // Endpoint per prenotare un evento
    @PostMapping("/{eventId}/book")
    public ResponseEntity<String> bookEvent(@PathVariable Long eventId) {
        eventService.bookEvent(eventId);
        return new ResponseEntity<>("Prenotazione effettuata con successo", HttpStatus.OK);
    }
}

