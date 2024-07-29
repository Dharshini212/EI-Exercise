public class DoorLock extends AbstractDevice {
    public DoorLock(int id) {
        super(id);
    }

    @Override
    public String getStatus() {
        return "Door " + id + " is " + (status ? "Locked" : "Unlocked");
    }
}
