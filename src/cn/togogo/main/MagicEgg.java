package cn.togogo.main;

import java.awt.Color;

public class MagicEgg extends Egg{
	private int speed;
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public MagicEgg(){
		this.x=Math.abs(((int)(Math.random()*10)-2)*100);
		this.y=Math.abs(((int)(Math.random()*8)-2)*100);
		//颜色随机
		this.color = new Color(50 + (int) (Math.random() * 205), 50 + (int) (Math.random() * 205),50 + (int) (Math.random() * 205));
		this.speed=((int)(Math.random()*20));
	}

}
