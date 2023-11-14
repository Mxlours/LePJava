import gui.*;
import gui.Rectangle;

import java.awt.*;
import java.util.HashMap;

public class SchellingSimulator implements Simulable {

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
                gui.addGraphicalElement(new Rectangle(200 + j * 50, 200 + i * 50, Color.decode(color), Color.decode(color), 50));
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
