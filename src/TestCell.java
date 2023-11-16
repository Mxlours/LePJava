/**
 * This code demonstrates the implementation of the Game of Conway using a Cell object.
 * The code initializes a Cell object and performs various operations on it, such as setting boolean coordinates, initializing the configuration, and generating new steps in the Conway game.
 * It also prints the final configuration of the Cell object after each operation.
 */
import java.awt.*;

public class TestCell {
    public static void main(String[] args) {
        // Create a new Cell object
        Cell cell = new Cell(10, 20);
        cell.setBoolean_coord(1, 1, 1);
        cell.setBoolean_coord(1, 2, 1);
        cell.setBoolean_coord(1, 1, 2);
        cell.setBoolean_coord(1, 2, 3);
        cell.setBoolean_coord(1, 4, 4);
        cell.InitConfigFirst();

        System.out.println(" notre configuration de base pour le jeu de conway:\n" + cell.toString());
        cell.setnewEtapeConway();
        System.out.println(" notre configuration de base pour le jeu de conway:\n" + cell.toString());
        cell.setnewEtapeConway();
        System.out.println(" notre configuration de base pour le jeu de conway:\n" + cell.toString());
        cell.Init_cells();
        System.out.println(" notre configuration de base pour le jeu de conway:\n" + cell.toString());

    }
}
