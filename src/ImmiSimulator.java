
import gui.*;
import gui.Rectangle;

import java.awt.*;
/**
 * This is a Java program that simulates an immigration cellular automaton.
 * The program uses a GUI to display the cells and their states.
 * It includes methods to set the graphic representation of the cells,
 * advance to the next step of the simulation, and restart the simulation.
 */
public class ImmiSimulator implements Simulable {

    public static final int TAILLE_CELLULE = 30;
    public static final int MARGES = TAILLE_CELLULE / 2; //Au mieux, si nombre pas pair faire marge visible

    private Immigration cells;
    private GUISimulator gui;

    /**
     * Constructs a new ImmiSimulator object with the given Immigration cells and GUISimulator gui.
     * Initializes the graphic representation of the cells.
     * @param cells the Immigration object representing the cells
     * @param gui the GUISimulator object for displaying the cells
     */
    public ImmiSimulator(Immigration cells, GUISimulator gui) {
        this.cells = cells;
        this.gui = gui;
        setGraphicCell();
    }

    /**
     * Sets the graphic representation of the cells.
     * Iterates through the cells and adds rectangles to the GUI based on their states.
     */
    public void setGraphicCell() {
        int pas_couleur = 255 / cells.getNb_etats();
        for (int i = 0; i < cells.getSize_y(); i++) {
            for (int j = 0; j < cells.getSize_x(); j++) {
                int couleur_rect = pas_couleur * cells.getIsAlive()[cells.getSize_x() * i + j];
                String color = "#" + Integer.toHexString(255 - couleur_rect) + Integer.toHexString(255 - couleur_rect) + Integer.toHexString(255 - couleur_rect);
                gui.addGraphicalElement(new Rectangle(MARGES + j * TAILLE_CELLULE, MARGES + i * TAILLE_CELLULE, Color.decode(color), Color.decode(color), TAILLE_CELLULE));
            }
        }
    }

    /**
     * Advances the simulation to the next step.
     * Resets the GUI, updates the cells' states, and sets the new graphic representation.
     */
    @Override
    public void next() {
        gui.reset();
        cells.setnewEtapeImmigration();

        setGraphicCell();
    }

    /**
     * Restarts the simulation.
     * Resets the GUI, initializes the cells, and sets the new graphic representation.
     */
    @Override
    public void restart() {
        gui.reset();
        cells.Init_cells();
        setGraphicCell();

    }
}
