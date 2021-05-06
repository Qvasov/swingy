package model.items.abstarct;

import lombok.Getter;
import model.characters.heroes.Hero;

public abstract class Weapon extends Item {
	@Getter
	private final int attack;

	protected Weapon(int attack) {
		this.attack = attack;
	}

	@Override
	public void equip(Hero hero) {
		hero.setWeapon(this);
	}

	@Override
	public String getStats() {
		return String.format("Attack: %d", attack);
	}
}
