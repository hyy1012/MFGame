package mf.button;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import mf.World;

public class ExitBtn extends Btn {

	public ExitBtn(World world) {
		super(334, 365, "退出游戏");
		this.world = world;
//		this.sbtn = world.getSbtn();
//		this.rlbtn = world.getRlbtn();
	}
	
	public void event() {
		this.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setIcon(new ImageIcon(filebj));
				setHorizontalTextPosition(SwingConstants.CENTER); // 设置图片居中
				world.getSbtn().setIcon(null);
				world.getRlbtn().setIcon(null);
				world.getRbtn().setIcon(null);
			}
		});
		this.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // 退出程序
			}
		});
	}

}
