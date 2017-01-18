package is.apedrivers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleBus implements Bus {
    private Map<String, List<Subscriber>> subscribers = new HashMap<>();

    private List<Subscriber> subscribersOf(String type) {
        createSubscribersListIfNotExist(type);
        return subscribers.get(type);
    }

    private void createSubscribersListIfNotExist(String type) {
        if (!subscribers.containsKey(type)) subscribers.put(type, new ArrayList<>());
    }

    @Override
    public Subscription subscribe(Subscriber subscriber) {
        return type -> subscribersOf(type).add(subscriber);
    }

    @Override
    public void send(Message message) {
        subscribersOf(message.type()).forEach(s -> s.receive(message));
    }
}
