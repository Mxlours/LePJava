import java.awt.*;
import java.util.HashMap;
public class Schelling extends Cell{

    private int nb_etats;
    private int ndrDeVoisinDiffPourChanger;
    private HashMap<Point, Boolean> dictPointToLibre;
    // si pour un point donné on renvoie true, ça veut dire que personne n'habites ici
    public Schelling(int size_x, int size_y, int nb_etats, int ndrDeVoisinDiffPourChanger){
        super(size_x, size_y);
        this.dictPointToLibre = new HashMap<>();
        this.nb_etats = nb_etats;
        this.ndrDeVoisinDiffPourChanger = ndrDeVoisinDiffPourChanger;
        initDict(dictPointToLibre);
    }

    public HashMap<Point, Boolean> getDict() { return dictPointToLibre; }
    public int getNb_etats() {
        return nb_etats;
    }

    public void initDict(HashMap<Point, Boolean> dictPointToLibre){
        int sizeX = getSize_x();
        int sizeY = getSize_y();
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                Point point = new Point(x, y);
                dictPointToLibre.put(point, true);
            }
        }
    }

    private int countOtherNeighborsSchelling(Point cellule, int etat_cellule) {
        if(etat_cellule == 0){
            // si c'est inhabité on fais rien
            return 0;
        }
        int voisinsupp = 0;
        for (int i = 0; i < getlength(); i++) {
            if (super.isNeighbor(cellule, super.cells[i]) && (super.alive_before[i] != etat_cellule)){
                voisinsupp++;
            }
        }
        return voisinsupp;
    }

    public void setnewEtapeSchelling(){
        for (int i = 0; i < getlength(); i++){
            int etat_cellule = alive_before[i];
            if ( countOtherNeighborsSchelling(getCellule(i), etat_cellule)>this.ndrDeVoisinDiffPourChanger) {
                setFree(getCellule(i));
                SetNewDestination(getCellule(i));
            }
        }
        alive_before = new int[getlength()];
        for (int i = 0; i < cells.length; i++) {
            this.alive_before[i] = isAlive[i];
        }
    }

    public void setFree(Point Cellule){
        // on libère la place
        this.dictPointToLibre.put(Cellule, true);
        // mets la valeur 999 pour indiqué que y'a personne ici
        setBoolean(0, Cellule.y*getSize_x() + Cellule.x);
    }

    public void SetNewDestination(Point Cellule){
        for(int i = 0; i < getlength(); i++){
            if(this.dictPointToLibre.get(getCellule(i))){
                // si c'est true donc libre on mets la cellule ici
                this.dictPointToLibre.put(getCellule(i), false);
                // faut mettre dans alive le fait qu'elle à changer de place
                int etat_cellule = alive_before[Cellule.y*getSize_x() + Cellule.x];
                setBoolean(etat_cellule, i);
            }
        }
    }

    @Override
    public void Init_cells(){
        this.isAlive = new int[size_x*size_y];
        this.alive_before = new int[size_x*size_y];
        for (int i = 0; i <size_y; i++){
            for (int j = 0; j < size_x; j++){
                this.isAlive[size_x*i + j]= this.first_config[size_x*i + j];
                this.alive_before[size_x*i + j]= this.first_config[size_x*i + j];
                Point current_point = new Point(j, i);
                this.dictPointToLibre.put(current_point, false);
            }
        }
    }

    public void setBoolean_coord_Sche(int bool, int coord_x, int coord_y){
        if (bool >= nb_etats) {
            throw new IllegalArgumentException("l'état voulu n'existe pas (n-1 max)");
        }
        isAlive[size_x*coord_y + coord_x] = bool;
        alive_before[size_x*coord_y + coord_x] = bool;
        Point current_point = new Point(coord_x, coord_y);
        this.dictPointToLibre.put(current_point, false);
    }

}
