package test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Welcome extends JFrame {
	Image img1;
	ImageIcon img2;
	Toolkit tool;
	JPanel p1;
	JLabel l1;
	JButton b1, b2;

	public static void main(String args[]) {
		Welcome wel = new Welcome();
		wel.setSize(500, 600);

	}

	Welcome() {
		b1 = new JButton("进入");
		b2 = new JButton("退出");
		tool = getToolkit();
		Dimension dim = tool.getScreenSize();
		int a = (int) dim.getHeight();
		int b = (int) dim.getWidth();
		img1 = tool.getImage("1.jpg");
		img2 = new ImageIcon("cici.jpg");
		this.setTitle("彩票系统");
		this.setIconImage(img1);
		l1 = new JLabel(img2);
		l1.setBounds(0, 0, img2.getIconWidth(), img2.getIconHeight());
		p1 = (JPanel) this.getContentPane();
		p1.setOpaque(false);
		p1.setLayout(new FlowLayout());
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().add(l1, new Integer(Integer.MIN_VALUE));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBounds(b / 4, a / 4, img2.getIconWidth(), img2.getIconHeight());
		b1.setBounds(this.getWidth() / 2, this.getHeight(), 100, 100);
		b2.setBounds(this.getWidth() / 4, this.getHeight(), 100, 100);
		add(b1);
		add(b2);
		this.setVisible(true);
	}

}