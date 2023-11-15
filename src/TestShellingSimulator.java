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
    public static final double DIVISON_ECHELLE = 1.8;
    public static final int NB_ETAT = 4;
    public static final int NB_VOISIN_CHANGEMENT = 3;

    public static void main(String[] args) {
        // Taille de l'écran divisée par 2
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int largeur = (int) (screenSize.getWidth() / DIVISON_ECHELLE / SchellingSimulator.TAILLE_CELLULE);
        int hauteur = (int) (screenSize.getHeight() / DIVISON_ECHELLE / SchellingSimulator.TAILLE_CELLULE);

        // Création d'une instance de GUISimulator
        GUISimulator gui = new GUISimulator(largeur * SchellingSimulator.TAILLE_CELLULE, hauteur * SchellingSimulator.TAILLE_CELLULE, Color.BLUE);
        Schelling cell = new Schelling(largeur, hauteur, NB_ETAT, NB_VOISIN_CHANGEMENT);


        // Initialisation random ds cellules
        Random random = new Random();
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                cell.setBoolean_coord_Sche(random.nextInt(NB_ETAT), i, j);
            }
        }

        cell.InitConfigFirst();
        // Création d'une instance de CellSimulator
        SchellingSimulator simulator = new SchellingSimulator(cell, gui);

        // Ajout du simulateur à la fenêtre graphique
        gui.setSimulable(simulator);
    }
}
