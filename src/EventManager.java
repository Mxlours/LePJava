/**
 * This class manages a priority queue of events and executes them in chronological order.
 */
import java.util.PriorityQueue;
public class EventManager{
    private long currentDate;
    private PriorityQueue<Event> eventQueue;

    public EventManager() {
        this.currentDate = 0;
        this.eventQueue = new PriorityQueue<>((event1, event2) -> Long.compare(event1.getDate(), event2.getDate()));
    }

    /**
     * Adds an event to the priority queue.
     * @param event Event to be added
     */
    public void addEvent(Event event) {
        eventQueue.add(event);
    }

    /**
     * Executes the next event in the queue that is scheduled to occur.
     */
    public void next() {
        currentDate++;
        while (!eventQueue.isEmpty() && eventQueue.peek().getDate() <= currentDate) {
            Event event = eventQueue.poll();
            event.execute();
        }
    }

    /**
     * Checks if the event queue is empty.
     * @return True if the queue is empty, false otherwise
     */
    public boolean isFinished() {
        return eventQueue.isEmpty();
    }

    /**
     * Restarts the event manager by resetting the current date and clearing the event queue.
     */
    public void restart() {
        currentDate = 0;
        eventQueue.clear();
    }
}
