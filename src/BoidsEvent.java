import gui.GUISimulator;

import java.awt.*;

public class BoidsEvent extends Event{
    private SpecialBoids[] BoidsAtraiter ;

    private SpecialBoids[] allBoids;

    private Color colorClass;

    private GUISimulator gui;

    private EventManager BoidsManager;
    public BoidsEvent (int date , SpecialBoids[] Boids , Color colorClass, GUISimulator gui, EventManager BoidsManager, SpecialBoids[] allBoids) {
        super(date) ;
        this.BoidsAtraiter = Boids;
        this.colorClass = colorClass;
        this.gui = gui;
        this.BoidsManager = BoidsManager;
        this.allBoids = allBoids;
    }
    public void execute () {
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
