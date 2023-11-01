import java.awt.*;

public class TestCell {
    public static void main(String[] args) {
        // Create a new Cell object
        Cell cell = new Cell( 10, 10);
        cell.setBoolean_coord(true, 1, 1);
        cell.setBoolean_coord(true, 2, 1);
        cell.setBoolean_coord(true, 1, 2);
        cell.setBoolean_coord(true, 3, 2);
        cell.setBoolean_coord(true, 4, 4);
        cell.InitConfigFirst();

        System.out.println(" notre configuration de base pour le jeu de conway:\n" + cell.toString());
        cell.setNewetape();
        System.out.println(" notre configuration de base pour le jeu de conway:\n" + cell.toString());
        cell.setNewetape();
        System.out.println(" notre configuration de base pour le jeu de conway:\n" + cell.toString());
        cell.Init_cells();
        System.out.println(" notre configuration de base pour le jeu de conway:\n" + cell.toString());

    }
}
