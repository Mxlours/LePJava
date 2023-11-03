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
        if(etat_cellule == 999){
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
    }

    public void SetNewDestination(Point Cellule){
        for(int i = 0; i < getlength(); i++){
            if(this.dictPointToLibre.get(getCellule(i))){
                // si c'est true donc libre on mets la cellule ici
                this.dictPointToLibre.put(getCellule(i), false);
                // faut mettre dans alive le fait qu'elle à changer de place
                int etat_cellule = alive_before[Cellule.y*getSize_x() + Cellule.x];
                setBoolean(etat_cellule, i);
                // mets la valeur 999 pour indiqué que y'a personne ici
                setBoolean(999, Cellule.y*getSize_x() + Cellule.x);
            }
        }
    }
}
