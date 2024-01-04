package idusw.leafton.model.DTO;

import idusw.leafton.model.entity.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoomDTO { //add ten brid3
    private Long roomId;

    private String unityData;

    public static RoomDTO toRoomDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setRoomId(room.getRoomId());
        roomDTO.setUnityData(room.getUnityData());

        return roomDTO;
    }
}
