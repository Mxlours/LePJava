
import java.awt.*;

import gui.*;
/**
 * This class represents a balls simulator that implements the Simulable interface.
 * It creates a GUI with graphical elements representing balls at initial positions.
 * The simulator updates the positions of the balls in each iteration and handles restart functionality.
 */
public class BallsSimulator implements Simulable {
    private Balls balls;
    private GUISimulator gui;

    /**
     * Constructs a BallsSimulator object with given initial positions and GUI.
     * Initializes the balls and adds graphical elements to the GUI.
     * @param initialPositions the initial positions of the balls
     * @param gui the GUI to display the balls
     */
    public BallsSimulator(Point[] initialPositions, GUISimulator gui) {
        balls = new Balls(initialPositions);
        this.gui = gui;
        for (int i = 0; i < initialPositions.length; i++) {
            gui.addGraphicalElement(new Oval(initialPositions[i].x, initialPositions[i].y, Color.decode("#1f77b4"), Color.decode("#1f77b4"), 20, 20));
        }
    }

    /**
     * Updates the positions of the balls in each iteration.
     * If a ball reaches the edge of the GUI, its direction is reversed ( not done yet )
     * Adds the updated graphical elements to the GUI.
     */
    @Override
    public void next() {
        gui.reset();

        Point[] ballPositions = balls.getBalls();
        for (int i = 0; i < ballPositions.length; i++) {
            int x = ballPositions[i].x;
            int y = ballPositions[i].y;
            if (x >= gui.getWidth()) {
                balls.translate(-10, 0); // Reverse the x direction
            } else {
                balls.translate(10, 0);
            }
            if (y >= gui.getHeight()) {
                balls.translate(0, -10); // Reverse the y direction
            } else {
                balls.translate(0, 10);
            }
            gui.addGraphicalElement(new Oval(ballPositions[i].x, ballPositions[i].y, Color.decode("#1f77b4"), Color.decode("#1f77b4"), 50, 50));
        }
    }

    /**
     * Restarts the simulator by reinitializing the balls and resetting the GUI.
     * Adds the graphical elements representing the balls to the GUI.
     */
    @Override
    public void restart() {
        balls.reInit();
        gui.reset();
        Point[] ballPositions = balls.getBalls();
        for (int i = 0; i < ballPositions.length; i++) {
            gui.addGraphicalElement(new Oval(ballPositions[i].x, ballPositions[i].y, Color.decode("#1f77b4"), Color.decode("#1f77b4"), 50, 50));
        }
    }
}