package cn.togogo.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class Yard extends JPanel {
	private static final long serialVersionUID = 8018986092730399936L;
	private Snake snake;
	private Egg egg;
	private Egg quickEgg;
	private int width;
	private int height;
	private boolean isrunning;
	private boolean isclear;
	private Thread timmer;
	

	public Yard() {
		this.isrunning = true;
		this.isclear=false;
		this.width = Variables.WIDTH;
		this.height = Variables.HEIGHT;
		this.setSize(width, height);
		this.setLocation(300, 0);
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		this.egg = new Egg();
		this.quickEgg=new QuickEgg();
		this.snake = new Snake();
		this.timmer = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (true) {
						/**在多线程中套双层循环需要休眠5毫秒,要不然内部循环不起作用*/
						Thread.sleep(5);
						while (isrunning) {
							Thread.sleep(snake.getSpeed());
							repaint();
							snake.move(snake.getDir());
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		timmer.start();
	}

	public void listenKeyPress(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_UP:
			snake.setDir(Direction.UP);
			isrunning = true;
			break;
		case KeyEvent.VK_DOWN:
			snake.setDir(Direction.DOWN);
			isrunning = true;
			break;
		case KeyEvent.VK_LEFT:
			snake.setDir(Direction.LEFT);
			isrunning = true;
			break;
		case KeyEvent.VK_RIGHT:
			snake.setDir(Direction.RIGHT);
			isrunning = true;
			break;
		/** Begin:按空白键暂停 */
		case KeyEvent.VK_SPACE:
			isrunning = false;
			break;
		/** End:按空白键暂停 */
		}
		snake.move(snake.getDir());

	}

	public void draw(Graphics g) {
		// 画横线
		for (int i = 0; i <= (height / 20); i++) {
			g.drawLine(0, 20 * i, width, 20 * i);
		}
		// 画竖线
		for (int i = 0; i <= width / 20; i++) {
			g.drawLine(20 * i, 0, 20 * i, height);
		}
	}

	/** 解决运动拖影问题 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.draw(g);
		snake.draw(g);
		egg = ((egg == null) ? new Egg() : egg);
		egg.draw(g);
		/** 蛇吃到蛋 */
		if (snake.getX() == egg.getX() && snake.getY() == egg.getY()) {
			snake.eat(egg);
			isclear=true;
		}
		/**吃到加速蛋*/
		quickEgg=(quickEgg==null)?new QuickEgg():quickEgg;
		quickEgg.draw(g);
		if (snake.getX() == quickEgg.getX() && snake.getY() == quickEgg.getY()) {
			snake.eat(quickEgg);
			isclear=true;
		}
		/** 撞墙 */
		if (snake.getX() ==0 || snake.getY() ==0 || snake.getX() == width - 20|| snake.getY() > height - 40) {
			isrunning = false;
		}
		if(isclear){
			isclear=false;
			egg=null;
			quickEgg=null;
		}
	}
}
