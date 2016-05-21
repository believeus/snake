package cn.togogo.main;

public enum Direction {
	UP(1), LEFT(2), RIGHT(-2), DOWN(-1);
	private int value;

	public int value() {
		return this.value;
	}

	private Direction(int value) {
		this.value = value;
	}

	// 手写的从int到enum的转换函数
	public static Direction valueOf(int value) {
		switch (value) {
		case 1:
			return UP;
		case 2:
			return LEFT;
		case -1:
			return DOWN;
		case -2:
			return RIGHT;
		default:
			return null;
		}
	}
}
