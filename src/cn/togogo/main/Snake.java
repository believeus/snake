package cn.togogo.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Snake extends JPanel {
	private static final long serialVersionUID = 9038861686579200310L;
	private List<Snode> snake = new ArrayList<>();
	private Direction dir;
	private int width = Yard.WIDTH;
	private int height = Yard.HEIGHT - 60;
	private Egg egg;
	private MagicEgg magicEgg;
	private MagicEgg boomEgg;
	private Image headimg;
	private int score;
	private int speed, recoder;

	// 分数显示标签
	private JLabel lbstatus;
	// 游戏规则标签
	private JLabel lbdesc;
	// 圆的直径
	private int unit = 20;

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	private Thread timer;

	public Snake() {
		this.setLayout(null);
		this.score = 10;
		this.speed = recoder = 200;
		/** Begin:分数标签 */
		this.lbstatus = new JLabel("当前速度:" + speed + "m/s 分数:0");
		lbstatus.setLocation(620, height + 10);
		lbstatus.setSize(150, 20);
		this.add(lbstatus);
		/** End:分数标签 */
		/** Begin:游戏说明 */
		this.lbdesc = new JLabel("▲向上移动;▼向下移动;◀向左移动;▶向右移动;空格:暂停");
		lbdesc.setLocation(100, height + 10);
		lbdesc.setSize(350, 20);
		this.add(lbdesc);
		/** End:游戏说明 */
		this.setSize(width, height);
		this.setBackground(Color.WHITE);

		/** Begin 获取蛇头的头像 */
		URL url = Snake.class.getResource("head.png");
		ImageIcon imageIcon = new ImageIcon(url);
		headimg = imageIcon.getImage();
		/** End 获取蛇头的头像 */
		// 每隔200毫秒执行一次move方法
		this.timer = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						Thread.sleep(speed);
						repaint();
						move(Snake.this.dir);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		this.timer.start();
		dir = Direction.LEFT;
		snake.add(new Snode(unit * 25, unit * 25, Color.BLUE));

	}

	public void move(Direction dir) {
		// TODO 防止反向 不许加速（加速感觉过于动感）
		Snode head = snake.get(0);
		int nextX = head.getX();
		int nextY = head.getY();
		switch (dir) {
		case UP:
			nextY -= 20;
			break;
		case DOWN:
			nextY += 20;
			break;
		case LEFT:
			nextX -= 20;
			break;
		case RIGHT:
			nextX += 20;
			break;
		case STOP:
			this.stopmove();
			return;
		}
		// 移动的核心代码
		// 将第三个节点移动到第二个节点的x,y点
		// 将第二个节点移动到第一个节点的x,y点
		for (int i = (snake.size() - 1); i > 0; i--) {
			snake.get(i).setX(snake.get(i - 1).getX());
			snake.get(i).setY(snake.get(i - 1).getY());
		}
		snake.get(0).setX(nextX);
		snake.get(0).setY(nextY);
		this.startmove();
	}

	public void eat() {
		this.score++;
		this.lbstatus.setText("当前速度:" + recoder + " 分数:" + score);
		int size = snake.size();
		int x = snake.get(0).getX();
		int y = snake.get(0).getY();
		Snode snode = new Snode(x + size * unit, y, egg.getColor());
		snake.add(snode);
		boomEgg = null;
		egg = null;
		magicEgg = null;
	}

	/** Begin:吃彩蛋 */
	public void eatMagicEgg() {
		speed -= magicEgg.getSpeed();
		recoder += magicEgg.getSpeed();
		this.lbstatus.setText("当前速度:" + recoder + " 分数:" + score);
		boomEgg = null;
		egg = null;
		magicEgg = null;
	}

	/** End:吃彩蛋 */
	/** 停止移动 */
	@SuppressWarnings("deprecation")
	public void stopmove() {
		timer.suspend();
	}

	/** 开始移动 */
	@SuppressWarnings("deprecation")
	public void startmove() {
		timer.resume();
	}

	// 该功能具有双缓冲功能
	@Override
	protected void paintComponent(final Graphics g) {
		// 不能删除!该功能具有双缓冲功能
		super.paintComponent(g);
		// 边界检测
		/** Begin 画出横线和竖线 */
		// 画横线
		for (int i = 0; i <= (height / unit); i++) {
			g.drawLine(0, unit * i, width, unit * i);
		}
		// 画竖线
		for (int i = 0; i <= (width / unit); i++) {
			g.drawLine(unit * i, 0, unit * i, height);
		}
		/** End 画出横线和竖线 */

		int headX = snake.get(0).getX();
		int headY = snake.get(0).getY();
		/** Begin 边界检测 */
		System.out.println("x=" + headX + " y=" + headY);
		if (headX < -1)
			timer.interrupt();
		else if (headY < -1)
			timer.interrupt();
		else if (headX > (width - 20))
			timer.interrupt();
		else if (headY > (height - 20))
			timer.interrupt();
		/** End 边界检测 */

		/** Begin 让蛇移动起来 */
		for (int i = 0; i < snake.size(); i++) {
			Snode snode = snake.get(i);
			g.setColor(snode.getColor());
			g.fillOval(snode.getX(), snode.getY(), unit, unit);
		}
		/** Begin 设置蛇头:让蛇头覆盖第一个节点 */
		int x = snake.get(0).getX();
		int y = snake.get(0).getY();
		g.drawImage(headimg, x, y, 20, 20, null);
		/** End 设置蛇头:让蛇头覆盖第一个节点 */
		/** End 让蛇移动起来 */

		/** Begin:画蛋蛋,不让蛋蛋和蛇的位置为同一个地方 */
		if (egg == null) {
			egg = new Egg();
		}
		g.setColor(egg.getColor());
		g.fillOval(egg.getX(), egg.getY(), unit, unit);
		g.setColor(Color.BLACK);
		/** End:画蛋蛋,不让蛋蛋和蛇的位置为同一个地方 */

		/** Begin:画彩蛋 */
		if (magicEgg == null) {
			magicEgg = new MagicEgg();
		}
		g.setColor(magicEgg.getColor());
		g.fillOval(magicEgg.getX(), magicEgg.getY(), unit, unit);
		/** End:画彩蛋 */

		/** Begin:画炸弹 */
		if (boomEgg == null) {
			boomEgg = new MagicEgg();
		}
		g.setColor(boomEgg.getColor());
		g.fillOval(boomEgg.getX(), boomEgg.getY(), unit, unit);
		/** End:画炸弹 */
		/** Begin:蛇吃蛋 */
		if (x == egg.getX() && y == egg.getY()) {
			eat();
		}
		/** End:蛇吃蛋 */

		/** Begin:蛇吃彩蛋 */
		if (x == magicEgg.getX() && y == magicEgg.getY()) {
			eatMagicEgg();
		}
		/** End:蛇吃彩蛋 */
		if (x == boomEgg.getX() && y == boomEgg.getY()) {
			eatBoomEgg();
		}
	}

	private void eatBoomEgg() {
		// this.stopmove();
		this.score--;
		if (score < 0) {
			System.exit(0);
		}
		this.speed += 30;
		this.recoder -= 30;
		boomEgg = null;
		egg = null;
		magicEgg = null;
		this.lbstatus.setText("当前速度:" + recoder + " 分数:" + score);

	}
}
