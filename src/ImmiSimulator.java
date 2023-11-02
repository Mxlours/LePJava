import gui.*;
import gui.Rectangle;

import java.awt.*;

public class ImmiSimulator implements Simulable{

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
                    int couleur_rect = pas_couleur * cells.getIsAlive()[cells.getSize_y() * i + j];
                    String color = "#" + Integer.toHexString(255 - couleur_rect) + Integer.toHexString(255 -couleur_rect) + Integer.toHexString(255 - couleur_rect);
                    gui.addGraphicalElement(new Rectangle(200 + j * 50, 200 + i * 50, Color.decode(color), Color.decode(color), 50));
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
