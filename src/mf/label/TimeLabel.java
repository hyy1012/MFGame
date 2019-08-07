package mf.label;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class TimeLabel extends JLabel {
	
	public TimeLabel() {
		this.setBounds(275, 100, 300, 100);// 游戏结束时显示的标签位置
		this.setFont(new Font("Dialog", Font.BOLD, 24));
		this.setForeground(new Color(250, 2, 2));
		this.setEnabled(true);
		this.setVisible(false);
	}

}
