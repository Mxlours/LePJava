import java.awt.Point;

public class TestBalls {
    public static void main(String[] args) {
        Point[] balls = {new Point(1, 2), new Point(3, 4), new Point(5, 6)};
        Balls ballCollection = new Balls(balls);

        /*System.out.println("Positions initiales: \n" + ballCollection.toString());

        ballCollection.translate(2, 3);
        System.out.println("Positions après transition: \n" + ballCollection.toString());

        ballCollection.reInit();
        System.out.println("Positions après la réinitialisation: \n" + ballCollection.toString());*/
        int nombreEntier = 255; // Remplacez 255 par l'entier que vous souhaitez convertir en hexadécimal
        String hexa = Integer.toHexString(nombreEntier);
        System.out.println("La valeur hexadécimale est : " + hexa);

    }
}