package alessandrovarchetta.Progetto_gestione_eventi.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Event event;

    public  Prenotazione() {}

    public Prenotazione(User user, Event event) {
        this.user = user;
        this.event = event;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", user=" + user +
                ", event=" + event +
                '}';
    }
}
