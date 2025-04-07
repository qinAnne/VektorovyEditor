package cz.uhk.veditor.grobjekty;

import java.awt.*;

public class Circle extends AbstractGeomObject{
    protected int radius; /*delka strany*/

    public Circle(Point position, int radius, Color color) {
        super(position, color); /* o pozici a barvu se postara constructor předka*/
        this.radius = radius;
    }

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public boolean contains(int x, int y) {
        int m = position.x + radius;
        int n = position.y + radius;

        int l = (x-m)*(x-m)+(y-n)*(y-n);
        int r = radius*radius;

        return (l <= r);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.drawOval(position.x, position.y, 2*radius, 2*radius);

    }
}
