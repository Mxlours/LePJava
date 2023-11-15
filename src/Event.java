/**
 * This is an abstract class representing an event.
 * It contains a date field and provides methods to get the date and execute the event.
 */
public abstract class Event {
    private long date;

    /**
     * Constructs an Event object with the given date.
     * @param date the date of the event
     */
    public Event(long date) {
        this.date = date;
    }

    /**
     * Gets the date of the event.
     * @return the date of the event
     */
    public long getDate() {
        return date;
    }

    /**
     * Executes the event.
     */
    public abstract void execute();
}
