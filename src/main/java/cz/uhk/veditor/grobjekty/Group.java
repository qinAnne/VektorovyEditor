package cz.uhk.veditor.grobjekty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Group extends AbstractGeomObject {

    private List<AbstractGeomObject> children = new ArrayList<>();

    public void add(AbstractGeomObject obj) {
        children.add(obj);
    }

    public List<AbstractGeomObject> getChildren() {
        return children;
    }

    public Stream<AbstractGeomObject> stream() {
        return children.stream();
    }

    @Override
    public void draw(Graphics2D g) {
        for (AbstractGeomObject obj : children) {
            obj.draw(g);
        }
    }

    @Override
    public boolean contains(int x, int y) {
        for (AbstractGeomObject obj : children) {
            if (obj.contains(x, y)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setPosition(Point newPos) {
        if (position == null) {
            position = newPos; // inicializace
            return;
        }

        int dx = newPos.x - position.x;
        int dy = newPos.y - position.y;

        for (AbstractGeomObject obj : children) {
            Point oldPos = obj.getPosition();
            obj.setPosition(new Point(oldPos.x + dx, oldPos.y + dy));
        }

        position = newPos;
    }
}

