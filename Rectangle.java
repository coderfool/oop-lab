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
    int currX = 0, currY = 0;
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
            public void actionPerformed() {
                shape = "arc";
            }
        });
        line.addActionListener(new ActionListener() {
            public void actionPerformed() {
                shape = "line";
            }
        });
        rect.addActionListener(new ActionListener() {
            public void actionPerformed() {
                shape = "rect";
            }
        });
        polygon.addActionListener(new ActionListener() {
            public void actionPerformed() {
                shape = "polygon";
            }
        });
    }

    public void paint(Graphics g) {
        super.paint(g);
        int startX = Math.min(x1, x2);
        int startY = Math.min(y1, y2);
        int endX = Math.max(x1, x2);
        int endY = Math.max(y1, y2);
        int x[] = {170, 220, 220, 170, 230};
        int y[] = {100, 100, 150, 150, 250};
        g.setColor(Color.black);
        if (shape.equals("line"))
            g.drawLine(x1, y1, x2, y2);        
        else if (shape.equals("arc"))
            g.fillArc(x1, y1, x2, y2, 0, 120);        
        else if (shape.equals("rect"))
            g.fillRoundRect(startX, startY, endX - startX, endY - startY, 10, 10);       
        else if (shape.equals("polygon"))
            g.drawPolygon(x, y, 5);
        showStatus("Mouse coordinates: " + currX + ", " + currY);
    }
}