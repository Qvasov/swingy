package model;

import lombok.Getter;

import javax.swing.*;

@Getter
public abstract class Unit {
	private Point position;
	private String name;
	private int attack;
	private int defence;
	private int curHp;
	private int hp;
	private Icon icon;

	//TODO воткнуть NOT NULL
	protected Unit(String name, int attack, int defence, int hp) {
		this.position = new Point();
		this.name = name;
		this.attack = attack;
		this.defence = defence;
		this.hp = hp;
		this.curHp = this.hp;
		this.icon = IconStorage.downloadImage(this.getClass().getSimpleName());
	}

	public int dealDamage() {
		return getAttack();
	}

	public int receiveDamage(int damage) {
		damage -= defence;
		if (damage <= 0) {
			damage = 1;
		}
		curHp -= damage;
		return damage;
	}

	public void recoveryHp(int hp) {
		this.curHp = hp;
	}

	public boolean isDead() {
		return curHp <= 0;
	}

	public void increaseStats (int addAttack, int addDefence, int addHp) {
		this.attack += addAttack;
		this.defence += addDefence;
		this.hp += addHp;
	}
}
