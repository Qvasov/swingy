package model.characters.enemies;

import lombok.Getter;
import lombok.Setter;
import model.Unit;
import model.artifacts.Item;

@Getter
public abstract class Enemy extends Unit {
	private int exp;
	@Setter
	private Item item;

	protected Enemy(String name, int attack, int defence, int hp, int exp) {
		super(name, attack, defence, hp);
		this.exp = exp;
	}
}
