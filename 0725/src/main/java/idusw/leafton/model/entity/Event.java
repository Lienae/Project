package idusw.leafton.model.entity;

import idusw.leafton.model.DTO.EventDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "event")
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventId")
    private Long eventId;

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    private Product product;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime date;

    @Column
    private String content;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime endDate;

    public static Event toEventEntity(EventDTO eventDTO) {
        Event event = new Event();

        event.setEventId(eventDTO.getEventId());
        event.setProduct(eventDTO.getProduct());
        event.setDate(eventDTO.getDate());
        event.setContent(eventDTO.getContent());
        event.setEndDate(event.getEndDate());

        return event;
    }
}
