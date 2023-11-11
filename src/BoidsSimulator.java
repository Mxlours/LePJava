import java.awt.*;
import gui.*;
import gui.Rectangle;

public class BoidsSimulator implements Simulable
{
    private Boids[] list_Boids;
    private GUISimulator gui;

    private void set_Boid(Boids boid, Color couleur){
        int[] abs = {boid.getPosition()[0], boid.getPosition()[0] - 10, boid.getPosition()[0] + 10};
        int[] ord = {boid.getPosition()[1], boid.getPosition()[1] + 20, boid.getPosition()[1] + 20};
        TriangleElement triangleElement= new TriangleElement(abs, ord, couleur, boid.getOrientation());
        gui.addGraphicalElement(triangleElement);
        //gui.addGraphicalElement(new Rectangle(20, 20, Color.decode("#1f77b4"), Color.decode("#1f77b4"), 20));

    }

    private void Draw_Boid(Boids boid, Color couleur){
        //Première approche: à l'état initial, tous les boids sont initialisé en étant orienté vers le haut, on changera ensuite
        // la direction lors de l'éxécution.
        set_Boid(boid, couleur);
    }

    public BoidsSimulator(Boids[] list_Boids, GUISimulator gui){
        this.list_Boids = list_Boids;
        this.gui = gui;
        for ( Boids boid: list_Boids){
            Draw_Boid(boid, Color.BLUE);
        }
    }
    @Override
    public void restart() {
        gui.reset();
        for (Boids boid : list_Boids) {
            // Reset the position and orientation of each boid
            boid.reset(); // Implement the reset() method in your Boids class
            // Update the graphical element's position and orientation
            Draw_Boid(boid, Color.BLUE);
        }
    }
    @Override
    // bon finalement j'uilise le même tableau de boids pour tout les boids, psk j'ai fait le test en interne donc oklm
    public void next() {
        gui.reset();
        for (Boids boid : list_Boids) {
            boid.separate(list_Boids, 40);
            boid.align(list_Boids, 80);
            boid.cohere(list_Boids, 60);
            boid.update();
            // Update the graphical element's position and orientation
            Draw_Boid(boid, Color.BLUE);
        }
    }
}