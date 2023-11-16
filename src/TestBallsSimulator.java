
import gui.GUISimulator;
import gui.Simulable;

import java.awt.*;
/**
 * TestBallsSimulator.java
 *
 * This program demonstrates the simulation of balls using a graphical user interface.
 * It creates a GUISimulator object with a black background and initializes an array of Point objects representing the balls' positions.
 * The BallsSimulator object is then created with the array of Point objects and the GUISimulator object as parameters.
 * Finally, the GUISimulator's simulable is set to the BallsSimulator object.
 */
public class TestBallsSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        Point[] balls = {new Point(0, 0), new Point(0, 50), new Point(0, 100)};
        BallsSimulator balles_simu = new BallsSimulator(balls, gui);
        gui.setSimulable(balles_simu);
    }
}