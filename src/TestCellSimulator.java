import gui.GUISimulator;
import gui.Rectangle;

import java.awt.Color;

public class TestCellSimulator {
    public static void main(String[] args) {
        // Création d'une instance de GUISimulator
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        Cell cell = new Cell( 100, 100);
        cell.setBoolean_coord(true, 50, 50);
        cell.setBoolean_coord(true, 52, 51);
        cell.setBoolean_coord(true, 50, 52);
        cell.setBoolean_coord(true, 49, 52);
        cell.setBoolean_coord(true, 53, 52);
        cell.setBoolean_coord(true, 54, 52);
        cell.setBoolean_coord(true, 55, 52);

        cell.InitConfigFirst();
        // Création d'une instance de CellSimulator
        CellSimulator simulator = new CellSimulator(cell, gui);

        // Ajout du simulateur à la fenêtre graphique
        gui.setSimulable(simulator);
    }
}
