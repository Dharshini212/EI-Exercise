
import java.time.LocalTime;

public class Schedule {
    private final int deviceId;
    private final LocalTime time;
    private final String command;

    public Schedule(int deviceId, LocalTime time, String command) {
        this.deviceId = deviceId;
        this.time = time;
        this.command = command;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getCommand() {
        return command;
    }
}

