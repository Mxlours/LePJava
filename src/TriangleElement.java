import gui.GraphicalElement;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Represents a triangle shape that can be painted on a graphical area.
 * The position, color, and orientation of the triangle can be set, and it can be painted with the specified attributes.
 */
public class TriangleElement implements GraphicalElement {
    private int[] xPoints;
    private int[] yPoints;
    private Color color;
    private int orientation;

    /**
     * Constructs a TriangleElement object with the given points, color, and orientation.
     *
     * @param xPoints     the x-coordinates of the triangle's vertices
     * @param yPoints     the y-coordinates of the triangle's vertices
     * @param color       the color of the triangle
     * @param orientation the orientation of the triangle in degrees
     */
    public TriangleElement(int[] xPoints, int[] yPoints, Color color, int orientation) {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.color = color;
        this.orientation = orientation;
    }

    /**
     * Sets the orientation of the triangle.
     *
     * @param orientation the new orientation of the triangle in degrees
     */
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    /**
     * Paints the triangle on the given Graphics2D object.
     *
     * @param graphics2D the Graphics2D object to paint on
     */
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