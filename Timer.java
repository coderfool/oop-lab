import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Timer {
    JFrame jf;
    JLabel time;
    JButton start;
    JButton stop;
    long t1, t2;

    Timer() {
        jf = new JFrame("Timer GUI");
        jf.setSize(400, 400);
        jf.setLayout(new BorderLayout()); 
        jf.setVisible(true);
        time = new JLabel("Click start to begin timer", SwingConstants.CENTER);
        start = new JButton("Start");
        stop = new JButton("Stop");
        jf.add(start, BorderLayout.WEST);
        jf.add(time, BorderLayout.CENTER);
        jf.add(stop, BorderLayout.EAST);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                time.setText("Timer running...");
                t1 = System.currentTimeMillis();
            }
        });
    
        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                t2 = System.currentTimeMillis();
                double t = (t2 - t1) / 1000.0;
                time.setText("Time elapsed: " + String.format("%.3f", t) + "s");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Timer();
            }
        });
    }
}
