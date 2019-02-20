import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Calculator {
    JFrame jf;
    JLabel num1;
    JLabel num2;
    JTextField t1;
    JTextField t2;
    JLabel msg;
    JLabel sum, diff, prod, quotient;
    JButton compute;

    Calculator() {
        jf = new JFrame("Calculator");
        jf.setLayout(new FlowLayout());
        jf.setSize(600, 400);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        num1 = new JLabel("First Number: ");
        num2 = new JLabel("Second number: ");
        t1 = new JTextField(10);
        t2 = new JTextField(10);
        msg = new JLabel();
        sum = new JLabel();
        diff = new JLabel();
        prod = new JLabel();
        quotient = new JLabel();
        compute = new JButton("Compute");
        jf.add(num1);
        jf.add(t1);
        jf.add(num2);
        jf.add(t2);
        jf.add(compute);
        jf.add(msg);
        jf.add(sum);
        jf.add(diff);
        jf.add(prod);
        jf.add(quotient);

        compute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                double a, b;
                msg.setText("");
                sum.setText("");
                diff.setText("");
                prod.setText("");
                quotient.setText("");
                try {
                    a = Double.parseDouble(t1.getText());
                    b = Double.parseDouble(t2.getText());
                }
                catch(NumberFormatException e) {
                    msg.setText("Please enter valid number");
                    return;
                }
                try {
                    if (b == 0) throw new ArithmeticException();
                    sum.setText("Sum = " + (a + b));
                    diff.setText("Difference = " + (a - b));
                    prod.setText("Product = " + (a * b));
                    quotient.setText("Quotient = " + (a / b));
                }
                catch(ArithmeticException e) {
                    msg.setText("Cannot divide by zero");
                }
            }
        });
    }
}

class Calc {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculator();
            }
        });
    }
}