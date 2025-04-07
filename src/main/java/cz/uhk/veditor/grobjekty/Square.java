package cz.uhk.veditor.grobjekty;

import java.awt.*;

public class Square extends AbstractGeomObject{
    protected int a; /*delka strany*/

    public Square(Point position, int a, Color color) {
        super(position, color); /* o pozici a barvu se postara constructor předka*/
        this.a = a;
    }

    public Square(int a) {
        this.a = a;
    }

    @Override
    public boolean contains(int x, int y) {
        return (x >= position.x && x<=position.x+a && y >= position.y && y<=position.y+a);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.drawRect(position.x, position.y, a, a);

    }
}
