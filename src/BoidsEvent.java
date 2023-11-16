
import gui.GUISimulator;
import java.awt.*;
/**
 * BoidsEvent class represents an event that updates the behavior of a group of boids.
 * It contains methods to separate, align, and cohere the boids, as well as update their positions.
 * The class also handles the scheduling of new events based on the type of boids.
 */
public class BoidsEvent extends Event {
    private SpecialBoids[] BoidsAtraiter;
    private SpecialBoids[] allBoids;
    private Color colorClass;
    private GUISimulator gui;
    private EventManager BoidsManager;

    /**
     * Constructs a BoidsEvent object with the specified parameters.
     * @param date the date of the event
     * @param Boids the boids to be processed
     * @param colorClass the color class of the boids
     * @param gui the GUISimulator object for visualization
     * @param BoidsManager the EventManager object for scheduling events
     * @param allBoids all the boids in the simulation
     */
    public BoidsEvent(int date, SpecialBoids[] Boids, Color colorClass, GUISimulator gui, EventManager BoidsManager, SpecialBoids[] allBoids) {
        super(date);
        this.BoidsAtraiter = Boids;
        this.colorClass = colorClass;
        this.gui = gui;
        this.BoidsManager = BoidsManager;
        this.allBoids = allBoids;
    }

    /**
     * Executes the event by updating the behavior of the boids.
     * If the boids are of type "poisson", a new BoidsEvent is scheduled every 1 unit of time.
     * If the boids are of type "requin", a new BoidsEvent is scheduled every 2 units of time.
     */
    public void execute() {
        for (SpecialBoids boid : this.BoidsAtraiter) {
            boid.separate(this.allBoids, 90);
            boid.align(this.allBoids, 90);
            boid.cohere(this.allBoids, 500);
            boid.update();
        }
        if (BoidsAtraiter[0].getName().equals("poisson")) {
            // poisson ajouter tous les 1
            BoidsManager.addEvent(new BoidsEvent((int) this.getDate() + 1, this.BoidsAtraiter, Color.BLUE, gui, BoidsManager, allBoids));
        }
        if (BoidsAtraiter[0].getName().equals("requin")) {
            // requin tous les 2
            BoidsManager.addEvent(new BoidsEvent((int) this.getDate() + 2, this.BoidsAtraiter, Color.RED, gui, BoidsManager, allBoids));
        }
    }
}
