package model.items.abstarct;

import lombok.Getter;
import model.characters.heroes.Hero;

public abstract class Helm extends Item {
	@Getter
	private int hp;

	protected Helm(int hp) {
		this.hp = hp;
	}

	@Override
	public void equip(Hero hero) {
		hero.setHelm(this);
	}

	@Override
	public String getStats() {
		return String.format("Hit points: %s", hp);
	}

}
