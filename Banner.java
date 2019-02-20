import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*<Applet code="Banner" width="600" height="800"></Applet>*/

public class Banner extends JApplet {
	String s = "THIS IS JAVA LAB EXAM";
	String option = "";
	String table = "";
	JButton banner;
	JButton rect;
	JButton mult;
	int x1, y1;
	int x2, y2;
	Runnable r1, r2, r3;
	boolean scroll;

	public Banner() {
		banner = new JButton("BANNER");
		rect = new JButton("RECTANGLE");
		mult = new JButton("TABLE");
		setLayout(new FlowLayout());
		setVisible(true);
		add(banner);
		add(rect);
		add(mult);
		x1 = x2 = y1 = y2 = 0;
		scroll = false;
		r1 = new Runnable() {
			public void run() {
				try {
					while (scroll) {
						repaint();
						Thread.sleep(500);
						s = s.substring(1, s.length()) + s.charAt(0);
					}
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		r2 = new Runnable() {
			public void run() {
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
		};

		r3 = new Runnable() {
			public void run() {
				try {
					table = "";
					for (int i = 0; i <= 10; i++) {
						table += "6 * " + i + " = " + (6 * i) + "\n";
						Thread.sleep(500);
						repaint();
					}
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
	}

	public void start() {
		banner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				option = "banner";
				scroll = true;
				repaint();
				new Thread(r1).start();
			}
 		});

 		rect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				option = "rect";
				scroll = false;
				x1 = x2 = y1 = y2 = 0;
				repaint();
				new Thread(r2).start();
			}
 		});

 		mult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				option = "table";
				scroll = false;
				repaint();
				new Thread(r3).start();
			}
 		});
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (option.equals("banner")) g.drawString(s, 200, 100);
		else if (option.equals("rect")) {
			int startX = Math.min(x1, x2);
			int endX = Math.max(x1, x2);
			int startY = Math.min(y1, y2);
			int endY = Math.max(y1, y2);
			g.fillRoundRect(startX, startY, endX - startX, endY - startY, 20, 20);
		}
		else if (option.equals("table")) {
			int v = 0;
			for (String line : table.split("\n")) {
				g.drawString(line, 200, 100 + v * 30);
				v++;
			}
		}
	}
}