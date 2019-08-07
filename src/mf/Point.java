package mf;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

public abstract class Point {
	
	protected int width; 		//宽
	protected int height; 	    //高
	protected int x; 			//x坐标
	protected int y; 			//y坐标

	// HeroPoint的构造方法
	public Point(int width,int height,int x,int y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	// WhitePoint的构造方法
	public Point(int width,int height) {
		this.width = width;
		this.height = height;
		Random rand = new Random(); // 随机数对象,产生0到3之内的随机数
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
	
	/** 加载/读取图片 */
	public static BufferedImage loadImage(String fileName) {
		try {
			BufferedImage img = ImageIO.read(WhitePoint.class.getResource(fileName));
			return img;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	/** 获取图片 */
	public abstract BufferedImage getImage();
	
	/** 画对象 g:画笔 */
	public void paintObject(Graphics g) {
		g.drawImage(getImage(), x, y, null); // 画对象
	}
	
	/** 判断白点是否与英雄点相撞 */
	public boolean hit(Point hp) {
		int x1 = hp.x - this.width; // x1:英雄点的x-白点的宽
		int x2 = hp.x + hp.width; // x2:英雄点的x+英雄点的宽
		int y1 = hp.y - this.height; // y1:英雄点的y-白点的高
		int y2 = hp.y + hp.height; // y2:英雄点的y+英雄点的高

		return x >= x1 && x <= x2 && y >= y1 && y <= y2; // x在x1与x2之间，并且，y在y1与y2之间，即为撞上了
	}
	
}
