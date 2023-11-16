
import java.awt.Point;
/**
 * TestBalls.java
 *
 * This code demonstrates the usage of the Balls class to manipulate a collection of Point objects representing the positions of balls.
 * It initializes a collection of balls with specific positions, translates the positions by a given amount, reinitializes the positions, and prints the positions before and after each operation.
 */
public class TestBalls {
    public static void main(String[] args) {
        Point[] balls = {new Point(1, 2), new Point(3, 4), new Point(5, 6)};
        Balls ballCollection = new Balls(balls);

        System.out.println("Positions initiales: \n" + ballCollection.toString());

        ballCollection.translate(2, 3);
        System.out.println("Positions après transition: \n" + ballCollection.toString());

        ballCollection.reInit();
        System.out.println("Positions après la réinitialisation: \n" + ballCollection.toString());

    }
}