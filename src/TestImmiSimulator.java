

import gui.GUISimulator;

import java.awt.*;

import gui.Rectangle;

import java.awt.Color;

// Récupérer la taille de l'écran
import java.awt.Dimension;
import java.awt.Toolkit;
// Nombre aléatoire
import java.util.Random;
/**
 * This code is a Java program that demonstrates the simulation of an immigration process.
 * It uses the GUISimulator library to create a graphical user interface and displays the simulation on a blue background.
 * The simulation is based on the Immigration class, which represents a grid of cells with different states.
 * The initial state of the cells is randomly assigned using the Random class.
 * The ImmiSimulator class is responsible for running the simulation and updating the GUI accordingly.
 * The main method initializes the necessary variables, creates an instance of GUISimulator, and adds the ImmiSimulator to the GUI.
 */
public class TestImmiSimulator {
    public static final double DIVISON_ECHELLE = 1.8;
    public static final int NB_ETAT = 4;

    public static void main(String[] args) {
        // Taille de l'écran divisée par 2
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int largeur = (int) (screenSize.getWidth() / DIVISON_ECHELLE / ImmiSimulator.TAILLE_CELLULE);
        int hauteur = (int) (screenSize.getHeight() / DIVISON_ECHELLE / ImmiSimulator.TAILLE_CELLULE);

        // Création d'une instance de GUISimulator
        GUISimulator gui = new GUISimulator(largeur * ImmiSimulator.TAILLE_CELLULE, hauteur * ImmiSimulator.TAILLE_CELLULE, Color.BLUE);
        Immigration cell = new Immigration(largeur, hauteur, NB_ETAT);

        // Initialisation random ds cellules
        Random random = new Random();
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                cell.setBoolean_coord_Immi(random.nextInt(NB_ETAT), i, j);
            }
        }

        cell.InitConfigFirst();

        // Création d'une instance de CellSimulator
        ImmiSimulator simulator = new ImmiSimulator(cell, gui);

        // Ajout du simulateur à la fenêtre graphique
        gui.setSimulable(simulator);
    }
}
