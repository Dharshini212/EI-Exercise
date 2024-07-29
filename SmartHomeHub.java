

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class SmartHomeHub extends DeviceSubject {
    private final Map<Integer, Device> devices = new HashMap<>();
    private final List<Schedule> schedules = new ArrayList<>();
    private final List<Trigger> triggers = new ArrayList<>();
    private final Timer timer = new Timer(true); // Timer to handle scheduling

    public void addDevice(Device device) {
        devices.put(device.getId(), device);
        notifyObservers(device);
    }

    public void removeDevice(int deviceId) {
        devices.remove(deviceId);
        notifyObservers(null);
    }

    public void turnOnDevice(int deviceId) {
        Device device = devices.get(deviceId);
        if (device != null) {
            device.turnOn();
            notifyObservers(device);
        }
    }

    public void turnOffDevice(int deviceId) {
        Device device = devices.get(deviceId);
        if (device != null) {
            device.turnOff();
            notifyObservers(device);
        }
    }

    public void addSchedule(int deviceId, LocalTime time, String command) {
        Schedule schedule = new Schedule(deviceId, time, command);
        schedules.add(schedule);
        scheduleTask(schedule);
    }

    private void scheduleTask(Schedule schedule) {
        LocalTime now = LocalTime.now();
        long delay = schedule.getTime().toSecondOfDay() - now.toSecondOfDay();
        if (delay < 0) delay += 24 * 60 * 60; // Handle tasks scheduled for the next day

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                executeCommand(schedule.getDeviceId(), schedule.getCommand());
            }
        }, delay * 1000);
    }

    private void executeCommand(int deviceId, String command) {
        switch (command.toLowerCase()) {
            case "turn on" -> turnOnDevice(deviceId);
            case "turn off" -> turnOffDevice(deviceId);
            default -> throw new IllegalArgumentException("Unknown command: " + command);
        }
    }

    public void addTrigger(String condition, String action) {
        triggers.add(new Trigger(condition, action));
    }

    public void checkTriggers() {
        for (Trigger trigger : triggers) {
            if (evaluateCondition(trigger.getCondition())) {
                executeAction(trigger.getAction());
            }
        }
    }

    private boolean evaluateCondition(String condition) {
        // Basic example for temperature check
        if (condition.startsWith("temperature >")) {
            int threshold = Integer.parseInt(condition.split(">")[1].trim());
            for (Device device : devices.values()) {
                if (device instanceof Thermostat thermostat) {
                    if (thermostat.getTemperature() > threshold) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void executeAction(String action) {
        if (action.startsWith("turnOff(")) {
            int deviceId = Integer.parseInt(action.substring(8, action.length() - 1));
            turnOffDevice(deviceId);
        } else if (action.startsWith("turnOn(")) {
            int deviceId = Integer.parseInt(action.substring(7, action.length() - 1));
            turnOnDevice(deviceId);
        }
    }

    public String getStatusReport() {
        StringBuilder statusReport = new StringBuilder();
        for (Device device : devices.values()) {
            statusReport.append(device.getStatus()).append("\n");
        }
        return statusReport.toString();
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public List<Trigger> getTriggers() {
        return triggers;
    }
}
