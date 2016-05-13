package cn.believeus.main;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Yard extends Frame {
	private static final long serialVersionUID = 8956944163072609691L;
	public static int width = 600;
	public static int height = 400;
	public static int distance = 15;
	Snake snake = new Snake();

	public Yard() {
		this.setSize(width, height);
		this.setLocation(500, 200);
		this.setResizable(true);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				snake.keypress(e);
			}
		});
		this.setVisible(true);

	}

	@Override
	public void paint(final Graphics g) {
		Color color = g.getColor();
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width, height);
		// 画横线
		for (int i = 0; i <= (height / 15); i++) {
			g.drawLine(0, distance * i, width, distance * i);
		}
		// 画竖线
		for (int i = 0; i <= width / 15; i++) {
			g.drawLine(distance * i, 0, distance * i, height);
		}
		snake.draw(g);
		g.setColor(color);
	}

	public void lauch() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						repaint();//会调用paint方法
						Thread.sleep(400);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

}
