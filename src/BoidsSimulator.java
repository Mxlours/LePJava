

import java.awt.*;

import gui.*;
import gui.Rectangle;
/**
 * BoidsSimulator.java
 *
 * This file contains the implementation of a Boids simulator. It uses the GUISimulator library to display graphical elements representing boids. The simulator allows for restarting the simulation and advancing to the next iteration.
 *
 * The BoidsSimulator class defines methods for drawing boids, restarting the simulation, and advancing to the next iteration. It also contains a constructor that initializes the simulator with a list of boids, a graphical user interface, and an event manager.
 *
 * The simulator uses the list of boids to draw each boid on the GUI. The restart() method resets the position and orientation of each boid, and updates the graphical elements accordingly. The next() method advances the simulation to the next iteration by updating the boids' positions and redrawing them on the GUI.
 */
public class BoidsSimulator implements Simulable {
    private SpecialBoids[] list_Boids;
    private GUISimulator gui;

    private EventManager BoidsEvent;

    /**
 * This method draws a boid based on its color. It creates a triangle element with specific coordinates and color, and adds it to the graphical user interface.
 * If the boid's color is blue, it creates a triangle with specific coordinates and orientation.
 * If the boid's color is red, it creates a different triangle with specific coordinates and orientation.
 * @param boid a special boid which has to be drawn
 */
    private void Draw_Boid(SpecialBoids boid) {
        if(boid.getColor().equals(Color.BLUE)){
            int[] abs = {boid.getPosition()[0], boid.getPosition()[0] - 12, boid.getPosition()[0] - 12};
            int[] ord = {boid.getPosition()[1], boid.getPosition()[1] - 5, boid.getPosition()[1] + 5};
            TriangleElement triangleElement = new TriangleElement(abs, ord, boid.getColor(), boid.getOrientation());
            gui.addGraphicalElement(triangleElement);
        } else if(boid.getColor().equals(Color.RED)){
            int[] abs = {boid.getPosition()[0], boid.getPosition()[0] - 30, boid.getPosition()[0] - 30};
            int[] ord = {boid.getPosition()[1], boid.getPosition()[1] - 20, boid.getPosition()[1] + 20};
            TriangleElement triangleElement = new TriangleElement(abs, ord, boid.getColor(), boid.getOrientation());
            gui.addGraphicalElement(triangleElement);
        }
        //gui.addGraphicalElement(new Rectangle(20, 20, Color.decode("#1f77b4"), Color.decode("#1f77b4"), 20));
    }

    /**
     * Constructor for BoidsSimulator class.
     *
     * @param list_Boids   an array of SpecialBoids representing the boids in the simulation
     * @param gui          the GUISimulator object used for displaying graphical elements
     * @param BoidsEvent   the EventManager object used for managing events in the simulation
     */
    public BoidsSimulator(SpecialBoids[] list_Boids, GUISimulator gui, EventManager BoidsEvent) {
        this.list_Boids = list_Boids;
        this.gui = gui;
        this.BoidsEvent = BoidsEvent;
        for (SpecialBoids boid : list_Boids) {
            Draw_Boid(boid);
        }
    }

    /**
     * Restarts the simulation by resetting the position and orientation of each boid and updating the graphical elements.
     */
    @Override
    public void restart() {
        gui.reset();
        for (SpecialBoids boid : list_Boids) {
            // Reset the position and orientation of each boid
            boid.reset(); // Implement the reset() method in your Boids class
            // Update the graphical element's position and orientation
            Draw_Boid(boid);
        }
    }

    /**
     * Advances the simulation to the next iteration by updating the boids' positions and redrawing them on the GUI.
     */
    @Override
    // bon finalement j'uilise le même tableau de boids pour tout les boids, psk j'ai fait le test en interne donc oklm
    public void next() {
        gui.reset();
        // AU MOINS UNE CLASSE A DESSINER A CHAQUE ITERATION
        BoidsEvent.next();
        for(SpecialBoids boid : list_Boids){
            Draw_Boid(boid);
        }
    }
}