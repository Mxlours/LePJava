/*
 * Schelling.java
 * This class extends the Cell class and implements the Schelling model of segregation.
 * It initializes a dictionary mapping each point to a boolean value indicating whether it is free or not.
 * It also provides methods to count the number of neighboring cells with a different state, set a new destination for a cell, and update the states of the cells.
 */
import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class Schelling extends Cell {

    private int nb_etats;
    private int ndrDeVoisinDiffPourChanger;
    private HashMap<Point, Boolean> dictPointToLibre;

    /**
     * Initializes a Schelling object with the given parameters.
     *
     * @param size_x                    the size of the grid in the x direction
     * @param size_y                    the size of the grid in the y direction
     * @param nb_etats                  the number of states
     * @param ndrDeVoisinDiffPourChanger the number of different neighbors required to trigger a change
     */
    public Schelling(int size_x, int size_y, int nb_etats, int ndrDeVoisinDiffPourChanger) {
        super(size_x, size_y);
        this.dictPointToLibre = new HashMap<>();
        this.nb_etats = nb_etats;
        this.ndrDeVoisinDiffPourChanger = ndrDeVoisinDiffPourChanger;
        initDict(dictPointToLibre);
    }

    /**
     * Returns the dictionary of points.
     *
     * @return the dictionary of points
     */
    public HashMap<Point, Boolean> getDict() {
        return dictPointToLibre;
    }
    public int getNb_etats() {
        return nb_etats;
    }
    /**
     * @brief Initializes the dictionary with all points set to free.
     *
     * @param dictPointToLibre The dictionary to initialize.
     */
    public void initDict(HashMap<Point, Boolean> dictPointToLibre) {
        int sizeX = getSize_x();
        int sizeY = getSize_y();
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                Point point = new Point(x, y);
                dictPointToLibre.put(point, true);
            }
        }
    }
    /**
     * @brief Counts the number of neighboring cells with a different state in the Schelling model.
     *
     * @param cellule The current cell.
     * @param etat_cellule The state of the current cell.
     * @return The number of neighboring cells with a different state.
     */
    private int countOtherNeighborsSchelling(Point cellule, int etat_cellule) {
        if (etat_cellule == 0) {
            return 0;
        }
        int voisinsupp = 0;
        for (int i = 0; i < getlength(); i++) {
            if (super.isNeighbor(cellule, super.cells[i]) && (super.alive_before[i] != etat_cellule) && (super.alive_before[i] != 0)) {
                voisinsupp++;
            }
        }
        return voisinsupp;
    }
    /**
     * @brief Updates the states of the cells based on the Schelling model.
     */
    public void setnewEtapeSchelling() {
        for (int i = 0; i < getlength(); i++) {
            int etat_cellule = alive_before[i];
            if (countOtherNeighborsSchelling(getCellule(i), etat_cellule) > this.ndrDeVoisinDiffPourChanger) {
                SetNewDestination(getCellule(i));
                setFree(getCellule(i));
            }
        }
        alive_before = new int[getlength()];
        for (int i = 0; i < cells.length; i++) {
            this.alive_before[i] = isAlive[i];
        }
    }
    /**
     * @brief Marks a cell as free in the Schelling model.
     *
     * @param Cellule The cell to mark as free.
     */
    public void setFree(Point Cellule) {
        this.dictPointToLibre.put(Cellule, true);
        setBoolean(0, Cellule.y * getSize_x() + Cellule.x);
    }
    /**
     * @brief Sets a new destination for a cell in the Schelling model.
     *
     * @param Cellule The current cell.
     */
    public void SetNewDestination(Point Cellule) {
        Random random = new Random();
        int valeur_random = random.nextInt(50);
        int compteur = 0;
        for (int i = 0; i < getlength(); i++) {
            if (this.dictPointToLibre.get(getCellule(i))) {
                compteur++;
                if(compteur == valeur_random){
                    this.dictPointToLibre.put(getCellule(i), false);
                    int etat_cellule = alive_before[Cellule.y * getSize_x() + Cellule.x];
                    setBoolean(etat_cellule, i);
                    break;
                }
            }
        }
    }
    /**
     * @brief Initializes the cells and dictionary based on the first configuration in the Schelling model.
     */
    @Override
    public void Init_cells() {
        this.isAlive = new int[size_x * size_y];
        this.alive_before = new int[size_x * size_y];
        for (int i = 0; i < size_y; i++) {
            for (int j = 0; j < size_x; j++) {
                int first_conf = this.first_config[size_x * i + j];
                this.isAlive[size_x * i + j] = first_conf;
                this.alive_before[size_x * i + j] = first_conf;
                if (first_conf != 0) {
                    Point current_point = new Point(j, i);
                    this.dictPointToLibre.put(current_point, false);
                }
            }
        }
    }
    /**
     * @brief Sets the state of a cell at a given coordinate in the Schelling model.
     *
     * @param bool The boolean value to set.
     * @param coord_x The x-coordinate of the cell.
     * @param coord_y The y-coordinate of the cell.
     */
    public void setBoolean_coord_Sche(int bool, int coord_x, int coord_y) {
        if (bool >= nb_etats + 1) {
            throw new IllegalArgumentException("l'état voulu n'existe pas (n-1 max)");
        }
        isAlive[size_x * coord_y + coord_x] = bool;
        alive_before[size_x * coord_y + coord_x] = bool;
        Point current_point = new Point(coord_x, coord_y);
        this.dictPointToLibre.put(current_point, false);
    }

}
