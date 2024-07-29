public class DeviceFactory {
    public static Device createDevice(String type, int id, int... params) {
        switch (type.toLowerCase()) {
            case "light" -> {
                return new Light(id);
            }
            case "thermostat" -> {
                return new Thermostat(id, params.length > 0 ? params[0] : 70);
            }
            case "door" -> {
                return new DoorLock(id);
            }
            default -> throw new IllegalArgumentException("Unknown device type: " + type);
        }
    }
}


