package cz.uhk.veditor.gui;

import cz.uhk.veditor.grobjekty.AbstractGeomObject;
import cz.uhk.veditor.grobjekty.Circle;
import cz.uhk.veditor.grobjekty.Square;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/* hlavní okno aplikace */
public class MainWindow extends JFrame {

    private List<AbstractGeomObject> objekty = new ArrayList<>();

    public MainWindow() {
        super("Vektorový editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initTestData();

        add(new GraphPanel(objekty), BorderLayout.CENTER);

        setSize(800, 600);
        setLocationRelativeTo(null); /* null ->vůči celé obrazovce*/
    }

    private void initTestData() {
        objekty.add(new Circle(new Point(100,100),50, Color.GREEN));
        objekty.add(new Square(new Point(200,100),50, Color.BLUE));
        objekty.add(new Circle(new Point(100,300),50, Color.YELLOW));
        objekty.add(new Circle(new Point(500,100),50, Color.BLUE));
        objekty.add(new Circle(new Point(300,300),50, Color.BLUE));
        objekty.add(new Square(new Point(600,400),50, Color.RED));
        objekty.add(new Circle(new Point(200,300),50, Color.BLACK));
    }
}
