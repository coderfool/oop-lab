import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
<Applet code="Scroll" width="600" height="400">
</Applet>
*/

public class Scroll extends JApplet {
    JLabel label;
    JTextField t;
    JButton start;
    JButton stop;
    String text;
    boolean running;

    public Scroll() {
        setLayout(new FlowLayout());
        setVisible(true);
        label = new JLabel("Enter text: ");
        add(label);
        t = new JTextField(10);
        add(t);
        start = new JButton("Start");
        add(start);
        stop = new JButton("Stop");
        add(stop);
        text = new String();
        running = false;
    }

    public void start() {
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Thread thread = new Thread(new Runnable() {
                    public void run() {
                        try {
                            text = t.getText();
                            running = true;
                            while(running) {
                                repaint();
                                Thread.sleep(1000); 
                                text = text.substring(1, text.length()) + text.charAt(0);
                            }
                        }
                        catch(InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        });
    
        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                running = false;
            }
        });
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawString(text, 150, 100);
    }
}