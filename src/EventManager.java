import java.util.PriorityQueue;
public class EventManager{
    private long currentDate;
    private PriorityQueue<Event> eventQueue;

    public EventManager() {
        this.currentDate = 0;
        // on identique ici que les dates les plus faibles sont prioritaires
        this.eventQueue = new PriorityQueue<>((event1, event2) -> Long.compare(event1.getDate(), event2.getDate()));
    }

    public void addEvent(Event event) {
        eventQueue.add(event);
    }

    public void next() {
        currentDate++;
        while (!eventQueue.isEmpty() && eventQueue.peek().getDate() <= currentDate) {
            // c'est pour ça que ça renvoie d'abord PONG qui est ajouté après.
            Event event = eventQueue.poll();
            event.execute();
        }
    }

    public boolean isFinished() {
        return eventQueue.isEmpty();
    }

    public void restart() {
        currentDate = 0;
        eventQueue.clear();
    }
}
