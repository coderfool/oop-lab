import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

class FileMatch {
    JFrame jf;
    JLabel firstFile;
    JLabel secondFile;
    JLabel res1;
    JLabel res2;
    JTextField tf1;
    JTextField tf2;
    JButton compare;
    JLabel msg;

    FileMatch() {
        jf = new JFrame("Swing based file comparison utility");
        jf.setSize(500, 400);
        jf.setVisible(true);
        jf.setLayout(new BoxLayout(jf.getContentPane(), BoxLayout.Y_AXIS));
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firstFile = new JLabel("First file: ");
        firstFile.setPreferredSize(new Dimension(80, 50));
        jf.add(firstFile);
        tf1 = new JTextField(15);
        jf.add(tf1);
        res1 = new JLabel();
        res1.setPreferredSize(new Dimension(200, 50));
        jf.add(res1);
        secondFile = new JLabel("Second file: ");
        secondFile.setPreferredSize(new Dimension(80, 50));
        jf.add(secondFile);
        tf2 = new JTextField(15);
        jf.add(tf2);
        res2 = new JLabel();
        res2.setPreferredSize(new Dimension(200, 50));
        jf.add(res2);
        compare = new JButton("Compare");
        jf.add(compare);
        msg = new JLabel();
        msg.setPreferredSize(new Dimension(300, 50));
        jf.add(msg);

        tf1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String fileName = tf1.getText();
                try (FileInputStream in = new FileInputStream(fileName)) {
                    res1.setText(fileName + " exists");
                }
                catch (IOException e) {
                    res1.setText(fileName + " does not exist");
                }
            }
        });

        tf2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String fileName = tf2.getText();
                try (FileInputStream in = new FileInputStream(fileName)) {
                    res2.setText(fileName + " exists");
                }
                catch (IOException e) {
                    res2.setText(fileName + " does not exist");
                }
            }
        });

        compare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String file1 = tf1.getText();
                String file2 = tf2.getText();
                if (file1.equals("")) {
                    msg.setText("First filename missing");
                    return;
                }
                if (file2.equals("")) {
                    msg.setText("Second filename missing");
                    return;
                }
                try (FileInputStream f1 = new FileInputStream(file1); FileInputStream f2 = new FileInputStream(file2)) {
                    int a, b;
                    int count = 0;
                    boolean equal = true;
                    while ((a = f1.read()) != -1) {
                        b = f2.read();
                        if (a != b) {
                            equal = false;
                            break;
                        }
                        count++;
                    }
                    if(equal) msg.setText("Files equal");
                    else msg.setText("Files differ at location " + count);
                }
                catch(IOException e) {
                    msg.setText("File error");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FileMatch();
            }
        });
    }
}