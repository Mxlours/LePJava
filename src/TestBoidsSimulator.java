import java.awt.Color;
import gui.GUISimulator;

public class TestBoidsSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(800, 800, Color.WHITE);
        Boids[] boids = new Boids[4];

        // Create 4 boids with different positions, velocities, and orientations
        boids[0] = new Boids(100, 200, 2, 40, (int)Math.PI / 2);
        boids[1] = new Boids(530, 300, -1, -20, 0);
        boids[2] = new Boids(400, 650, -2, -8, 0 );
        boids[3] = new Boids(400, 400, 1, -12, 0);

        BoidsSimulator simulator = new BoidsSimulator(boids, gui);
        gui.setSimulable(simulator);
    }
}
