package model.characters.heroes;

import lombok.Getter;
import lombok.Setter;
import model.Point;
import model.Unit;
import model.artifacts.abstarct.Armor;
import model.artifacts.abstarct.Helm;
import model.artifacts.abstarct.Weapon;


public class Hero extends Unit {
	@Setter
	String name;
	@Getter
	int level;
	int experience;
	int attack;
	int defense;
	int hp = 100;

	@Setter
	private Helm helm;
	@Setter
	private Armor armor;
	@Setter
	private Weapon weapon;

	public Hero() {
		super();
	}
}
