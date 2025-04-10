package cz.uhk.veditor.gui;

import cz.uhk.veditor.grobjekty.AbstractGeomObject;

import javax.swing.*;
import java.awt.*;

public class GraphPanel extends JPanel {

    private AbstractGeomObject rootObject; // Group, který obsahuje všechny objekty

    public GraphPanel(AbstractGeomObject rootObject) {
        this.rootObject = rootObject;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2f));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (rootObject != null) {
            rootObject.draw(g2d);
        }
    }
}