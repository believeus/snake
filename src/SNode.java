

import java.awt.*;

public class SNode extends Object

{

	private int x;

	private int y;

	private Color color;

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

	public SNode()

	{

		x = 0;

		y = 0;

		color = Color.white;

	}

	public SNode(int x, int y, Color clr)

	{

		this.x = x;

		this.y = y;

		this.color = clr;

	}

}