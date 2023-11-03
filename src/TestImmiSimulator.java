import gui.GUISimulator;

import java.awt.*;

import gui.Rectangle;

import java.awt.Color;

public class TestImmiSimulator {
    public static void main(String[] args) {
        // Création d'une instance de GUISimulator
        GUISimulator gui = new GUISimulator(500, 500, Color.BLUE);
        Immigration cell = new Immigration( 5, 6, 4);
        cell.setBoolean_coord_Immi(3, 0, 0);
        cell.setBoolean_coord_Immi(1, 2, 0);
        cell.setBoolean_coord_Immi(1, 3, 0);
        cell.setBoolean_coord_Immi(3, 0, 1);
        cell.setBoolean_coord_Immi(1, 1, 1);
        cell.setBoolean_coord_Immi(1, 2, 1);
        cell.setBoolean_coord_Immi(1, 3, 1);
        cell.setBoolean_coord_Immi(2, 4, 1);
        cell.setBoolean_coord_Immi(1, 0, 2);
        cell.setBoolean_coord_Immi(1, 1, 2);
        cell.setBoolean_coord_Immi(3, 2, 2);
        cell.setBoolean_coord_Immi(2, 3, 2);
        cell.setBoolean_coord_Immi(2, 4, 2);
        cell.setBoolean_coord_Immi(1, 1, 3);
        cell.setBoolean_coord_Immi(2, 2, 3);
        cell.setBoolean_coord_Immi(2, 3, 3);
        cell.setBoolean_coord_Immi(2, 4, 3);
        cell.setBoolean_coord_Immi(3, 1, 4);
        cell.setBoolean_coord_Immi(2, 2, 4);
        cell.setBoolean_coord_Immi(2, 3, 4);
        cell.setBoolean_coord_Immi(1, 4, 4);

        cell.InitConfigFirst();
        // Création d'une instance de CellSimulator
        ImmiSimulator simulator = new ImmiSimulator(cell, gui);

        // Ajout du simulateur à la fenêtre graphique
        gui.setSimulable(simulator);
    }
}
