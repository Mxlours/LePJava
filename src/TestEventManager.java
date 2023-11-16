/**
 * TestEventManager.java
 *
 * This program demonstrates the usage of the EventManager class to manage message events.
 * It creates an EventManager object and adds multiple MessageEvent objects with different messages and intervals.
 * The program then iterates through the events, printing the messages at the specified intervals.
 * The program waits for 1 second between each event.
 */

public class TestEventManager {
    public static void main(String[] args) throws InterruptedException {
        EventManager manager = new EventManager();

        // Add MessageEvent objects with [PING] message at even intervals
        for (int i = 2; i <= 10; i += 2){
            manager.addEvent(new MessageEvent(i, "[PING]"));
        }

        // Add MessageEvent objects with [PONG] message at multiples of 3 intervals
        for (int i = 3; i <= 9; i += 3){
            manager.addEvent(new MessageEvent(i, "[PONG]"));
        }

        // Iterate through the events and print messages at the specified intervals
        while(!manager.isFinished()) {
            manager.next();
            Thread.sleep(1000);
        }
    }
}
