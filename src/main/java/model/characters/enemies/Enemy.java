package model.characters.enemies;

import lombok.Getter;
import model.Unit;
import model.artifacts.Item;

public abstract class Enemy extends Unit {
	@Getter
	private int exp;
	private Item item;

	protected Enemy(String name, int attack, int defence, int hp, int exp) {
		super(name, attack, defence, hp);
		this.exp = exp;
	}
}
