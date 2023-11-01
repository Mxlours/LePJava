import gui . GUISimulator ;
import gui.Simulable;

import java.awt.*;

public class TestBallsSimulator {
    public static void main ( String [] args ) {
        GUISimulator gui = new GUISimulator (500 , 500 , Color . BLACK ) ;
        Point[] balls = { new Point(0, 0), new Point(0, 50), new Point(0, 100) };
        BallsSimulator balles_simu = new BallsSimulator (balls, gui);
        gui . setSimulable ( balles_simu );
    }
}