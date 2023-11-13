import gui.GraphicalElement;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class TriangleElement implements GraphicalElement {
    private int[] xPoints;
    private int[] yPoints;
    private Color color;
    private int orientation;
    // beug orientation 0 à droite pi à gauche.

    public TriangleElement(int[] xPoints, int[] yPoints, Color color, int orientation) {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.color = color;
        this.orientation = orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        Polygon triangle = new Polygon(xPoints, yPoints, 3);
        AffineTransform transform = AffineTransform.getRotateInstance(-Math.toRadians(orientation), xPoints[0], yPoints[0]);
        Shape transformedTriangle = transform.createTransformedShape(triangle);
        graphics2D.setColor(color);
        graphics2D.fill(transformedTriangle);
        graphics2D.setColor(color);
        graphics2D.draw(transformedTriangle);
    }
}