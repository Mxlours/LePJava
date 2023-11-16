
import java.awt.*;

import gui.*;
import gui.Rectangle;
/**
 * This class implements the Simulable interface and simulates the behavior of a group of cells using Conway's Game of Life rules.
 * The class takes in a Cell object and a GUISimulator object as parameters and uses them to display the state of the cells.
 */
public class CellSimulator implements Simulable {
    public static final int TAILLE_CELLULE = 20;
    public static final int MARGES = TAILLE_CELLULE / 2; //Au mieux, si nombre pas pair faire marge visible

    private Cell cells;
    private GUISimulator gui;

    /**
     * Constructs a CellSimulator object with the given Cell and GUISimulator objects.
     * @param cells the Cell object representing the group of cells
     * @param gui the GUISimulator object used to display the state of the cells
     */
    public CellSimulator(Cell cells, GUISimulator gui) {
        this.cells = cells;
        this.gui = gui;
        setGraphicCell();
    }

    /**
     * Returns the Cell object used in the simulation.
     * @return the Cell object used in the simulation
     */
    public Cell getCells() {
        return cells;
    }

    /**
     * Advances the simulation by one step, updating the state of the cells and redrawing them on the GUI.
     */
    @Override
    public void next() {
        gui.reset();
        cells.setnewEtapeConway();

        setGraphicCell();
    }

    /**
     * Restarts the simulation by resetting the state of the cells and redrawing them on the GUI.
     */
    @Override
    public void restart() {
        gui.reset();
        cells.Init_cells();
        setGraphicCell();
    }

    /**
     * Draws the cells on the GUI using rectangles, with live cells represented by blue rectangles and dead cells represented by white rectangles.
     */
    public void setGraphicCell() {
        for (int i = 0; i < cells.getSize_y(); i++) {
            for (int j = 0; j < cells.getSize_x(); j++) {
                if (cells.getIsAlive()[cells.getSize_x() * i + j] == 1) {
                    gui.addGraphicalElement(new Rectangle(MARGES + j * TAILLE_CELLULE, MARGES + i * TAILLE_CELLULE, Color.decode("#1f77b4"), Color.decode("#1f77b4"), TAILLE_CELLULE));
                } else {
                    gui.addGraphicalElement(new Rectangle(MARGES+ j * TAILLE_CELLULE, MARGES + i * TAILLE_CELLULE, Color.decode("#ffffff"), Color.decode("#ffffff"), TAILLE_CELLULE));
                }
            }
        }
    }

}
