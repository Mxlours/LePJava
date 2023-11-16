

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import gui.GUISimulator;
/**
 * This code is written in Java and is stored in the file TestBoidsSimulator.java.
 * It creates a simulation of boids, which are virtual creatures that exhibit collective behavior.
 * The simulation is displayed using a graphical user interface (GUI) provided by the GUISimulator library.
 * The code defines the behavior and properties of the boids, such as their positions, velocities, orientations, and colors.
 * It also creates an event manager to handle the simulation events and manages the interaction between the boids.
 * The boids are represented by instances of the SpecialBoids class, which is a subclass of the Boids class.
 * The simulation is started by creating a BoidsSimulator object and setting it as the simulable for the GUI.
 */
public class TestBoidsSimulator {
    public static void main(String[] args) {
        int taille_bleu = 10;
        int taille_rouge = 1;
        int taille_tot = taille_rouge + taille_bleu;

        EventManager BoidsManager = new EventManager();
        GUISimulator gui = new GUISimulator(1600, 1000, Color.WHITE);
        SpecialBoids[] boids = new SpecialBoids[taille_bleu];

        // Create 4 boids with different positions, velocities, and orientations
        // 90 fais tourner vers la gauche, 270 vers la droite, sens trigo
        boids[0] = new SpecialBoids(0, 0, 1, 2, 180, 1600, 1000, Color.BLUE, "poisson");
        boids[1] = new SpecialBoids(530, 300, -1, -2, 90,1600, 1000, Color.BLUE, "poisson");
        boids[2] = new SpecialBoids(200, 350, -2, -1, 180,1600, 1000, Color.BLUE, "poisson");
        boids[3] = new SpecialBoids(400, 400, 1, -2, 0,1600, 1000, Color.BLUE, "poisson");
        boids[4] = new SpecialBoids(200, 200, 1, -2, 0,1600, 1000, Color.BLUE, "poisson");
        boids[5] = new SpecialBoids(400, 450, 1, -2, -90,1600, 1000, Color.BLUE, "poisson");
        boids[6] = new SpecialBoids(400, 500, 1, -2, 0,1600, 1000, Color.BLUE, "poisson");
        boids[7] = new SpecialBoids(180, 490, 1, -2, 0,1600, 1000, Color.BLUE, "poisson");
        boids[8] = new SpecialBoids(100, 100, 1, -2, 0,1600, 1000, Color.BLUE, "poisson");
        boids[9] = new SpecialBoids(800, 800, 1, -2, 0,1600, 1000, Color.BLUE, "poisson");

        // quand y'aura un autre
        SpecialBoids[] boids_red = new SpecialBoids[taille_rouge];

        boids_red[0] = new SpecialBoids(1000, 500, 3, 4, 180, 1600, 1000, Color.RED, "requin");
        // boids_red[1] = new SpecialBoids(1530, 300, -1, -2, 90,1600, 1000, Color.RED, "requin");
        // boids_red[2] = new SpecialBoids(1200, 350, -2, -1, 180,1600, 1000, Color.RED, "requin");
        // boids_red[3] = new SpecialBoids(1400, 400, 1, -2, 0,1600, 1000, Color.RED, "requin");
        // boids_red[4] = new Boids(1200, 200, 1, -2, 0,1600, 1000, Color.RED);
        // boids_red[5] = new Boids(1400, 450, 1, -2, -90,1600, 1000, Color.RED);
        // boids_red[6] = new Boids(1400, 500, 1, -2, 0,1600, 1000, Color.RED);
        // boids_red[7] = new Boids(1180, 490, 1, -2, 0,1600, 1000, Color.RED);
        // boids_red[8] = new Boids(1100, 100, 1, -2, 0,1600, 1000, Color.RED);
        // boids_red[9] = new Boids(1800, 800, 1, -2, 0,1600, 1000, Color.RED);

        SpecialBoids[] listeBoids = new SpecialBoids[taille_tot];
        // ATTENTION A CHANGER CA SI ON CHANGE LA TAILLE
        listeBoids[0] = boids[0];
        listeBoids[1] = boids[1];
        listeBoids[2] = boids[2];
        listeBoids[3] = boids[3];
        listeBoids[4] = boids[4];
        listeBoids[5] = boids[5];
        listeBoids[6] = boids[6];
        listeBoids[7] = boids[7];
        listeBoids[8] = boids[8];
        listeBoids[9] = boids[9];
        listeBoids[10] = boids_red[0];
        // listeBoids[11] = boids_red[1];
        // listeBoids[12] = boids_red[2];
        // listeBoids[13] = boids_red[3];
        // listeBoids[14] = boids_red[4];
        // listeBoids[15] = boids_red[5];
        // listeBoids[16] = boids_red[6];
        // listeBoids[17] = boids_red[7];
        // listeBoids[18] = boids_red[8];
        // listeBoids[19] = boids_red[9];

        BoidsManager.addEvent(new BoidsEvent(1, boids, Color.BLUE, gui, BoidsManager, listeBoids));
        BoidsManager.addEvent(new BoidsEvent(1, boids_red, Color.RED, gui, BoidsManager, listeBoids));
        BoidsSimulator simulator = new BoidsSimulator(listeBoids, gui, BoidsManager);
        gui.setSimulable(simulator);
    }
}
