package mf;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

public abstract class Point {
	
	protected int width; 		//��
	protected int height; 	    //��
	protected int x; 			//x����
	protected int y; 			//y����

	// HeroPoint�Ĺ��췽��
	public Point(int width,int height,int x,int y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	// WhitePoint�Ĺ��췽��
	public Point(int width,int height) {
		this.width = width;
		this.height = height;
		Random rand = new Random(); // ���������,����0��3֮�ڵ������
		switch (rand.nextInt(4) + 1) {
			case 1:
				x = rand.nextInt(World.WIDTH - 16);
				y = 0;
				break;
			case 2:
				x = rand.nextInt(World.WIDTH - 16);
				y = World.HEIGHT - 38;
				break;
			case 3:
				x = World.WIDTH - 16;
				y = rand.nextInt(World.HEIGHT - 38);
				break;
			case 4:
				x = 0;
				y = rand.nextInt(World.HEIGHT - 38);
				break;
			default:
				break;
		}
	}
	
	/** ����/��ȡͼƬ */
	public static BufferedImage loadImage(String fileName) {
		try {
			BufferedImage img = ImageIO.read(WhitePoint.class.getResource(fileName));
			return img;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	/** ��ȡͼƬ */
	public abstract BufferedImage getImage();
	
	/** ������ g:���� */
	public void paintObject(Graphics g) {
		g.drawImage(getImage(), x, y, null); // ������
	}
	
	/** �жϰ׵��Ƿ���Ӣ�۵���ײ */
	public boolean hit(Point hp) {
		int x1 = hp.x - this.width; // x1:Ӣ�۵��x-�׵�Ŀ�
		int x2 = hp.x + hp.width; // x2:Ӣ�۵��x+Ӣ�۵�Ŀ�
		int y1 = hp.y - this.height; // y1:Ӣ�۵��y-�׵�ĸ�
		int y2 = hp.y + hp.height; // y2:Ӣ�۵��y+Ӣ�۵�ĸ�

		return x >= x1 && x <= x2 && y >= y1 && y <= y2; // x��x1��x2֮�䣬���ң�y��y1��y2֮�䣬��Ϊײ����
	}
	
}
