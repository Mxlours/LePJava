import java.awt.*;

import gui.*;
import gui.Rectangle;

public class BoidsSimulator implements Simulable {
    private SpecialBoids[] list_Boids;
    private GUISimulator gui;

    private EventManager BoidsEvent;

    private void Draw_Boid(SpecialBoids boid) {
        // de base on pointe vers la droite c'est plus logique pour les calculs de trigo
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

    public BoidsSimulator(SpecialBoids[] list_Boids, GUISimulator gui, EventManager BoidsEvent) {
        this.list_Boids = list_Boids;
        this.gui = gui;
        this.BoidsEvent = BoidsEvent;
        for (SpecialBoids boid : list_Boids) {
            Draw_Boid(boid);
        }
    }

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