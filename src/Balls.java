import java.awt.Point;
import java.util.Arrays;

/**
 * pleins de balleeeeeees
 */
public class Balls {
    private Point[] balls;
    private Point[] initBalls;

    /**
     * je construis une copie de mon tableau de balles pour en créer une nouvelle instance, puis
     * je copie dans mon tableau de points initiaux.
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

    public Point[] getBalls() {
        return balls;
    }

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
     * remet les balles comme à l'origine, d'où l'utilité de mes deux listes de balle
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