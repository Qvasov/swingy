package model.items;

import model.characters.heroes.Hero;
import model.characters.heroes.Warrior;
import model.items.abstarct.Weapon;

public class Sword extends Weapon {
	private final int classBonus = 5;

	protected Sword(int attack) {
		super(attack);
	}

	@Override
	public void equip(Hero hero) {
		if (hero instanceof Warrior) {
			addClassBonus(classBonus);
		}
		super.equip(hero);
	}

	@Override
	public String getStats() {
		return super.getStats() + " (+" + classBonus + " for Warrior)";
	}
}
