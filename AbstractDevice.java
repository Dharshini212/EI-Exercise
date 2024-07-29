public abstract class AbstractDevice implements Device {
    protected int id;
    protected boolean status;

    public AbstractDevice(int id) {
        this.id = id;
        this.status = false;
    }

    @Override
    public void turnOn() {
        this.status = true;
    }

    @Override
    public void turnOff() {
        this.status = false;
    }

    @Override
    public int getId() {
        return this.id;
    }
}
