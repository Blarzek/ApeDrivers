package is.apedrivers;


public interface Bus {
    Subscription subscribe(Subscriber subscriber);

    void send(Message message);

    interface Subscription {
        void to(String type);
    }
}
