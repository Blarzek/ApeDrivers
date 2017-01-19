package is.apedrivers.sensors;

import is.apedrivers.Bus;
import is.apedrivers.Message;
import is.apedrivers.sensors.VirtualSensor;

public class CarAheadSpeedSensor implements VirtualSensor{

    private Bus bus;
    private double carAheadSpeed;
    private double ownSpeed;
    public CarAheadSpeedSensor(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void receive(Message message) {
        
    }

    @Override
    public void publish(Message message) {
        if("CarAheadSpeed".equals(message.type())) bus.send(message);
    }
}
