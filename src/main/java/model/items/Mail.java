package model.items;

import model.characters.heroes.Hero;
import model.characters.heroes.Ranger;
import model.items.abstarct.Armor;

public class Mail extends Armor {
	private final int classBonus = 5;

	@Override
	public void equip(Hero hero) {
		if (hero instanceof Ranger) {
			addClassBonus(classBonus);
		}
		super.equip(hero);
	}

	protected Mail(int defence) {
		super(defence);
	}

	@Override
	public String getStats() {
		return super.getStats() + " (+" + classBonus + " for Ranger)";
	}
}
