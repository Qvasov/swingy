package model.characters.enemies;

import lombok.Getter;
import model.Unit;

@Getter
public abstract class Enemy extends Unit {
	private int exp;

	protected Enemy(String name, int minAttack, int maxAttack, int defence, int hp, int exp) {
		super(name, minAttack, maxAttack, defence, hp);
		this.exp = exp;
	}
}
