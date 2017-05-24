package cn.togogo.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setLocation(200, 100);
	    int width=Variables.WIDTH;
	    int height=Variables.HEIGHT+20;
	    frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final Yard yard = new Yard();
		
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				yard.listenKeyPress(e);
			}
		});
		frame.add(yard);
		frame.setVisible(true);
	}
}
