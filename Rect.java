import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
<applet code="Rectangle" width=600 height=400>
</applet>
*/

public class Rectangle extends JApplet {
    JFrame jf;
    JLabel area;
    int x1, y1;
    int x2, y2;

    public Rectangle() {
        area = new JLabel();
        add(area);
        setSize(600, 400);
        setLayout(new FlowLayout());
        setVisible(true);
    }

    public void start() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();
                repaint();
            }
        });
    }

    public void paint(Graphics g) {
        super.paint(g);
    }
}