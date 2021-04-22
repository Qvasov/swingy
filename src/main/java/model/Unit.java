package model;

import lombok.Getter;

public abstract class Unit {
	@Getter
	private Point position;

	public Unit() {
		this.position = new Point();
	}
}
