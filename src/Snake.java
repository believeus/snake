//这个是Snake.java**********************************************


import java.awt.Color;

import java.awt.Graphics;

import java.awt.Image;

import java.util.ArrayList;

import java.util.Calendar;

import java.util.GregorianCalendar;

import java.awt.event.*;

import javax.swing.*;

/*

 * Time : 2011.11.24

 * 贪吃蛇 By WUQIWEI

 * 

 * */

public class Snake extends JPanel{
	private static final long serialVersionUID = -7844810652256449836L;
	final int height = 20;
	final int width = 30;
	final int unit = 20;
	private ArrayList<SNode> Snake = new ArrayList<SNode>(); // 存蛇身
	private int Direction = 1; // 存方向
	
	ImageIcon imgico = createImageIcon("images/head.png");
	Image img = imgico.getImage(); // 这个是蛇头像
	
	public int getDirection() {
		return Direction;

	}
	public void setDirection(int direction) {
		Direction = direction;
	}

	public SNode getNewNode() {
		return NewNode;

	}

	public void setNewNode(SNode newNode) {
		NewNode = newNode;
	}

	public int getLength() {
		return Length;
	}

	public void setLength(int length) {
		Length = length;
	}
	
	private SNode NewNode = new SNode(1, 1, Color.BLACK); // 存随机点
	private int Length = 0; // 存蛇长
	Timer timer = new Timer(200, new ActionListener() {

		public void actionPerformed(ActionEvent arg0){
			Move(Direction);
			repaint();
		}
	});
	public Snake(){
		this.Snake.add(new SNode((int) height / 2, (int) width / 2, Color.black));
		this.Snake.add(new SNode((int) height / 2 + 1, (int) width / 2,Color.red));
		this.Snake.add(new SNode((int) height / 2 + 2, (int) width / 2,Color.blue));
		this.Direction = 2;
		CreateNode();
		this.Length = 3;
		timer.start();
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int direction = 0;
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					direction = 1;
					break;
				case KeyEvent.VK_DOWN:
					direction = -1;
					break;
				case KeyEvent.VK_LEFT:
					direction = 2;
					break;
				case KeyEvent.VK_RIGHT:
					direction = -2;
					break;
				}
				// 防止反向 不许加速（加速感觉过于动感）
				if (direction + Direction != 0 && direction != Direction){
					Direction = direction;
					Move(direction);
					repaint();
				}
			}
		});

	}
	// 移动
	public void Move(int dir) {
		timer.stop(); // 感觉在这里不把定时器暂停掉的话键盘事件和定时事件响应间隔很近就显得很假
		int nextX = Snake.get(0).getX();
		int nextY = Snake.get(0).getY();
		switch (dir){
		case 1:
			nextY--;
			break;
		case -1:
			nextY++;
			break;
		case 2:
			nextX--;
			break;
		case -2:
			nextX++;
			break;
		default:
			break;
		}
		// 若碰上食物
		if ((nextX == NewNode.getX()) && (nextY == NewNode.getY())) {
			Eat();
			timer.start();
			return;
		}
		// 越界（测试无误）
		if (nextX > width - 1 || nextX < 0 || nextY > height - 1 || nextY < 0) {
			GameOver("您撞墙啦~~~!"); // 撞墙（ok）
			return;
		}
		// 自撞（测试无误）
		for (int i = 0; i < Length; i++) {
			if ((Snake.get(i).getX() == nextX)&& (Snake.get(i).getY() == nextY)){
				GameOver("您撞到自己啦~~~!");
				return;
			}
		}
		//前一个节点获取上一个节点的位置
		for (int j = Length - 1; j > 0; j--){
			Snake.get(j).setX(Snake.get(j - 1).getX());
			Snake.get(j).setY(Snake.get(j - 1).getY());
		}
		Snake.get(0).setX(nextX);
		Snake.get(0).setY(nextY);
		repaint();
		timer.start();

	}
	// 吃
	public void Eat() {
		SNode nd = new SNode(NewNode.getX(), NewNode.getY(), NewNode.getColor());
		Snake.add(new SNode());
		Length++;
		for (int j = Length - 1; j > 0; j--){
			Snake.get(j).setX(Snake.get(j - 1).getX());
			Snake.get(j).setY(Snake.get(j - 1).getY());
			Snake.get(j).setColor(Snake.get(j - 1).getColor()); 
		}
		Snake.get(0).setX(NewNode.getX());
		Snake.get(0).setY(NewNode.getY());
		Snake.get(0).setColor(NewNode.getColor());
		CreateNode();
		repaint();
	}

	// 产生随机点
	public void CreateNode(){
		boolean flag = true;
		int newX = 0;
		int newY = 0;
		while (flag){
			newX = (int) (Math.random() * (width - 1));
			newY = (int) (Math.random() * (height - 1));
			for (int i = 0; i < Length; i++){
				if ((Snake.get(i).getX() == newX)&& (Snake.get(i).getY() == newY))
					break;
			}
			flag = false;
		}
		// 颜色也随机一下
		Color color = new Color(50 + (int) (Math.random() * 205), 50 + (int) (Math.random() * 205),50 + (int) (Math.random() * 205));
		NewNode.setX(newX);
		NewNode.setY(newY);
		NewNode.setColor(color);
		Snake.get(0).setColor(NewNode.getColor());

	}
	// GameOver
	public void GameOver(String str) {
		Calendar cal = new GregorianCalendar();
		str = str + "\n 点击确定键结束游戏..." + "\n当前时间: " + cal.getTime().toString();
		JOptionPane.showMessageDialog(null, str, "游戏结束 ———————By Mr.Xu", 1);
		System.exit(0);
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(NewNode.getColor()); // 画随机产生的那个点
		g.fillOval(NewNode.getX() * unit, NewNode.getY() * unit, unit, unit);
		g.setColor(NewNode.getColor());
		g.drawRect(0, 0, width * unit, height * unit); // 画大框
		for (int i = 0; i < Length; i++){
			g.setColor(Snake.get(i).getColor());
			g.fillOval(Snake.get(i).getX() * unit, Snake.get(i).getY() * unit,unit, unit);
		}
		g.drawImage(img, Snake.get(0).getX() * unit - 3, Snake.get(0).getY()* unit - 3, unit + 6, unit + 6, this);
	}
	
	protected static ImageIcon createImageIcon(String path){
		java.net.URL imgURL = Snake.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path); // err 红字
			return null;
		}

	}
	

	public static void main(String args[]){
		JFrame frame = new JFrame("贪吃蛇");
		Snake snk = new Snake();
		snk.setBackground(Color.white);
		frame.add(snk);
		frame.setSize(617, 439);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		snk.requestFocus(); // JPanel要响应键盘事件，必须设置焦点

	}

}
