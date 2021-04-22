package model;

import lombok.Getter;

public class Point {
	@Getter
	private int x;
	@Getter
	private int y;
	private int prevX;
	private int prevY;

	public void setX(int x) {
		this.prevX = this.x;
		this.x = x;
	}

	public void setY(int y) {
		this.prevY = this.y;
		this.y = y;
	}

	public void setPrevX() {
		this.x = this.prevX;
	}
	public void setPrevY() {
		this.y = prevY;
	}
}
