package model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Point {
	private int x;
	private int y;
	private int oldX;
	private int oldY;

	public void setX(int x) {
		this.oldX = this.x;
		this.x = x;
	}

	public void setY(int y) {
		this.oldY = this.y;
		this.y = y;
	}
}
