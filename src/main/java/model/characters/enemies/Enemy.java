package model.characters.enemies;

import lombok.Setter;
import model.Point;
import model.Unit;
import model.artifacts.Item;
import model.characters.Character;

public class Enemy extends Unit {
	private int attack;
	private int defense;
	private int hp = 100;

	private Item item;

	protected Enemy() {
		super();
	}
}
