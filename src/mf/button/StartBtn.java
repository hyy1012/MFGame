package mf.button;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import mf.World;

public class StartBtn extends Btn {
	
	public StartBtn() {}
	public StartBtn(World world) {
		super(334, 235, "��ʼ��Ϸ");
		this.world = world;
	}
	
	public void event() {
		this.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setIcon(new ImageIcon(filebj)); // ���ر���ͼƬ
				setHorizontalTextPosition(SwingConstants.CENTER); // ����ͼƬ����
				world.getRlbtn().setIcon(null);
				world.getEbtn().setIcon(null);
			}
		});
		this.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				world.setState(World.RUNNING); // ��ʼ��Ϸ
				setVisible(false);
				world.getRlbtn().setVisible(false);
				world.getEbtn().setVisible(false);
				world.setStarttime(new Date().getTime());
			}
		});
		this.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyReleased(java.awt.event.KeyEvent e) {
				if (e.getKeyCode() == 10) {
				}
				if (e.getKeyCode() == 38) {
				}
				if (e.getKeyCode() == 40) {
				}
			}
		});
	}
		
	
	
	/**public static void main(String[] args) {
		JFrame frame = new JFrame(); // ����һ�����ڶ���
		JPanel jp = new JPanel();
		StartBtn sb = new StartBtn();
		jp.add(sb);
		jp.setLayout(null); // ��������˲���Ч��
		frame.add(jp);
		frame.setSize(800, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ùرմ���ʱ�˳�����
		frame.setLocationRelativeTo(null); // ���ô��ھ�����ʾ
		frame.setVisible(true);
	}*/
	
}
