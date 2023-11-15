/**
 * Represents a message event, derived from the base Event class.
 */
public class MessageEvent extends Event {
    private String message ;

    /**
     * Constructs a MessageEvent object with a specified date and message.
     * @param date the date of the event
     * @param message the message associated with the event
     */
    public MessageEvent(int date, String message) {
        super(date);
        this.message = message;
    }

    /**
     * Executes the message event by printing the date and message.
     */
    public void execute() {
        System.out.println(this.getDate() + this.message);
    }
}
