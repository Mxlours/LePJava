import java.awt.Point;
import java.util.Arrays;

/**
 * This class represents our balls with point table
 */
public class Balls {
    private Point[] balls;
    private Point[] initBalls;

    /**
     * on construit une copie de notre tableau de balles pour en créer une nouvelle instance, puis
     * on copie dans le tableau de points initiaux.
     *
     * @param balls notre tableau de coordonnées de balles
     */
    public Balls(Point[] balls) {
        this.balls = balls;
        this.initBalls = new Point[balls.length];
        for (int i = 0; i < balls.length; i++) {
            this.initBalls[i] = new Point(balls[i]);
        }
    }
    /**
     * on retourne notre tableau de balles
     */
    public Point[] getBalls() {
        return balls;
    }
    /**
     * on retourne notre tableau de balles initiales
     */
    public Point[] getInitBalls() {
        return initBalls;
    }

    /**
     * translate les balles du montant indiqué sur chaque coordonnée
     *
     * @param dx x coord
     * @param dy y coord
     */
    public void translate(int dx, int dy) {
        for (Point ball : balls) {
            ball.translate(dx, dy);
        }
    }

    /**
     * remet les balles comme à l'origine, d'où l'utilité de mes deux tableaux de balles
     */
    public void reInit() {
        for (int i = 0; i < balls.length; i++) {
            balls[i].setLocation(initBalls[i]);
        }
    }

    /**
     * représentation de notre classe sous forme de string
     *
     * @return notre chaine clean
     */
    @Override
    public String toString() {
        String rt = "";
        for (Point ball : balls) {
            rt += "( " + ball.x + " , " + ball.y + " )\n";
        }
        return rt;
    }
}