import java.awt.Point;

public class Cell {
    protected Point[] cells;

    protected int[] isAlive;

    protected int[] alive_before;

    protected int[] first_config;
    protected int size_x;
    protected int size_y;

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

    public int getSize_y() {
        return size_y;
    }

    public int getSize_x() {
        return size_x;
    }

    public void InitConfigFirst() {
        this.first_config = new int[size_x * size_y];
        for (int i = 0; i < size_y; i++) {
            for (int j = 0; j < size_x; j++) {
                this.first_config[size_x * i + j] = this.isAlive[size_x * i + j];
            }
        }
    }

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

    public Point getCellule(int i) {
        return cells[i];
    }

    public int getlength() {
        return size_y * size_x;
    }

    public int[] getIsAlive() {
        return isAlive;
    }

    public int[] getAlive_before() {
        return alive_before;
    }

    public void setBoolean_coord(int bool, int coord_x, int coord_y) {
        isAlive[size_x * coord_y + coord_x] = bool;
        alive_before[size_x * coord_y + coord_x] = bool;
    }

    public void setBoolean(int bool, int i) {
        isAlive[i] = bool;
    }


    // au dessus je définie les fonctions basiques que devront utiliser l'uilisateur pour un cell

    // maintenant définissons une méthode qui va calculer le nombre de voisins
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
    private int countAliveNeighbors(Point cellule) {
        int aliveNeighbors = 0;

        for (int i = 0; i < getlength(); i++) {
            if (isNeighbor(cellule, cells[i]) && (alive_before[i] == 1)) {
                aliveNeighbors++;
            }
        }

        return aliveNeighbors;
    }

    protected boolean isNeighbor(Point cellule1, Point cellule2) {
        int dx = Math.abs((cellule1.x - cellule2.x));
        int dy = Math.abs((cellule1.y - cellule2.y));
        boolean xNeighbor = (dx == 1);
        boolean yNeighbor = (dy == 1);
        return (xNeighbor && dy <= 1) || (yNeighbor && dx <= 1);
    }


    @Override
    public String toString() {
        String rt = "";
        for (int i = 0; i < cells.length; i++) {
            rt += "( " + cells[i].x + " , " + cells[i].y + " , " + isAlive[i] + " )\n";
        }
        return rt;
    }

    // On va devoir implémenter une fonction pour le calcul de notre cell, donc ce que je vais faire c'est extend ma classe cell, avec un calcul pour conway, et un calcul pour IMMIGRATION !

}
