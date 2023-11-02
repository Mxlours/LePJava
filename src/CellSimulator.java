import java.awt.*;

import gui.*;
import gui.Rectangle;

public class CellSimulator implements Simulable
{
    private Cell cells;
    private GUISimulator gui;

    public CellSimulator(Cell cells, GUISimulator gui) {
        this.cells = cells;
        this.gui = gui;
        setGraphicCell();
    }
    public Cell getCells(){
        return cells;
    }

    @Override
    public void next() {
        gui.reset();
        cells.setnewEtapeConway();

        setGraphicCell();
    }

    public void setGraphicCell() {
        for (int i = 0; i <cells.getSize_y(); i++){
            for (int j = 0; j < cells.getSize_x(); j++){
                if (cells.getIsAlive()[cells.getSize_y() * i + j] == 1) {
                    gui.addGraphicalElement(new Rectangle(j * 20, i * 20, Color.decode("#1f77b4"), Color.decode("#1f77b4"), 20));
                }
                else{
                    gui.addGraphicalElement(new Rectangle(j * 20, i * 20, Color.decode("#ffffff"), Color.decode("#ffffff"), 20));
                }
            }
        }
    }

    @Override
    public void restart() {
        gui.reset();
        cells.Init_cells();
        setGraphicCell();

    }
}
