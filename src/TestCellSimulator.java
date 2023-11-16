

import gui.GUISimulator;
import gui.Rectangle;

import java.awt.Color;

// Récupérer la taille de l'écran
import java.awt.Dimension;
import java.awt.Toolkit;
// Nombre aléatoire
import java.util.Random;

/**

 This code initializes a graphical user interface (GUI) simulator and creates a cell object. The cell object is then modified by setting boolean coordinates. The initial configuration of the cell is set using the InitConfigFirst() method. Finally, a CellSimulator object is created and added to the GUI window.

 */
public class TestCellSimulator {
    public static final double DIVISON_ECHELLE = 1.8;
    public static final double CHANCE = 40; // Pourcentage chance d'initialisation d'une cellule)

    public static void main(String[] args) {
        // Taille de l'écran divisée par DIVISON_ECHELLE
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int largeur = (int) (screenSize.getWidth() / DIVISON_ECHELLE / CellSimulator.TAILLE_CELLULE);
        int hauteur = (int) (screenSize.getHeight() / DIVISON_ECHELLE / CellSimulator.TAILLE_CELLULE);

        // Création d'une instance de GUISimulator
        GUISimulator gui = new GUISimulator(largeur * CellSimulator.TAILLE_CELLULE, hauteur * CellSimulator.TAILLE_CELLULE, Color.GREEN);

        // Initialisation random ds cellules
        Random random = new Random();
        Cell cell = new Cell(largeur, hauteur);
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                if (random.nextInt(100)< CHANCE) {
                    cell.setBoolean_coord(1, i, j);
                }
            }
        }

        /* // Configuration manuelle
        Cell cell = new Cell(50, 50);
        cell.setBoolean_coord(1, 30, 30);
        cell.setBoolean_coord(1, 32, 31);
        cell.setBoolean_coord(1, 30, 32);
        cell.setBoolean_coord(1, 29, 32);
        cell.setBoolean_coord(1, 33, 32);
        cell.setBoolean_coord(1, 34, 32);
        cell.setBoolean_coord(1, 35, 32);
        */

        cell.InitConfigFirst();
        // Création d'une instance de CellSimulator
        CellSimulator simulator = new CellSimulator(cell, gui);

        // Ajout du simulateur à la fenêtre graphique
        gui.setSimulable(simulator);
    }
}
