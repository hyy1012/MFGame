package mf;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mf.button.ExitBtn;
import mf.button.RankingListBtn;
import mf.button.RestartBtn;
import mf.button.StartBtn;
import mf.label.TimeLabel;

public class World extends JPanel {
	/**
	 * �汾1
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 800; // ���ڵĿ�
	public static final int HEIGHT = 700; // ���ڵĸ�

	public static final int START = 0; // ����״̬
	public static final int RUNNING = 1; // ����״̬
	public static final int GAME_OVER = 3; // ��Ϸ����״̬
	private int state = START; // ��ǰ״̬(Ĭ��Ϊ����״̬)

	private BGpng bg = new BGpng(); // ����ͼƬ
	private HeroPoint hp = new HeroPoint(); // ����Ӣ�۵�
	private List<WhitePoint> wp = new ArrayList<WhitePoint>(); // �����׵�
	
	private StartBtn sbtn = new StartBtn(this); // ������ʼ��ť
	private RestartBtn rbtn = new RestartBtn(this); // �������¿�ʼ��ť
	private RankingListBtn rlbtn = new RankingListBtn(this); // ������ѯ���а�ť
	private ExitBtn ebtn = new ExitBtn(this); // �����˳���ť
	private TimeLabel timelbl = new TimeLabel();
	
	private long starttime; // ��ʼʱ��
	private long overtime; // ����ʱ��
	private Date date = new Date();
	private SimpleDateFormat fmt= new SimpleDateFormat("������:  mm �� ss ��"); // ��ʽ������

	/** �����׵���� */
	public void nextOne() {
		wp.add(new WhitePoint(hp.x, hp.y));
	}

	int enterIndex = 0; // �����볡����

	/** С�׵��볡 */
	public void enterAction() { // 10������һ��
		enterIndex++; // ÿ10������1
		if (wp.size() <= 60 && enterIndex % 10 == 0) { // ÿ100(10*10)������һ��
			nextOne();
		}
	}

	/** С�׵��ƶ� */
	public void stepAction() { // 10������һ��
		for (int i = 0; i < wp.size(); i++) { // �������е���
			wp.get(i).step(hp.x, hp.y); // �����ƶ�
		}
	}
	
	/** ɾ��Խ��ķ�����(���˺��ӵ�) */
	public void outOfBoundsAction(){ //10������һ��
		Iterator<WhitePoint> it = wp.iterator();
		while(it.hasNext()) {
			if(it.next().outOfBounds()) {
				it.remove();
			}
		}
	}
	
	/** Ӣ�۵�����˵���ײ */
	public void heroBangAction(){ //10������һ��
		for (WhitePoint whitePoint : wp) {
			if(whitePoint.hit(hp)) {
				state=GAME_OVER;   //�޸ĵ�ǰ״̬Ϊ��Ϸ����״̬
				hp.state = HeroPoint.DEAD;
				rbtn.setVisible(true);  // ��ʾ��ť
				rlbtn.setVisible(true);
				ebtn.setVisible(true);
				overtime = new Date().getTime(); //����ʱ��
				date.setTime(overtime - starttime);
				System.out.println(fmt.format(date));
				timelbl.setText(fmt.format(date));
				timelbl.setVisible(true);
			}
		}
	}
	
	public void action() {
		sbtn.event();
		rbtn.event();
		rlbtn.event();
		ebtn.event();
		// ��������������
		KeyAdapter k = new KeyAdapter() {
			/** ��дkeyPressed()���̰����¼� */
			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				if(e.getKeyCode()==10){
//					state = RUNNING;
//					hp.state = HeroPoint.LIFE;
				}
				if (e.getKeyCode() == 37)
					hp.left = true;
				if (e.getKeyCode() == 38)
					hp.up = true;
				if (e.getKeyCode() == 39)
					hp.right = true;
				if (e.getKeyCode() == 40)
					hp.down = true;
			}

			/** ��дkeyReleased()�����ͷ��¼� */
			@Override
			public void keyReleased(java.awt.event.KeyEvent e) {
				if (e.getKeyCode() == 37)
					hp.left = false;
				if (e.getKeyCode() == 38)
					hp.up = false;
				if (e.getKeyCode() == 39)
					hp.right = false;
				if (e.getKeyCode() == 40)
					hp.down = false;
			}
		};
		this.addKeyListener(k);

		Timer timer = new Timer(); // ������ʱ������
		timer.schedule(new TimerTask() {
			public void run() { // ÿ10��������һ��--��ʱ�ɵ��Ǹ���
				if (state == RUNNING) { // ����״̬ʱִ��
					enterAction(); // С�׵��볡
					stepAction(); // С�׵��ƶ�
					hp.step(); // Ӣ�۵��ƶ�
					outOfBoundsAction(); // ɾ��Խ���С�׵�
					heroBangAction();   // Ӣ�۵�����˵���ײ
//					checkGameOverAction(); //�����Ϸ����
					repaint(); // �ػ�(���µ���paint()����)
				}
			}
		}, 10, 10); // ��ʱ�ƻ�
	}

	/** ��дpaint()�� */
	public void paint(Graphics g) { // 10������һ��
		super.paint(g); // ���̳и����,��ô��ťҪ��껮��ʱ�Ż���ʾ,��������������ö�������
		if (state != START) { // ��Ϊ��Щ��û��Component,JPanel����addҲ���ử����,����Ҫ�Լ�д
			if(state == RUNNING) {
				bg.paintObject(g); // �Ȼ�����ͼƬ
			}
			hp.paintObject(g); // ��Ӣ�۵�
			for (int i = 0; i < wp.size(); i++) {
				wp.get(i).paintObject(g); // ���׵�
			}
		}
		rbtn.repaint(); // ��Ϊ����������Ҫ�ػ��ٸ��ǻ�ȥ
		rlbtn.repaint();
		ebtn.repaint();
		// timelbl.repaint(); û��Ч��,���ܰ�ǰ��ĸ���
	}

	public World() {
		this.setLayout(null); // ��������˰�ťλ�ô�С���ò���Ч��
		this.add(sbtn);
		this.add(rbtn);
		this.add(rlbtn);
		this.add(ebtn);
		this.add(timelbl);
	}
	
	public StartBtn getSbtn() {
		return sbtn;
	}

	public RestartBtn getRbtn() {
		return rbtn;
	}

	public RankingListBtn getRlbtn() {
		return rlbtn;
	}

	public ExitBtn getEbtn() {
		return ebtn;
	}
	
	public TimeLabel getTimelbl() {
		return this.timelbl;
	}
	
	public void setState(int state) {
		this.state = state;
	}

	public void setHp(HeroPoint hp) {
		this.hp = hp;
	}

	public void setWp(List<WhitePoint> wp) {
		this.wp = wp;
	}
	
	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame(); // ����һ�����ڶ���
		World world = new World(); // ����һ��������
		world.setBackground(new Color(0, 0, 0)); // ������Ч��,paint�ػ���͸�����
		frame.add(world); // �������ӵ�������
		frame.setTitle("huangkai��С��Ϸ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ùرմ���ʱ�˳�����
		frame.setSize(WIDTH, HEIGHT); // ���ô��ڵĴ�С
		frame.setResizable(false); // ���ô�С���ɱ�
		frame.setLocationRelativeTo(null); // ���ô��ھ�����ʾ
		world.setFocusable(true); // ��panel��ý���,ʹ���ܼ��������¼�,Ҫ����setVisibleǰ��
		frame.setVisible(true); // 1)���ô��ڿɼ� 2)�������paint()
		// world.requestFocus();  ��panel��ý���,ʹ���ܼ��������¼�,Ҫ����setVisible����,���ǻ�ʱ��ʱ����,������setFocusable

		world.action();
	}

}
