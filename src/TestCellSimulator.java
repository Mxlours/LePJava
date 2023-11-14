import gui.GUISimulator;
import gui.Rectangle;

import java.awt.Color;

public class TestCellSimulator {
    public static void main(String[] args) {
        // Création d'une instance de GUISimulator
        GUISimulator gui = new GUISimulator(500, 500, Color.GREEN);
        Cell cell = new Cell(50, 100);
        cell.setBoolean_coord(1, 30, 30);
        cell.setBoolean_coord(1, 32, 31);
        cell.setBoolean_coord(1, 30, 32);
        cell.setBoolean_coord(1, 29, 32);
        cell.setBoolean_coord(1, 33, 32);
        cell.setBoolean_coord(1, 34, 32);
        cell.setBoolean_coord(1, 35, 32);

        cell.InitConfigFirst();
        // Création d'une instance de CellSimulator
        CellSimulator simulator = new CellSimulator(cell, gui);

        // Ajout du simulateur à la fenêtre graphique
        gui.setSimulable(simulator);
    }
}
