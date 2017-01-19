package is.apedrivers;

import is.apedrivers.sensors.CarAheadSpeedSensor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class CarAheadSpeedSensor_ {

    private CarAheadSpeedSensor carAheadSpeedSensor;
    private Bus bus;

    @Before

    public void setUp() throws Exception {
        bus = mock(Bus.class);
        this.carAheadSpeedSensor = new CarAheadSpeedSensor(bus);

    }

    private Message createMessageOfType(String type) {
        Message message = mock(Message.class);
        doReturn(type).when(message).type();
        return message;
    }

    private Subscriber createSubscriberToType(String type) {
        Subscriber subscriber = mock(Subscriber.class);
        bus.subscribe(subscriber).to(type);
        return subscriber;
    }
    @Test
    public void should_publish_a_message_of_type_CarAheadSpeed() throws Exception {
        Message message = createMessageOfType("CarAheadSpeed");
        carAheadSpeedSensor.publish(message);
        verify(bus, times(1)).send(message);
    }

    @Test
    public void should_not_publish_a_message_if_type_is_not_CarAheadSpeed() throws Exception {
        Message message = createMessageOfType("faa");
        carAheadSpeedSensor.publish(message);
        verify(bus, times(0)).send(message);

    }
    @Test
    public void should_send_many_messages_to_subscriber_with_correct_type() throws Exception {
        carAheadSpeedSensor.publish(createMessageOfType("faa"));
        carAheadSpeedSensor.publish(createMessageOfType("faa"));
        carAheadSpeedSensor.publish(createMessageOfType("CarAheadSpeed"));
        carAheadSpeedSensor.publish(createMessageOfType("CarAheadSpeed"));
        carAheadSpeedSensor.publish(createMessageOfType("fee"));
        carAheadSpeedSensor.publish(createMessageOfType("fee"));
        ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
        verify(bus, times(2)).send(captor.capture());
        captor.getAllValues().forEach(m -> assertThat(m.type(), is("CarAheadSpeed")));
    }
}
