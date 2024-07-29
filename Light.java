
public class Light extends AbstractDevice {
    public Light(int id) {
        super(id);
    }

    @Override
    public String getStatus() {
        return "Light " + id + " is " + (status ? "On" : "Off");
    }
}

