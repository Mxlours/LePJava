import java.awt.Color;

import gui.GUISimulator;

public class TestBoidsSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(1600, 1000, Color.WHITE);
        Boids[] boids = new Boids[10];

        // Create 4 boids with different positions, velocities, and orientations
        // 90 fais tourner vers la gauche, 270 vers la droite, sens trigo
        boids[0] = new Boids(0, 0, 1, 2, 180);
        boids[1] = new Boids(530, 300, -1, -2, 90);
        boids[2] = new Boids(200, 350, -2, -1, 180);
        boids[3] = new Boids(400, 400, 1, -2, 0);
        boids[4] = new Boids(200, 200, 1, -2, 0);
        boids[5] = new Boids(400, 450, 1, -2, -90);
        boids[6] = new Boids(400, 500, 1, -2, 0);
        boids[7] = new Boids(180, 490, 1, -2, 0);
        boids[8] = new Boids(100, 100, 1, -2, 0);
        boids[9] = new Boids(800, 800, 1, -2, 0);

        BoidsSimulator simulator = new BoidsSimulator(boids, gui);
        gui.setSimulable(simulator);
    }
}
