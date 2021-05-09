package model.items;

import model.characters.heroes.Hero;
import model.characters.heroes.Ranger;
import model.items.abstarct.Weapon;

public class Bow extends Weapon {
	private final int classBonus = 5;

	protected Bow(int attack) {
		super(attack);
	}

	@Override
	public void equip(Hero hero) {
		if (hero instanceof Ranger) {
			addClassBonus(classBonus);
		}
		super.equip(hero);
	}

	@Override
	public String getStats() {
		return super.getStats() + " (+" + classBonus + " for Ranger)";
	}
}
