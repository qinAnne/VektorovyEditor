package cz.uhk.veditor.gui;

import cz.uhk.veditor.grobjekty.*;
import cz.uhk.veditor.grobjekty.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;



// hlavní okno aplikace //
public class MainWindow extends JFrame {

    private JToolBar toolBar;
    private JToggleButton btSquare;
    private JToggleButton btCircle;
    private JToggleButton btTriangle;
    private JToggleButton btRectangle;
    private JToggleButton btDrag;

    private Group objekty = new Group();
    private AbstractGeomObject selected = null;
    private Point offset = null;
    private int offsetY = 0;
    private int offsetX = 0;
    public MainWindow() {
        super("Vektorový editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initTestData();
        createToolBar();

        GraphPanel panel = new GraphPanel(objekty);
        add(panel, BorderLayout.CENTER);
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (btDrag.isSelected()) {
                    selected = findSelected(e.getX(), e.getY());
                    if (selected != null) {
                        offset = new Point(
                                e.getX() - selected.getPosition().x,
                                e.getY() - selected.getPosition().y
                        );
                    }
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (btDrag.isSelected() && selected != null && offset != null) {
                    Point newPos = new Point(
                            e.getX() - offset.x,
                            e.getY() - offset.y
                    );
                    selected.setPosition(newPos);
                    repaint(); // prekresleni komponentu //
                }
            }

            public void mouseReleased(MouseEvent e) {
                selected = null;
                offset = null;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                offsetY = getInsets().top + toolBar.getHeight();
                offsetX = getInsets().bottom;
                AbstractGeomObject newObject = null;
                if (e.getButton() == MouseEvent.BUTTON1 && !btDrag.isSelected()) {
                    if (btCircle.isSelected()) {
                        newObject = new Circle(new Point(e.getX() - offsetX, e.getY() - offsetY), 50, Color.RED);
                    }
                    if (btSquare.isSelected()) {
                        newObject = new Square(new Point(e.getX() - offsetX, e.getY() - offsetY), 50, Color.BLUE);
                    }
                    if (btTriangle.isSelected()) {
                        newObject = new Triangle(new Point(e.getX() - offsetX, e.getY() - offsetY), 50, Color.GREEN);
                    }
                    if (btRectangle.isSelected()) {
                        newObject = new Rectangle(new Point(e.getX() - offsetX, e.getY() - offsetY), 50, 60, Color.YELLOW);
                    }

                    if (newObject != null) {
                        // Přidání objektu do skupiny
                        objekty.add(newObject);
                    }
                    repaint();
                }
            }
        };

        addMouseListener(mouseAdapter);

        addMouseMotionListener(mouseAdapter);

        setSize(800,600);

        setLocationRelativeTo(null); // -> null - vuci cele obrazovce //
    }

    private void createToolBar () {
        toolBar = new JToolBar(JToolBar.HORIZONTAL);
        add(toolBar, BorderLayout.NORTH);
        btSquare = new JToggleButton("Ctverec", new ImageIcon(getClass().getResource("/square.png")));
        btCircle = new JToggleButton("Kruznice", new ImageIcon(getClass().getResource("/circle.png")));
        btTriangle = new JToggleButton("Trojuhelnik", new ImageIcon(getClass().getResource("/triangle.png")));
        btRectangle = new JToggleButton("Obdelnik", new ImageIcon(getClass().getResource("/rectangle.png")));
        btDrag = new JToggleButton("Drag", new ImageIcon(getClass().getResource("/drag.png")));

        toolBar.add(btSquare);
        toolBar.add(btCircle);
        toolBar.add(btTriangle);
        toolBar.add(btRectangle);
        toolBar.add(btDrag);

        ButtonGroup gr = new ButtonGroup();
        gr.add(btSquare);
        gr.add(btCircle);
        gr.add(btTriangle);
        gr.add(btRectangle);
        gr.add(btDrag);
    }

            private void initTestData () {
            objekty.add(new Circle(new Point(100, 100), 50, Color.GREEN));
            objekty.add(new Square(new Point(200, 100), 50, Color.BLUE));
            objekty.add(new Circle(new Point(100, 300), 50, Color.YELLOW));
            objekty.add(new Circle(new Point(300, 300), 50, Color.BLUE));
            objekty.add(new Square(new Point(600, 400), 50, Color.RED));
            objekty.add(new Circle(new Point(200, 300), 50, Color.BLACK));
            objekty.add(new Rectangle(new Point(350, 200), 50, 60, Color.GREEN));
            objekty.add(new Triangle(new Point(150, 250), 50, Color.GREEN));
        }
    private AbstractGeomObject findSelected ( int x, int y){
        Optional<AbstractGeomObject> res = objekty.stream().filter(obj -> obj.contains(x - offsetX, y - offsetY)).findFirst();
        return res.orElse(null);
    }

}
