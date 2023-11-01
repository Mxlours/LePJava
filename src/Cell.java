import java.awt.Point;
public class Cell {
    private Point[] cells;

    private boolean[] isAlive;

    private boolean[] alive_before;

    private boolean[] first_config;
    private int size_x;
    private int size_y;

    public Cell(int size_x, int size_y) {

        this.size_x = size_x;
        this.size_y = size_y;
        this.cells = new Point[size_x*size_y];
        this.isAlive = new boolean[size_x*size_y];
        this.alive_before = new boolean[size_x*size_y];
        if (cells.length != isAlive.length) {
            throw new IllegalArgumentException("les longueurs des deux tableaux doivent être identiques ! ");
        }
        for (int i = 0; i <size_y; i++){
            for (int j = 0; j < size_x; j++){
                this.cells[size_y*i + j] = new Point(i, j);
                this.isAlive[size_y*i + j] = false;
                this.alive_before[size_y*i + j]= false;
            }
        }
    }

    public int getSize_y() {
        return size_y;
    }

    public int getSize_x() {
        return size_x;
    }

    public void InitConfigFirst(){
        this.first_config = new boolean[size_x*size_y];
        for (int i = 0; i <size_y; i++){
            for (int j = 0; j < size_x; j++){
                this.first_config[size_y*i + j]= this.isAlive[size_y*i + j];
            }
        }
    }
    public void Init_cells(){
        this.isAlive = new boolean[size_x*size_y];
        this.alive_before = new boolean[size_x*size_y];
        for (int i = 0; i <size_y; i++){
            for (int j = 0; j < size_x; j++){
                this.isAlive[size_y*i + j]= this.first_config[size_y*i + j];
                this.alive_before[size_y*i + j]= this.first_config[size_y*i + j];


            }
        }
    }

    public Point getCellule(int i) {
        return cells[i];
    }

    public int getlength(){
        return size_y*size_x;
    }
    public boolean[] getIsAlive() {
        return isAlive;
    }

    public boolean[] getAlive_before(){
        return alive_before;
    }

    public void setBoolean_coord(boolean bool, int coord_x, int coord_y){

        isAlive[size_y*coord_y + coord_x] = bool;
        alive_before[size_y*coord_y + coord_x] = bool;
    }
    public void setBoolean(boolean bool, int i){
        isAlive[i] = bool;
    }


    // au dessus je définie les fonctions basiques que devront utiliser l'uilisateur pour un cell

    // maintenant définissons une méthode qui va calculer le nombre de voisins
    public void setNewetape(){
        for (int i = 0; i < getlength(); i++){
            if ( !(alive_before[i]) && (countAliveNeighbors(getCellule(i))==3)){
                setBoolean(true, i);
            } else if ( !((alive_before[i]) && ((countAliveNeighbors( getCellule(i)))==3 || (countAliveNeighbors( getCellule(i)))==2))){
                setBoolean(false, i);
            }
        }
        alive_before = new boolean[getlength()];
        for (int i = 0; i < cells.length; i++) {
            this.alive_before[i] = isAlive[i];
        }
    }
    // TOUJOURS SE BASER SUR cells.alive_before, car commme on va modifier en temps réel notre tableau de booléens, on peut pas se baser dessus
    private int countAliveNeighbors(Point cellule) {
        int aliveNeighbors = 0;

        for (int i = 0; i < getlength(); i++) {
            if (isNeighbor(cellule, cells[i]) && alive_before[i]) {
                aliveNeighbors++;
            }
        }

        return aliveNeighbors;
    }

    private boolean isNeighbor(Point cellule1, Point cellule2) {
        int dx = Math.abs((cellule1.x - cellule2.x)%(size_x - 1));
        int dy = Math.abs((cellule1.y - cellule2.y)%(size_y - 1));

        return (dx == 1 && dy <= 1) || (dy == 1 && dx <= 1);
    }


    @Override
    public String toString() {
        String rt = "";
        for (int i = 0; i < cells.length; i++) {
            rt += "( " + cells[i].x + " , " + cells[i].y + " , " + isAlive[i] + " )\n";
        }
        return rt;
    }


}