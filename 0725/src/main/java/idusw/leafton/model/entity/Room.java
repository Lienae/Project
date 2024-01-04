package idusw.leafton.model.entity;

import idusw.leafton.model.DTO.RoomDTO;
import idusw.leafton.model.DTO.StyleDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "room")
@Getter
@Setter
public class Room { //add ten brid3
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomId")
    private Long roomId;

    @Column(columnDefinition = "LONGTEXT")
    private String unityData;

    public static Room toRoomEntity(RoomDTO roomDTO) {
        Room room = new Room();

        room.setRoomId(roomDTO.getRoomId());
        room.setUnityData(roomDTO.getUnityData());

        return room;
    }
}
