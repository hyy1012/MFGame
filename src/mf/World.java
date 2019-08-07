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
	 * 版本1
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 800; // 窗口的宽
	public static final int HEIGHT = 700; // 窗口的高

	public static final int START = 0; // 启动状态
	public static final int RUNNING = 1; // 运行状态
	public static final int GAME_OVER = 3; // 游戏结束状态
	private int state = START; // 当前状态(默认为启动状态)

	private BGpng bg = new BGpng(); // 背景图片
	private HeroPoint hp = new HeroPoint(); // 创建英雄点
	private List<WhitePoint> wp = new ArrayList<WhitePoint>(); // 创建白点
	
	private StartBtn sbtn = new StartBtn(this); // 创建开始按钮
	private RestartBtn rbtn = new RestartBtn(this); // 创建重新开始按钮
	private RankingListBtn rlbtn = new RankingListBtn(this); // 创建查询排行榜按钮
	private ExitBtn ebtn = new ExitBtn(this); // 创建退出按钮
	private TimeLabel timelbl = new TimeLabel();
	
	private long starttime; // 开始时间
	private long overtime; // 结束时间
	private Date date = new Date();
	private SimpleDateFormat fmt= new SimpleDateFormat("你坚持了:  mm 分 ss 秒"); // 格式化日期

	/** 创建白点对象 */
	public void nextOne() {
		wp.add(new WhitePoint(hp.x, hp.y));
	}

	int enterIndex = 0; // 敌人入场计数

	/** 小白点入场 */
	public void enterAction() { // 10毫秒走一次
		enterIndex++; // 每10毫秒增1
		if (wp.size() <= 60 && enterIndex % 10 == 0) { // 每100(10*10)毫秒走一次
			nextOne();
		}
	}

	/** 小白点移动 */
	public void stepAction() { // 10毫秒走一次
		for (int i = 0; i < wp.size(); i++) { // 遍历所有敌人
			wp.get(i).step(hp.x, hp.y); // 敌人移动
		}
	}
	
	/** 删除越界的飞行物(敌人和子弹) */
	public void outOfBoundsAction(){ //10毫秒走一次
		Iterator<WhitePoint> it = wp.iterator();
		while(it.hasNext()) {
			if(it.next().outOfBounds()) {
				it.remove();
			}
		}
	}
	
	/** 英雄点与敌人的碰撞 */
	public void heroBangAction(){ //10毫秒走一次
		for (WhitePoint whitePoint : wp) {
			if(whitePoint.hit(hp)) {
				state=GAME_OVER;   //修改当前状态为游戏结束状态
				hp.state = HeroPoint.DEAD;
				rbtn.setVisible(true);  // 显示按钮
				rlbtn.setVisible(true);
				ebtn.setVisible(true);
				overtime = new Date().getTime(); //计算时间
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
		// 创建侦听器对象
		KeyAdapter k = new KeyAdapter() {
			/** 重写keyPressed()键盘按下事件 */
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

			/** 重写keyReleased()键盘释放事件 */
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

		Timer timer = new Timer(); // 创建定时器对象
		timer.schedule(new TimerTask() {
			public void run() { // 每10个毫秒走一次--定时干的那个事
				if (state == RUNNING) { // 运行状态时执行
					enterAction(); // 小白点入场
					stepAction(); // 小白点移动
					hp.step(); // 英雄点移动
					outOfBoundsAction(); // 删除越界的小白点
					heroBangAction();   // 英雄点与敌人的碰撞
//					checkGameOverAction(); //检测游戏结束
					repaint(); // 重画(重新调用paint()方法)
				}
			}
		}, 10, 10); // 定时计划
	}

	/** 重写paint()画 */
	public void paint(Graphics g) { // 10毫秒走一次
		super.paint(g); // 不继承父类的,那么按钮要鼠标划过时才会显示,这个会把组件及设置都画出来
		if (state != START) { // 因为这些类没有Component,JPanel不能add也不会画他们,所以要自己写
			if(state == RUNNING) {
				bg.paintObject(g); // 先画背景图片
			}
			hp.paintObject(g); // 画英雄点
			for (int i = 0; i < wp.size(); i++) {
				wp.get(i).paintObject(g); // 画白点
			}
		}
		rbtn.repaint(); // 因为覆盖了所以要重画再覆盖回去
		rlbtn.repaint();
		ebtn.repaint();
		// timelbl.repaint(); 没有效果,不能把前面的覆盖
	}

	public World() {
		this.setLayout(null); // 这个设置了按钮位置大小设置才有效果
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
		JFrame frame = new JFrame(); // 创建一个窗口对象
		World world = new World(); // 创建一个面板对象
		world.setBackground(new Color(0, 0, 0)); // 本来有效果,paint重画后就覆盖了
		frame.add(world); // 将面板添加到窗口中
		frame.setTitle("huangkai的小游戏");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置关闭窗口时退出程序
		frame.setSize(WIDTH, HEIGHT); // 设置窗口的大小
		frame.setResizable(false); // 设置大小不可变
		frame.setLocationRelativeTo(null); // 设置窗口居中显示
		world.setFocusable(true); // 给panel获得焦点,使其能监听键盘事件,要放在setVisible前面
		frame.setVisible(true); // 1)设置窗口可见 2)尽快调用paint()
		// world.requestFocus();  给panel获得焦点,使其能监听键盘事件,要放在setVisible后面,但是会时灵时不灵,所以用setFocusable

		world.action();
	}

}
