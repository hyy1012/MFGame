package mf.button;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import mf.World;

public class RankingListBtn extends Btn {

	public RankingListBtn(World world) {
		super(334, 300, "�鿴���а�");
		this.world = world;
//		this.sbtn = world.getSbtn();
//		this.ebtn = world.getEbtn();
	}
	
	public void event() {
		this.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setIcon(new ImageIcon(filebj)); // ���ر���ͼƬ
				setHorizontalTextPosition(SwingConstants.CENTER); // ����ͼƬ����
				world.getSbtn().setIcon(null);
				world.getEbtn().setIcon(null);
				world.getRbtn().setIcon(null);
			}
		});
	}

}
