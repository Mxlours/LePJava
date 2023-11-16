/*
 * Summary:
 * This code is a simulation of the Schelling model, which is a social simulation
 * model used to study segregation in a population. The code initializes a grid
 * of cells with random states, and then runs the simulation to observe the
 * dynamics of segregation.
 */

import gui.GUISimulator;

import java.awt.*;
import java.util.HashMap;

import gui.Rectangle;

import java.awt.Color;

// Récupérer la taille de l'écran
import java.awt.Dimension;
import java.awt.Toolkit;
// Nombre aléatoire
import java.util.Random;


public class TestShellingSimulator {
    // Constants
    public static final double DIVISON_ECHELLE = 1.8;
    public static final int NB_ETAT = 4;
    public static final int NB_VOISIN_CHANGEMENT = 3;

    public static void main(String[] args) {
        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int largeur = (int) (screenSize.getWidth() / DIVISON_ECHELLE / SchellingSimulator.TAILLE_CELLULE);
        int hauteur = (int) (screenSize.getHeight() / DIVISON_ECHELLE / SchellingSimulator.TAILLE_CELLULE);

        // Create GUISimulator instance
        GUISimulator gui = new GUISimulator(largeur * SchellingSimulator.TAILLE_CELLULE, hauteur * SchellingSimulator.TAILLE_CELLULE, Color.BLUE);

        // Create Schelling instance
        Schelling cell = new Schelling(largeur, hauteur, NB_ETAT, NB_VOISIN_CHANGEMENT);

        // Initialize random cells
        Random random = new Random();
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                cell.setBoolean_coord_Sche(random.nextInt(NB_ETAT), i, j);
            }
        }

        cell.InitConfigFirst();

        // Create SchellingSimulator instance
        SchellingSimulator simulator = new SchellingSimulator(cell, gui);
        simulator.restart();

        // Add simulator to the GUI window
        gui.setSimulable(simulator);
    }
}
