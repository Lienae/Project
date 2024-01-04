package idusw.leafton.model.DTO;

import idusw.leafton.model.entity.Event;
import idusw.leafton.model.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class EventDTO {
    private Long eventId;

    //private Image image;

    private Product product;

    private LocalDateTime date;

    private String content;

    private LocalDateTime endDate;

    public static EventDTO toEventDTO(Event event) {
        EventDTO eventDTO = new EventDTO();

        eventDTO.setEventId(event.getEventId());
        //eventDTO.setImage(event.getImage());
        eventDTO.setProduct(event.getProduct());
        eventDTO.setDate(event.getDate());
        eventDTO.setContent(event.getContent());
        eventDTO.setEndDate(event.getEndDate());

        return eventDTO;
    }
}
