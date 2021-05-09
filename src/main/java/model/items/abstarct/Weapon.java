package model.items.abstarct;

import lombok.Getter;
import model.characters.heroes.Hero;

public abstract class Weapon extends Item {
	@Getter
	private int attack;

	protected Weapon(int attack) {
		this.attack = attack;
	}

	@Override
	public void equip(Hero hero) {
		hero.setWeapon(this);
	}

	protected void addClassBonus(int bonus) {
		attack += bonus;
	}

	@Override
	public String getStats() {
		return String.format("Attack: %d", attack);
	}
}
