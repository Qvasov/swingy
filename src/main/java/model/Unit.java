package model;

import lombok.Getter;

import javax.swing.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Random;

@Getter
public abstract class Unit {
	private static Random random = new Random();
	private Point position;
	@NotNull(message = "Can not be null")
	@Size(min = 1, max = 20, message = "Length must be from 1 to 20 symbols")
	private String name;
	@Positive(message = "Can not be negative")
	private int minAttack;
	@Positive(message = "Can not be negative")
	private int maxAttack;
	@Positive(message = "Can not be negative")
	private int defence;
	@Positive(message = "Can not be negative")
	private int curHp;
	@Positive(message = "Can not be negative")
	private int hp;
	private Icon icon;

	protected Unit(String name, int minAttack, int maxAttack, int defence, int hp) {
		this.position = new Point();
		this.name = name;
		this.minAttack = minAttack;
		this.maxAttack = maxAttack;
		this.defence = defence;
		this.hp = hp;
		this.curHp = this.hp;
		this.icon = IconStorage.getInstance().downloadImage(this.getClass().getSimpleName());
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
