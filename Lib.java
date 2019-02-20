import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Book {
    String title;
    String author;
    int price;

    Book(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    } 
}

class Lib {
    final int MAX = 10;
    Book[] books;
    int count;
    JFrame jf;
    JLabel enterDetails;
    JLabel enterTitle;
    JLabel res;
    JTextField title;
    JTextField auth;
    JTextField price;
    JTextField searchKey;
    JButton create;
    JButton search;

    Lib() {
        books = new Book[MAX];
        count = 0;
        jf = new JFrame("Library GUI");
        jf.setSize(180, 300);
        jf.setLayout(new FlowLayout()); 
        jf.setVisible(true);
        enterDetails = new JLabel("Enter book details");
        jf.add(enterDetails);
        title = new JTextField(10);
        jf.add(title);
        auth = new JTextField(10);
        jf.add(auth);
        price = new JTextField(10);
        jf.add(price);
        create = new JButton("Create entry");
        jf.add(create);
        enterTitle = new JLabel("Enter title");
        jf.add(enterTitle);
        searchKey = new JTextField(10);
        jf.add(searchKey);
        search = new JButton("Search");
        jf.add(search);
        res = new JLabel();
        jf.add(res);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String t = title.getText();
                String a = auth.getText();
                String p = price.getText();
                try {
                    int pr = Integer.parseInt(p);
                    books[count++] = new Book(t, a, pr);
                    res.setText("Book added");
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog(null, "Library full");
                }
                catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter valid price");
                }
            }
        });
    
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String key = searchKey.getText();
                res.setText("Book not found");
                for (Book b : books) {
                    if (b == null) {
                        break;
                    }
                    if (b.title.equals(key)) {
                        res.setText("Book found");
                        break;
                    }
                }
            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Lib();
            }
        });
    }
}
