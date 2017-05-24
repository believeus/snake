package cn.togogo.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Snake {
	private List<Snode> snake = new ArrayList<>();
	private int x;
	private int y;
	private int speed;
	private Direction dir;
	private Image headimg;

	public List<Snode> getSnake() {
		return snake;
	}

	public void setSnake(List<Snode> snake) {
		this.snake = snake;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Image getHeadimg() {
		return headimg;
	}

	public void setHeadimg(Image headimg) {
		this.headimg = headimg;
	}

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	public Snake() {
		/** Begin 获取蛇头的头像 */
		ImageIcon imageIcon = new ImageIcon(Snake.class.getResource("head.png"));
		this.headimg = imageIcon.getImage();
		/** End 获取蛇头的头像 */
		this.dir = Direction.LEFT;
		this.speed = 200;
		this.x = 300;
		this.y = 300;
		snake.add(new Snode(x, y, Color.BLUE));
		snake.add(new Snode(x + 1, y, Color.BLACK));
	}

	public void draw(Graphics g) {
		/** Begin 让蛇移动起来 2 */
		for (int i = 0; i < snake.size(); i++) {
			g.setColor(snake.get(i).getColor());
			g.fillOval(snake.get(i).getX(), snake.get(i).getY(), 20, 20);
		}
		/** Begin 设置蛇头:让蛇头覆盖第一个节点 */
		int x = snake.get(0).getX();
		int y = snake.get(0).getY();
		g.drawImage(headimg, x, y, 20, 20, null);
		/** End 让蛇移动起来 */
	}

	public void move(Direction dir) {
		switch (dir) {
		case UP:
			this.y -= 20;
			break;
		case DOWN:
			this.y += 20;
			break;
		case LEFT:
			this.x -= 20;
			break;
		case RIGHT:
			this.x += 20;
			break;
		}
		// 设置前一个节点获取上一个节点的位置
		for (int j = snake.size() - 1; j > 0; j--) {
			snake.get(j).setX(snake.get(j - 1).getX());
			snake.get(j).setY(snake.get(j - 1).getY());
		}
		// 给头设置一个新的位置
		snake.get(0).setX(this.x);
		snake.get(0).setY(this.y);
	}

	public void eat(Egg egg) {
		egg.setLive(false);
		Snode snode = new Snode(egg.getX(), egg.getY(), egg.getColor());
		snake.add(snode);
	}
}
