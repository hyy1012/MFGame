package test;

import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ButtonImage {
	public ButtonImage() {
		JFrame frame = new JFrame();
		JButton button = new JButton("confirm");
		ImageIcon icon = new ImageIcon("bj.jpg");
		button.setIcon(icon);
		frame.add(button);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new ButtonImage();
	}
}