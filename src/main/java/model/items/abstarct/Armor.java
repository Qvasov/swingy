package model.items.abstarct;

import lombok.Getter;
import model.characters.heroes.Hero;

public abstract class Armor extends Item {
	@Getter
	private int defence;

	protected Armor(int defence) {
		this.defence = defence;
	}

	@Override
	public void equip(Hero hero) {
		hero.setArmor(this);
	}

	protected void addClassBonus(int bonus) {
		defence += bonus;
	}

	@Override
	public String getStats() {
		return String.format("Defence: %d", defence);
	}
}
