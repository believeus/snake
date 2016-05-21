package cn.togogo.main;

import java.awt.Color;

//吃到蛇会加速或者减速
public class QuickEgg extends Egg{
	private int speed;
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public QuickEgg(){
		super();
		this.speed=((int)(Math.random()*10)-5);
		this.color = new Color(50 + (int) (Math.random() * 205),50 + (int) (Math.random() * 205),50 + (int) (Math.random() * 205));
	}

}
