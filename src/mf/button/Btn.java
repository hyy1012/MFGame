package mf.button;

import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.JButton;

import mf.World;

public class Btn extends JButton {
	
	private static final int WIDTH = 116; // ���ڵĿ�
	private static final int HEIGHT = 60; // ���ڵĿ�
	protected World world;
	URLClassLoader urlLoader = (URLClassLoader) this.getClass().getClassLoader();
	URL filebj = urlLoader.findResource("./mf/bj.png"); // ����ͼƬ�ļ�
	
	public Btn() {}
	public Btn(int x, int y, String tname) {
		super(tname);
		this.setBounds(x, y, WIDTH, HEIGHT);
	}

}
