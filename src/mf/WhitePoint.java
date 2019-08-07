package mf;

import java.awt.image.BufferedImage;
import java.util.Random;

public class WhitePoint extends Point {
	private static BufferedImage image; // ͼƬ����
	static {
		image = loadImage("white.png");
	}
	
	private int step = 2; // ����߿��Ĳ���
	private float xx; // С�׵���Ӣ�۵��x����
	private int yy; // С�׵���Ӣ�۵��y����

	/** ���췽�� */
	public WhitePoint(int hx, int hy) {
		super(10, 10);
		setXxYy(hx, hy);
	}

	/** С�׵��ƶ� */
	public void step(int hx, int hy) {
//		if (x <= 0) {
//			setXxYy(hx, hy, "left");
//		} else if (x >= World.WIDTH - 16) {
//			setXxYy(hx, hy, "right");
//		} else if (y <= 0) {
//			setXxYy(hx, hy, "top");
//		} else if (y >= World.HEIGHT - 38) {
//			setXxYy(hx, hy, "bottom");
//		}

		this.x += xx; // ��Ӣ�۵��x����
		this.y += yy; // ��Ӣ�۵��y����
	}

	/** ��дgetImage()��ȡͼƬ */
	public BufferedImage getImage() {
		return image;
	}

	/** ������Ӣ�۵�Ĳ��� */
	public void setXxYy(int hx, int hy) {
		xx = (int) ((hx + 2) - this.x) / 100;
		yy = (int) ((hy + 2) - this.y) / 100;
	}

	/** ���������������Ӣ�۵�Ĳ���,��ʱ���� */
	public void setXxYy(int hx, int hy, String direction) {
		xx = (int) ((hx + 2) - this.x) / 200;
		yy = (int) ((hy + 2) - this.y) / 200;
		Random rand = new Random(); // ���������
		if ("left".equals(direction) && xx == 0) {
			xx = step;
			if (yy == 0)
				yy = rand.nextInt(2) + step;
		} else if ("right".equals(direction) && xx == 0) {
			xx = -step;
			if (yy == 0)
				yy = -rand.nextInt(2) - step;
		} else if ("top".equals(direction) && yy == 0) {
			yy = step;
			if (xx == 0)
				xx = rand.nextInt(2) + step;
		} else if ("bottom".equals(direction) && yy == 0) {
			yy = -step;
			if (xx == 0)
				xx = -rand.nextInt(2) - step;
		}
	}

	/** ��дoutOfBounds()�ж��Ƿ�Խ�� */
	public boolean outOfBounds(){
		return x <= 0 || x >= World.WIDTH - 16 || y <= 0 || y >= World.HEIGHT - 38;
	}

}
