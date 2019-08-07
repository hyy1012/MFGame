package mf;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class BGpng {
	private static BufferedImage image; //ͼƬ
	static{
		image = loadImage("timg.jpg");
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
	
	protected int x; // x����
	protected int y; // y����
	
	/** ���췽�� */
	public BGpng(){
		x = 0;
		y = 0;
	}
	
	/** ������ g:���� */
	public void paintObject(Graphics g){
		g.drawImage(getImage(),x,y,null);  //�����1
	}
	
	/** ��дgetImage()��ȡͼƬ */
	public BufferedImage getImage() {
		return image;
	}
}
