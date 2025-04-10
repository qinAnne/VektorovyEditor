package cz.uhk.veditor.grobjekty;

import java.awt.*;

public class Rectangle extends AbstractGeomObject{
    protected int a;/*delka strany a, vodorovne*/
    protected int b;/*delka strany b, svisle*/

    public Rectangle(Point position, int a, int b, Color color) {
        super(position, color); /* o pozici a barvu se postara constructor předka*/
        this.a = a;
        this.b = b;
    }

    public Rectangle(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean contains(int x, int y) {
        return (x >= position.x && x<=position.x+a && y >= position.y && y<=position.y+b);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.drawRect(position.x, position.y, a, b);

    }
}
