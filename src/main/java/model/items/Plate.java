package model.items;

import model.characters.heroes.Hero;
import model.characters.heroes.Warrior;
import model.items.abstarct.Armor;

public class Plate extends Armor {
	private final int classBonus = 5;

	@Override
	public void equip(Hero hero) {
		if (hero instanceof Warrior) {
			addClassBonus(classBonus);
		}
		super.equip(hero);
	}

	protected Plate(int defence) {
		super(defence);
	}

	@Override
	public String getStats() {
		return super.getStats() + " (+" + classBonus + " for Warrior)";
	}
}
