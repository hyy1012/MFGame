package mf;

import java.awt.image.BufferedImage;
import java.util.Random;

public class WhitePoint extends Point {
	private static BufferedImage image; // 图片数组
	static {
		image = loadImage("white.png");
	}
	
	private int step = 2; // 到达边框后的步距
	private float xx; // 小白点与英雄点的x步距
	private int yy; // 小白点与英雄点的y步距

	/** 构造方法 */
	public WhitePoint(int hx, int hy) {
		super(10, 10);
		setXxYy(hx, hy);
	}

	/** 小白点移动 */
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

		this.x += xx; // 朝英雄点的x方向
		this.y += yy; // 朝英雄点的y方向
	}

	/** 重写getImage()获取图片 */
	public BufferedImage getImage() {
		return image;
	}

	/** 设置往英雄点的步距 */
	public void setXxYy(int hx, int hy) {
		xx = (int) ((hx + 2) - this.x) / 100;
		yy = (int) ((hy + 2) - this.y) / 100;
	}

	/** 反弹情况下设置往英雄点的步距,暂时不用 */
	public void setXxYy(int hx, int hy, String direction) {
		xx = (int) ((hx + 2) - this.x) / 200;
		yy = (int) ((hy + 2) - this.y) / 200;
		Random rand = new Random(); // 随机数对象
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

	/** 重写outOfBounds()判断是否越界 */
	public boolean outOfBounds(){
		return x <= 0 || x >= World.WIDTH - 16 || y <= 0 || y >= World.HEIGHT - 38;
	}

}
