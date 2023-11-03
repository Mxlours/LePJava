import java.awt.*;

public class Immigration extends Cell {
    private int nb_etats;
    // On condisère que nos états vont de 0 à etats-1 !!!!!!!!
    public Immigration(int size_x, int size_y, int nb_etats){
        super(size_x, size_y);
        this.nb_etats = nb_etats;
    }

    public int getNb_etats() {
        return nb_etats;
    }


    private int countAliveNeighborsImmigration(Point cellule, int etat_cellule) {
        int voisinsupp = 0;

        for (int i = 0; i < getlength(); i++) {
            if (super.isNeighbor(cellule, super.cells[i]) && (super.alive_before[i]== (etat_cellule + 1)%nb_etats)) {
                voisinsupp++;
            }
        }

        return voisinsupp;
    }
    public void setnewEtapeImmigration(){
        for (int i = 0; i < getlength(); i++){
            int etat_cellule = alive_before[i];
            if ( countAliveNeighborsImmigration(getCellule(i), etat_cellule)>=3)
                setBoolean((etat_cellule+1)%nb_etats, i);
        }
        alive_before = new int[getlength()];
        for (int i = 0; i < cells.length; i++) {
            this.alive_before[i] = isAlive[i];
        }
    }

    public void setBoolean_coord_Immi(int bool, int coord_x, int coord_y){
        if (bool >= nb_etats) {
            throw new IllegalArgumentException("l'état voulu n'existe pas (n-1 max)");
        }
        isAlive[size_y*coord_y + coord_x] = bool;
        alive_before[size_y*coord_y + coord_x] = bool;
    }
}