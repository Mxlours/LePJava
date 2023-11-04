import gui.GUISimulator;

import java.awt.*;
import java.util.HashMap;

import gui.Rectangle;

import java.awt.Color;
public class TestShellingSimulator {
    public static void main(String[] args) {
        // Création d'une instance de GUISimulator
        GUISimulator gui = new GUISimulator(500, 500, Color.BLUE);
        Schelling cell = new Schelling( 5, 6, 4, 3);
        cell.setBoolean_coord_Sche(3, 0, 0);
        cell.setBoolean_coord_Sche(1, 2, 0);
        cell.setBoolean_coord_Sche(1, 3, 0);
        cell.setBoolean_coord_Sche(3, 0, 1);
        cell.setBoolean_coord_Sche(1, 1, 1);
        cell.setBoolean_coord_Sche(1, 2, 1);
        cell.setBoolean_coord_Sche(1, 3, 1);
        cell.setBoolean_coord_Sche(2, 4, 1);
        cell.setBoolean_coord_Sche(1, 0, 2);
        cell.setBoolean_coord_Sche(1, 1, 2);
        cell.setBoolean_coord_Sche(3, 2, 2);
        cell.setBoolean_coord_Sche(2, 3, 2);
        cell.setBoolean_coord_Sche(2, 4, 2);
        cell.setBoolean_coord_Sche(1, 1, 3);
        cell.setBoolean_coord_Sche(2, 2, 3);
        cell.setBoolean_coord_Sche(2, 3, 3);
        cell.setBoolean_coord_Sche(4, 4, 3);
        cell.setBoolean_coord_Sche(3, 1, 4);
        cell.setBoolean_coord_Sche(2, 2, 4);
        cell.setBoolean_coord_Sche(2, 3, 4);
        cell.setBoolean_coord_Sche(1, 4, 4);
        cell.setBoolean_coord_Sche(4, 0, 5);
        cell.setBoolean_coord_Sche(4, 1, 5);
        cell.setBoolean_coord_Sche(3, 2, 5);

        cell.setBoolean_coord_Sche(4, 4, 5);

        cell.InitConfigFirst();
        // Création d'une instance de CellSimulator
        SchellingSimulator simulator = new SchellingSimulator(cell, gui);

        // Ajout du simulateur à la fenêtre graphique
        gui.setSimulable(simulator);
    }
}
