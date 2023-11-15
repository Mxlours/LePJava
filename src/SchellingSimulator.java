import gui.*;
import gui.Rectangle;

import java.awt.*;
import java.util.HashMap;

public class SchellingSimulator implements Simulable {

    // Déclaration d'un la taille des cellules
    public static final int TAILLE_CELLULE = 50;
    // Marges (demie cellule)
    public static final int MARGES = TAILLE_CELLULE / 2; //Au mieux, si nombre pas pair faire marge visible


    private Schelling cells;
    private GUISimulator gui;

    public SchellingSimulator(Schelling cells, GUISimulator gui) {
        this.cells = cells;
        this.gui = gui;
        setGraphicCell();
    }

    public void setGraphicCell() {

        int pas_couleur = 255 / cells.getNb_etats();
        int sizeX = cells.getSize_x();
        int sizeY = cells.getSize_y();
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                String color;
                if (cells.getIsAlive()[sizeX * i + j] == 0) {
                    // couleur de zone non habité
                    color = "#00FF00";
                } else {
                    int couleur_rect = pas_couleur * (cells.getIsAlive()[cells.getSize_x() * i + j] - 1);
                    color = "#" + Integer.toHexString(255 - couleur_rect) + Integer.toHexString(255 - couleur_rect) + Integer.toHexString(255 - couleur_rect);
                }
                gui.addGraphicalElement(new Rectangle(MARGES + j * TAILLE_CELLULE, MARGES + i * TAILLE_CELLULE, Color.decode(color), Color.decode(color), TAILLE_CELLULE));
            }
        }
    }

    @Override
    public void next() {
        gui.reset();
        cells.setnewEtapeSchelling();

        setGraphicCell();
    }

    @Override
    public void restart() {
        gui.reset();
        // remettre hashmap à 0
        cells.initDict(cells.getDict());
        // remettre les valeurs
        cells.Init_cells();
        setGraphicCell();
    }
}
