package cn.togogo.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class Yard extends JFrame {
	private static final long serialVersionUID = 8018986092730399936L;
	private Snake snake;

	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;

	public Yard() {
		this.setSize(WIDTH, HEIGHT);
		this.setLocation(300, 0);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.snake = new Snake();

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				switch (keyCode) {
				case KeyEvent.VK_UP:
					snake.setDir(Direction.UP);
					break;
				case KeyEvent.VK_DOWN:
					snake.setDir(Direction.DOWN);
					break;
				case KeyEvent.VK_LEFT:
					snake.setDir(Direction.LEFT);
					break;
				case KeyEvent.VK_RIGHT:
					snake.setDir(Direction.RIGHT);
					break;
				/**Begin:按空白键暂停*/
				case KeyEvent.VK_SPACE:
					snake.setDir(Direction.STOP);
					break;
				/**End:按空白键暂停*/
				}
				snake.move(snake.getDir());
			}

		});
		this.add(snake);
	}

	public void lauch() {
		this.setVisible(true);
	}
}
