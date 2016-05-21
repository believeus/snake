package cn.togogo.main;

import java.awt.Color;

public class Egg {
	protected int x;
	protected int y;
	protected Color color;
	
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
	public Egg(){
		x=Math.abs(((int)(Math.random()*10)-2)*100);
		y=Math.abs(((int)(Math.random()*8)-2)*100);
		//颜色随机
		color = new Color(50 + (int) (Math.random() * 205), 50 + (int) (Math.random() * 205),50 + (int) (Math.random() * 205));
	}

	
	
}
