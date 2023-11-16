
import java.awt.Point;
/**
 * This is a Java program that defines a Cell class. The Cell class represents a cell in a grid. It contains methods for initializing the grid, setting the state of cells, calculating the number of neighbors for each cell, and updating the state of cells based on the rules of Conway's Game of Life.
 */
public class Cell {
    protected Point[] cells;

    protected int[] isAlive;

    protected int[] alive_before;

    protected int[] first_config;
    protected int size_x;
    protected int size_y;

    /**
     * Constructs a Cell object with the given size of the grid.
     * Initializes the cells array, isAlive array, and alive_before array.
     * Throws an IllegalArgumentException if the lengths of the arrays are not the same.
     * Each cell is assigned a Point object and initialized with default state values.
     *
     * @param size_x the number of cells in the x-direction
     * @param size_y the number of cells in the y-direction
     */
    public Cell(int size_x, int size_y) {

        this.size_x = size_x;
        this.size_y = size_y;
        this.cells = new Point[size_x * size_y];
        this.isAlive = new int[size_x * size_y];
        this.alive_before = new int[size_x * size_y];
        if (cells.length != isAlive.length) {
            throw new IllegalArgumentException("les longueurs des deux tableaux doivent être identiques ! ");
        }
        for (int i = 0; i < size_y; i++) {
            for (int j = 0; j < size_x; j++) {
                this.cells[size_x * i + j] = new Point(j, i);
                this.isAlive[size_x * i + j] = 0;
                this.alive_before[size_x * i + j] = 0;
            }
        }
    }
    /**
     * @return size_y
     */
    public int getSize_y() {
        return size_y;
    }
    /**
     * @return size_x
     */
    public int getSize_x() {
        return size_x;
    }
    /**
     * Initialize our first config to make a copy which will be useful while reseting
     */
    public void InitConfigFirst() {
        this.first_config = new int[size_x * size_y];
        for (int i = 0; i < size_y; i++) {
            for (int j = 0; j < size_x; j++) {
                this.first_config[size_x * i + j] = this.isAlive[size_x * i + j];
            }
        }
    }
    /**
     * Initialize isAlive and alive_before
     */
    public void Init_cells() {
        this.isAlive = new int[size_x * size_y];
        this.alive_before = new int[size_x * size_y];
        for (int i = 0; i < size_y; i++) {
            for (int j = 0; j < size_x; j++) {
                this.isAlive[size_x * i + j] = this.first_config[size_x * i + j];
                this.alive_before[size_x * i + j] = this.first_config[size_x * i + j];
            }
        }
    }

    /**
     * Returns the Point object at the specified index of the cells array.
     *
     * @param i The index of the Point object to be returned.
     * @return The Point object at the specified index.
     */
    public Point getCellule(int i) {
        return cells[i];
    }

    /**
     * Returns the total number of cells.
     *
     * @return The total number of cells.
     */
    public int getlength() {
        return size_y * size_x;
    }

    /**
     * Returns the array containing the status of each cell (alive or dead).
     *
     * @return The array containing the status of each cell.
     */
    public int[] getIsAlive() {
        return isAlive;
    }

   /**
    * This method returns the array of alive cells before the current state.
    */
    public int[] getAlive_before() {
        return alive_before;
    }

    /**
     * Sets the boolean value at the specified coordinates in the Cell.
     *
     * @param bool     the boolean value to set
     * @param coord_x  the x-coordinate of the cell
     * @param coord_y  the y-coordinate of the cell
     */
    public void setBoolean_coord(int bool, int coord_x, int coord_y) {
        isAlive[size_x * coord_y + coord_x] = bool;
        alive_before[size_x * coord_y + coord_x] = bool;
    }

    /**
     * Sets the boolean value at the specified index.
     *
     * @param bool the boolean value to set
     * @param i the index to set the value at
     */
    public void setBoolean(int bool, int i) {
        isAlive[i] = bool;
    }


    // au dessus je définie les fonctions basiques que devront utiliser l'uilisateur pour un cell

    // maintenant définissons une méthode qui va calculer le nombre de voisins
    /**
     * This code updates the state of each cell in a Conway's Game of Life simulation, based on the rules of the game.
     */
    public void setnewEtapeConway() {
        for (int i = 0; i < getlength(); i++) {
            if ((alive_before[i] == 0) && (countAliveNeighbors(getCellule(i)) == 3)) {
                setBoolean(1, i);
            } else if (!((alive_before[i] == 1) && ((countAliveNeighbors(getCellule(i))) == 3 || (countAliveNeighbors(getCellule(i))) == 2))) {
                setBoolean(0, i);
            }
        }
        alive_before = new int[getlength()];
        for (int i = 0; i < cells.length; i++) {
            this.alive_before[i] = isAlive[i];
        }
    }

    // TOUJOURS SE BASER SUR cells.alive_before, car commme on va modifier en temps réel notre tableau de booléens, on peut pas se baser dessus
    /**
     * This method counts the number of alive neighbors for a given cell.
     *
     * @param cellule The cell for which the alive neighbors need to be counted.
     * @return The total number of alive neighbors.
     */
    private int countAliveNeighbors(Point cellule) {
        int aliveNeighbors = 0;
        for (int i = 0; i < getlength(); i++) {
            if (isNeighbor(cellule, cells[i]) && (alive_before[i] == 1)) {
                aliveNeighbors++;
            }
        }
        return aliveNeighbors;
    }

    /**
     * This code defines a method called "isNeighbor" that checks if two given points are neighbors.
     * It calculates the absolute difference in x and y coordinates between the two points and checks
     * if either the x or y difference is equal to 1. If so, it also checks if the other difference is
     * less than or equal to 1. The method returns true if the points are neighbors, and false otherwise.
     * @param cellule1 first cell
     * @param cellule2 second cell
     * @return a boolean which indicate if cellule1/2 are neighbors.
     */
protected boolean isNeighbor(Point cellule1, Point cellule2) {
    int dx = Math.abs((cellule1.x - cellule2.x));
    int dy = Math.abs((cellule1.y - cellule2.y));
    boolean xNeighbor = (dx == 1);
    boolean yNeighbor = (dy == 1);
    return (xNeighbor && dy <= 1) || (yNeighbor && dx <= 1);
}


    @Override
    /**
     * Cell.java
     *
     * This code represents a Java class that defines a toString() method to generate a string representation of an array of cells.
     * The method iterates through the cells array and constructs a string containing the x and y coordinates along with the alive status of each cell.
     * The final string is returned as the result.
     */
    public String toString() {
        String rt = "";
        for (int i = 0; i < cells.length; i++) {
            rt += "( " + cells[i].x + " , " + cells[i].y + " , " + isAlive[i] + " )\n";
        }
        return rt;
    }

    // On va devoir implémenter une fonction pour le calcul de notre cell, donc ce que je vais faire c'est extend ma classe cell, avec un calcul pour conway, et un calcul pour IMMIGRATION !

}
