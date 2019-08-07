package mf.button;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import mf.HeroPoint;
import mf.WhitePoint;
import mf.World;

public class RestartBtn extends Btn {
	
	public RestartBtn(World world) {
		super(334, 235, "重新开始");
		this.setVisible(false);
		this.world = world;
	}

	public void event() {
		this.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setIcon(new ImageIcon(filebj)); // 加载背景图片
				setHorizontalTextPosition(SwingConstants.CENTER); // 设置图片居中
				world.getRlbtn().setIcon(null);
				world.getEbtn().setIcon(null);
				world.setStarttime(new Date().getTime());
			}
		});
		this.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				world.getRlbtn().setVisible(false);
				world.getEbtn().setVisible(false);
				world.getTimelbl().setVisible(false);
				world.setHp(new HeroPoint()); // 刷新英雄点
				world.setWp(new ArrayList<WhitePoint>()); // 刷新白点
				world.setState(World.RUNNING); // 重新开始游戏,放前面的话可能还没setfalse就画好了,会有BUG
			}
		});
	}
	
}
