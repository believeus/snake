package cn.togogo.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Egg {
	protected int x;
	protected int y;
	protected Color color;
	protected boolean isLive;

	public Egg() {
		Random random = new Random();
		// x,y坐标随机
		x = random.nextInt(40) * 20;
		y = random.nextInt(30) * 20;
		// 颜色随机
		color = new Color(50 + (int) (Math.random() * 205),
				50 + (int) (Math.random() * 205),
				50 + (int) (Math.random() * 205));
		this.isLive = true;
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(this.color);
		g.fillOval(x, y, 20, 20);
		g.setColor(c);
	}

}
