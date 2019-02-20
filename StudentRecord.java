import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*<applet code="StudentRecord" width=600 height=400></applet>*/

class Student {
	String name;
	int rollNo;
	double cgpa;

	Student(String n, int roll, double cg) {
		name = n;
		rollNo = roll;
		cgpa = cg;
	}

	public String toString() {
		return "Name: " + name + " CGPA: " + cgpa;
	}

	static void sort(Student[] s) {
		int n = 5;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (s[j].name.compareTo(s[j + 1].name) > 0) {
					Student temp = s[j];
					s[j] = s[j + 1];
					s[j + 1] = temp;
				}
				else if (s[j].name.equals(s[j + 1].name)) {
					if (s[j].cgpa > s[j + 1].cgpa) {
						Student temp = s[j];
						s[j] = s[j + 1];
						s[j + 1] = temp;
					}
				}
			}
		}
	}
}

class IncorrectCGPAException extends Exception {
	public String toString() {
		return "CGPA cannot be greater than 10";
	}
}

class IncorrectNameException extends Exception {
	public String toString() {
		return "Name must begin with alphabet";
	}
}

public class StudentRecord extends JApplet {
	final int MAX = 10;
	Student[] s;
	int count;
	JButton sort;
	JButton search;
	JLabel rollno;
	JTextField tf;
	String msg = "";
	String option = "";

	public StudentRecord() {
		count = 5;
		s = new Student[MAX];
		s[0] = new Student("kffh", 35, 8.25);
		s[1] = new Student("xakcb", 32, 9.25);
		s[2] = new Student("kffh", 36, 8.21);
		s[3] = new Student("asdf", 25, 10);
		s[4] = new Student("ldif", 15, 6.25);
		setLayout(new FlowLayout());
		setVisible(true);
		sort = new JButton("SORT");
		add(sort);
		rollno = new JLabel("Roll No: ");
		add(rollno);
		tf = new JTextField(10);
		add(tf);
		search = new JButton("SEARCH");
		add(search);
	}

	public void start() {
		sort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				option = "sort";
				Student.sort(s);
				repaint();
			}
		});

		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				option = "search";
				try {
					boolean found = false;
					int r = Integer.parseInt(tf.getText());
					for (Student student : s) {
						if (student == null) break;
						if (student.rollNo == r) {
							found = true;
							break;
						}
					}
					if(found) msg = "Student found";
					else msg = "Student not found";
				}
				catch (NumberFormatException e) {
					msg = "Please enter valid roll number";
				}
				repaint();
			}
		});
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (option.equals("sort")) {
			int v = 0;
			for (Student student : s) {
				if (student == null) break;
				g.drawString(student.toString(), 100, 100 + v * 30);
				v++;
			}
		}
		else if (option.equals("search")) {
			g.drawString(msg, 100, 100);
		}
	}
}


