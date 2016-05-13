package cn.believeus.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Snake {
	private Node head = null;
	private Node n = new Node(Yard.distance , Yard.distance , Dir.right);

	public Snake() {
		head = n;
	}

	public void keypress(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_UP:
			n.dir=Dir.up;
			break;
		case KeyEvent.VK_DOWN:
			n.dir=Dir.down;
			break;
		case KeyEvent.VK_LEFT:
			n.dir=Dir.left;
			break;
		case KeyEvent.VK_RIGHT:
			n.dir=Dir.right;
			break;

		}
	}

	public void draw(Graphics g) {
		for (Node n = head; n != null; n = n.next) {
			n.draw(g);
		}
		move();
	}
	
	public void move() {
		switch (n.dir) {
		case left:
			n.row -= Yard.distance;
			break;
		case right:
			n.row += Yard.distance;
			break;
		case up:
			n.col -= Yard.distance;
			break;
		case down:
			n.col += Yard.distance;
			break;
		}
	}

	private class Node {
		int w = Yard.distance;
		int h = Yard.distance;
		int row, col;
		Dir dir;
		Node next;

		public Node(int row, int col, Dir dir) {
			this.row = row;
			this.col = col;
			this.dir = dir;
		}

		public void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.GRAY);
			g.fillRect(row, col, w, h);
			g.setColor(c);
		}
	}

}
