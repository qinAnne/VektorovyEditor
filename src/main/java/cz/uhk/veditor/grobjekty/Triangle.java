package cz.uhk.veditor.grobjekty;

import java.awt.*;

public class Triangle extends AbstractGeomObject{
    protected int a; /*delka strany a = b = c*/

    public Triangle(Point position, int a, Color color) {
        super(position, color); /* o pozici a barvu se postara constructor předka*/
        this.a = a;
    }

    public Triangle(int a) {
        this.a = a;
    }

    @Override
    public boolean contains(int x, int y) {
        Polygon triangle = createTriangle();
        return triangle.contains(x, y);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        Polygon triangle = createTriangle();
        g.drawPolygon(triangle);
    }
    private Polygon createTriangle() {
        int height = (int)(Math.sqrt(3) / 2 * a);
        int offsetY = (int)(height / 2);

        int[] xPoints = {
                position.x - a/2,
                position.x + a/2,
                position.x
        };
        int[] yPoints = {
                position.y + offsetY,
                position.y + offsetY,
                position.y - (height - offsetY)
        };

        return new Polygon(xPoints, yPoints, 3);
    }
}
