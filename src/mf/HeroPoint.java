package mf;

import java.awt.image.BufferedImage;

public class HeroPoint extends Point {
	private static BufferedImage[] images; // ͼƬ����
	static {
		images = new BufferedImage[2];
		for (int i = 1; i <= images.length; i++) {
			images[i - 1] = loadImage("gwl" + i + ".gif");
		}
	}

	public static final int LIFE = 0; // ���ŵ�
	public static final int DEAD = 1; // ���˵�
	protected int state = LIFE; // ��ǰ״̬(Ĭ��Ϊ���ŵ�)

	boolean up = false; // ����
	boolean down = false; // ����
	boolean left = false; // ����
	boolean right = false; // ����

	/** ���췽�� */
	public HeroPoint() {
		super(16, 16, 385, 320);
	}

	/** Ӣ�۵�ͨ���������ƶ� x,y */
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

	/** �ж��Ƿ��ǻ����� */
	public boolean isLife() {
		return state == LIFE;
	}

	/** �ж��Ƿ������˵� */
	public boolean isDead() {
		return state == DEAD;
	}

	/** ��дgetImage()��ȡͼƬ */
	public BufferedImage getImage() { // 10������һ��
		if (isLife()) { // ��������
			return images[0]; // ����ͼ1
		} else { // ��������
			return images[1]; // ����ͼ2
		}
	}

}
