
public class DeviceProxy implements Device {
    private final Device device;

    public DeviceProxy(Device device) {
        this.device = device;
    }

    @Override
    public void turnOn() {
        // Access control logic
        device.turnOn();
    }

    @Override
    public void turnOff() {
        // Access control logic
        device.turnOff();
    }

    @Override
    public String getStatus() {
        return device.getStatus();
    }

    @Override
    public int getId() {
        return device.getId();
    }
}

