import gui.*;
import gui.Rectangle;

import java.awt.*;

public class ImmiSimulator implements Simulable{

    // Déclaration d'un la taille des cellules
    public static final int TAILLE_CELLULE = 30;
    // Marges (demie cellule)
    public static final int MARGES = TAILLE_CELLULE/2; //Au mieux, si nombre pas pair faire marge visible

    private Immigration cells;
    private GUISimulator gui;
    public ImmiSimulator(Immigration cells, GUISimulator gui) {
        this.cells = cells;
        this.gui = gui;
        setGraphicCell();
    }
    public void setGraphicCell() {
        int pas_couleur = 255/ cells.getNb_etats();
        for (int i = 0; i <cells.getSize_y(); i++){
            for (int j = 0; j < cells.getSize_x(); j++){
                    int couleur_rect = pas_couleur * cells.getIsAlive()[cells.getSize_x() * i + j];
                    String color = "#" + Integer.toHexString(255 - couleur_rect) + Integer.toHexString(255 -couleur_rect) + Integer.toHexString(255 - couleur_rect);
                    gui.addGraphicalElement(new Rectangle(MARGES + j * TAILLE_CELLULE, MARGES + i * TAILLE_CELLULE, Color.decode(color), Color.decode(color), TAILLE_CELLULE));
            }
        }
    }

    @Override
    public void next() {
        gui.reset();
        cells.setnewEtapeImmigration();

        setGraphicCell();
    }

    @Override
    public void restart() {
        gui.reset();
        cells.Init_cells();
        setGraphicCell();

    }
}
