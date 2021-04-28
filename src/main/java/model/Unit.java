package model;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
public abstract class Unit {
	private Point position;
	private String name;
	private int attack;
	private int defence;
	@Setter
	private int hp;

	public Unit() {
		this.position = new Point();
	}

	public int dealDamage() {
		return getAttack();
	}

	public int receiveDamage(int damage) {
		damage -= defence;
		hp -= damage;
		return damage;
	}

	public boolean isDead() {
		return hp <= 0;
	}
}
