import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        SmartHomeHub hub = new SmartHomeHub();

        Device light = DeviceFactory.createDevice("light", 1);
        Device thermostat = DeviceFactory.createDevice("thermostat", 2, 70);
        Device doorLock = DeviceFactory.createDevice("door", 3);

        hub.addDevice(light);
        hub.addDevice(thermostat);
        hub.addDevice(doorLock);

        System.out.println("Initial Status:");
        System.out.println(hub.getStatusReport());

        hub.turnOnDevice(1);
        System.out.println("Status After Turning On Light 1:");
        System.out.println(hub.getStatusReport());

        hub.addSchedule(1, LocalTime.now().plusSeconds(10), "turn off");
        System.out.println("Schedule Added: Turn off Light 1 in 10 seconds");

        hub.addTrigger("temperature > 75", "turnOff(1)");
        System.out.println("Trigger Added: Turn off Light 1 if temperature > 75");

        // Simulate temperature change
        if (thermostat instanceof Thermostat thermostat1) {
            thermostat1.setTemperature(80);
            hub.checkTriggers();
        }

        System.out.println("Final Status:");
        System.out.println(hub.getStatusReport());
    }
}
