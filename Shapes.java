import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
<applet code="Shapes" width=600 height=400>
</applet>
*/

public class Shapes extends JApplet {
    JButton arc, line, rect, polygon;
    int x1, y1;
    int x2, y2;
    int currX, currY;
    String shape;

    public Shapes() {
        arc = new JButton("Arc");
        add(arc);
        line = new JButton("Line");
        add(line);
        rect = new JButton("Rectangle");
        add(rect);
        polygon = new JButton("Polygon");
        add(polygon);
        setLayout(new FlowLayout());
        setVisible(true);
        currX = 0;
        currY = 0;
        shape = new String();
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
                currX = e.getX();
                currY = e.getY();
                repaint();
            }
            
            public void mouseMoved(MouseEvent e) {
                currX = e.getX();
                currY = e.getY();
                repaint();
            }
        });

        arc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                x1 = x2 = y1 = y2 = 0;
                shape = "arc";
                repaint();
            }
        });
        line.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                x1 = x2 = y1 = y2 = 0;
                shape = "line";
                repaint();
            }
        });
        rect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                x1 = x2 = y1 = y2 = 0;
                shape = "rect";
                repaint();
            }
        });
        polygon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                x1 = x2 = y1 = y2 = 0;
                shape = "polygon";
                repaint();
            }
        });
    }

    public void paint(Graphics g) {
        super.paint(g);
        int startX = Math.min(x1, x2);
        int startY = Math.min(y1, y2);
        int endX = Math.max(x1, x2);
        int endY = Math.max(y1, y2);
        int x[] = {170, 220, 220, 170, 120};
        int y[] = {100, 100, 150, 150, 130};
        g.setColor(Color.black);
        if (shape.equals("line"))
            g.drawLine(x1, y1, x2, y2);        
        else if (shape.equals("arc"))
            g.fillArc(startX, startY, endX - startX, endY - startY, 0, 120);        
        else if (shape.equals("rect"))
            g.fillRoundRect(startX, startY, endX - startX, endY - startY, 20, 20);       
        else if (shape.equals("polygon"))
            g.drawPolygon(x, y, 5);
        showStatus("Mouse coordinates: " + currX + ", " + currY);
    }
}