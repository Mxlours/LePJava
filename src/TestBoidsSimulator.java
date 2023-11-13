import java.awt.Color;
import gui.GUISimulator;

public class TestBoidsSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(800, 800, Color.WHITE);
        Boids[] boids = new Boids[4];

        // Create 4 boids with different positions, velocities, and orientations
        // 90 fais tourner vers la gauche, 270 vers la droite, sens trigo
        boids[0] = new Boids(100, 200, 2, 40, 270);
        boids[1] = new Boids(530, 300, -1, -20, 90);
        boids[2] = new Boids(400, 350, -2, -8, 180 );
        boids[3] = new Boids(400, 400, 1, -12, 0);

        BoidsSimulator simulator = new BoidsSimulator(boids, gui);
        gui.setSimulable(simulator);
    }
}
