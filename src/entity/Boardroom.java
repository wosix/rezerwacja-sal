package entity;

public class Boardroom {

    private Long id;
    private RoomType roomType;
    private RoomSize roomSize;
    private Equipment equipment;
    private boolean active = true;

    public Boardroom(Long id, RoomType type, RoomSize size, Equipment equipment) {
        this.id = id;
        this.roomType = type;
        this.roomSize = size;
        this.equipment = equipment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoomSize getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(RoomSize roomSize) {
        this.roomSize = roomSize;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
