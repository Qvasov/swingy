package model;

import lombok.Getter;

import javax.swing.*;
import java.util.Random;

@Getter
public abstract class Unit {
	private static Random random = new Random();
	private Point position;
	private String name;
	private int minAttack;
	private int maxAttack;
	private int defence;
	private int curHp;
	private int hp;
	private Icon icon;

	//TODO воткнуть NOT NULL
	protected Unit(String name, int minAttack, int maxAttack, int defence, int hp) {
		this.position = new Point();
		this.name = name;
		this.minAttack = minAttack;
		this.maxAttack = maxAttack;
		this.defence = defence;
		this.hp = hp;
		this.curHp = this.hp;
		this.icon = IconStorage.downloadImage(this.getClass().getSimpleName());
	}

	public int dealDamage() {
		return getMinAttack() + random.nextInt(getMaxAttack() - getMinAttack());
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
		this.minAttack += addAttack;
		this.maxAttack += addAttack;
		this.defence += addDefence;
		this.hp += addHp;
	}

	public abstract String getSymbol();
}
