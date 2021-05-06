package model.items.abstarct;

import lombok.Getter;
import model.IconStorage;
import model.characters.heroes.Hero;

import javax.swing.*;

@Getter
public abstract class Item {
	private Icon icon;

	protected Item() {
		this.icon = IconStorage.downloadImage(this.getClass().getSimpleName());
	}

	public abstract void equip(Hero hero);

	public abstract String getStats();
}
