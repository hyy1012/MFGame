package mf;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class BGpng {
	private static BufferedImage image; //图片
	static{
		image = loadImage("timg.jpg");
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
	
	protected int x; // x坐标
	protected int y; // y坐标
	
	/** 构造方法 */
	public BGpng(){
		x = 0;
		y = 0;
	}
	
	/** 画对象 g:画笔 */
	public void paintObject(Graphics g){
		g.drawImage(getImage(),x,y,null);  //画天空1
	}
	
	/** 重写getImage()获取图片 */
	public BufferedImage getImage() {
		return image;
	}
}
