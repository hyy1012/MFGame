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
		super(334, 235, "开始游戏");
		this.world = world;
	}
	
	public void event() {
		this.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setIcon(new ImageIcon(filebj)); // 加载背景图片
				setHorizontalTextPosition(SwingConstants.CENTER); // 设置图片居中
				world.getRlbtn().setIcon(null);
				world.getEbtn().setIcon(null);
			}
		});
		this.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				world.setState(World.RUNNING); // 开始游戏
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
		JFrame frame = new JFrame(); // 创建一个窗口对象
		JPanel jp = new JPanel();
		StartBtn sb = new StartBtn();
		jp.add(sb);
		jp.setLayout(null); // 这个设置了才有效果
		frame.add(jp);
		frame.setSize(800, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置关闭窗口时退出程序
		frame.setLocationRelativeTo(null); // 设置窗口居中显示
		frame.setVisible(true);
	}*/
	
}
