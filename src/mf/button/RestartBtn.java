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
		super(334, 235, "���¿�ʼ");
		this.setVisible(false);
		this.world = world;
	}

	public void event() {
		this.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setIcon(new ImageIcon(filebj)); // ���ر���ͼƬ
				setHorizontalTextPosition(SwingConstants.CENTER); // ����ͼƬ����
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
				world.setHp(new HeroPoint()); // ˢ��Ӣ�۵�
				world.setWp(new ArrayList<WhitePoint>()); // ˢ�°׵�
				world.setState(World.RUNNING); // ���¿�ʼ��Ϸ,��ǰ��Ļ����ܻ�ûsetfalse�ͻ�����,����BUG
			}
		});
	}
	
}
