/**
 * This code defines a class called "Immigration" that represents a simulation of a cellular automaton with multiple states.
 * It extends the "Cell" class and provides methods to set the number of states, count alive neighbors, update the state of the cells, and set the state of a specific cell.
 */
import java.awt.*;

public class Immigration extends Cell {
    private int nb_etats;

    /**
     * Constructs an Immigration object with the specified size of the grid and number of states.
     *
     * @param size_x    the number of cells in the x-direction
     * @param size_y    the number of cells in the y-direction
     * @param nb_etats  the number of states in the simulation
     */
    public Immigration(int size_x, int size_y, int nb_etats) {
        super(size_x, size_y);
        this.nb_etats = nb_etats;
    }

    /**
     * Returns the number of states in the simulation.
     *
     * @return the number of states
     */
    public int getNb_etats() {
        return nb_etats;
    }

    /**
     * Counts the number of alive neighbors of a cell with a specific state.
     *
     * @param cellule       the coordinates of the cell
     * @param etat_cellule  the state of the cell
     * @return the number of alive neighbors
     */
    private int countAliveNeighborsImmigration(Point cellule, int etat_cellule) {
        int voisinsupp = 0;
        for (int i = 0; i < getlength(); i++) {
            if (super.isNeighbor(cellule, super.cells[i]) && (super.alive_before[i] == (etat_cellule + 1) % nb_etats)) {
                voisinsupp++;
            }
        }
        return voisinsupp;
    }

    /**
     * Updates the state of the cells in the simulation based on the Immigration rules.
     */
    public void setnewEtapeImmigration() {
        for (int i = 0; i < getlength(); i++) {
            int etat_cellule = alive_before[i];
            if (countAliveNeighborsImmigration(getCellule(i), etat_cellule) >= 3) {
                setBoolean((etat_cellule + 1) % nb_etats, i);
            }
        }
        alive_before = new int[getlength()];
        for (int i = 0; i < cells.length; i++) {
            this.alive_before[i] = isAlive[i];
        }
    }

    /**
     * Sets the state of a specific cell at the given coordinates in the Immigration simulation.
     *
     * @param bool      the state to set for the cell
     * @param coord_x   the x-coordinate of the cell
     * @param coord_y   the y-coordinate of the cell
     * @throws IllegalArgumentException if the specified state is greater than or equal to the number of states
     */
    public void setBoolean_coord_Immi(int bool, int coord_x, int coord_y) {
        if (bool >= nb_etats) {
            throw new IllegalArgumentException("l'état voulu n'existe pas (n-1 max)");
        }
        isAlive[size_x * coord_y + coord_x] = bool;
        alive_before[size_x * coord_y + coord_x] = bool;
    }
}