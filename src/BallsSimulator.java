import java.awt.*;

import gui.*;
public class BallsSimulator implements Simulable {
    private Balls balls;
    private GUISimulator gui;

    public BallsSimulator(Point[] initialPositions, GUISimulator gui) {
        balls = new Balls(initialPositions);
        this.gui = gui;
        for (int i = 0; i < initialPositions.length; i++) {
            gui.addGraphicalElement(new Oval(initialPositions[i].x, initialPositions[i].y, Color.decode("#1f77b4"), Color.decode("#1f77b4"), 20, 20));
        }
    }

    @Override
    public void next() {
        gui.reset();

        Point[] ballPositions = balls.getBalls();
        for (int i = 0; i < ballPositions.length; i++) {
            int x = ballPositions[i].x;
            int y = ballPositions[i].y;
            if (x >= gui.getWidth()) {
                balls.translate(-10, 0); // Reverse the x direction
            }
            else{
                balls.translate(10, 0);
            }
            if (y >= gui.getHeight()) {
                balls.translate(0, -10); // Reverse the y direction
            }
            else{
                balls.translate(0, 10);
            }
            gui.addGraphicalElement(new Oval(ballPositions[i].x, ballPositions[i].y, Color.decode("#1f77b4"), Color.decode("#1f77b4"), 50, 50));

        }

    }

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