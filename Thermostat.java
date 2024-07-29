public class Thermostat extends AbstractDevice {
    private int temperature;

    public Thermostat(int id, int temperature) {
        super(id);
        this.temperature = temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getTemperature() {
        return this.temperature;
    }

    @Override
    public String getStatus() {
        return "Thermostat " + id + " is set to " + temperature + " degrees.";
    }
}

