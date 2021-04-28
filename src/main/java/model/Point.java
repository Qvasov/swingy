package model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Point {
	private int x;
	private int y;
	@Setter
	private int prevX;
	@Setter
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
