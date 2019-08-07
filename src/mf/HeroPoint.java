package mf;

import java.awt.image.BufferedImage;

public class HeroPoint extends Point {
	private static BufferedImage[] images; // 图片数组
	static {
		images = new BufferedImage[2];
		for (int i = 1; i <= images.length; i++) {
			images[i - 1] = loadImage("gwl" + i + ".gif");
		}
	}

	public static final int LIFE = 0; // 活着的
	public static final int DEAD = 1; // 死了的
	protected int state = LIFE; // 当前状态(默认为活着的)

	boolean up = false; // 向上
	boolean down = false; // 向下
	boolean left = false; // 向左
	boolean right = false; // 向右

	/** 构造方法 */
	public HeroPoint() {
		super(16, 16, 385, 320);
	}

	/** 英雄点通过按键盘移动 x,y */
	public void step() {
		if (up && y >= 0)
			this.y -= 3;
		if (down && y <= World.HEIGHT - 47)
			this.y += 3;
		if (left && x >= 0)
			this.x -= 3;
		if (right && x <= World.WIDTH - 25)
			this.x += 3;
	}

	/** 判断是否是活着呢 */
	public boolean isLife() {
		return state == LIFE;
	}

	/** 判断是否是死了的 */
	public boolean isDead() {
		return state == DEAD;
	}

	/** 重写getImage()获取图片 */
	public BufferedImage getImage() { // 10毫秒走一次
		if (isLife()) { // 若活着呢
			return images[0]; // 返回图1
		} else { // 若死了呢
			return images[1]; // 返回图2
		}
	}

}
