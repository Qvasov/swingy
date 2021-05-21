package model.items;

import lombok.Getter;
import model.characters.enemies.Enemy;
import model.items.abstarct.Item;
import model.items.abstarct.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemBuilder {
	private static ItemBuilder itemBuilder = new ItemBuilder();
	private Random random = new Random();
	@Getter
	private List<String> items = new ArrayList<>();

	private ItemBuilder() {
		items.add("Sword");
		items.add("Plate");
		items.add("Bow");
		items.add("Mail");
		items.add("Light");
	}

	public static ItemBuilder getInstance() {
		return itemBuilder;
	}

	public Item generateItem(Enemy enemy) {
		Item item = null;
		int bonus = 0;
		if (random.nextInt(4) == 0) {
			int itemLvl = enemy.getMinAttack() + enemy.getDefence() + enemy.getHp();
			String itemName = items.get(random.nextInt(items.size()));

			if (random.nextInt(3) == 0) {
				bonus = 4;
				if (random.nextInt(2) == 0) {
					bonus *= 2;
					if (random.nextInt(2) == 0) {
						bonus *= 2;
					}
				}
			}
			switch (itemName) {
				case "Sword":
					item = new Sword(itemLvl / 20 + bonus);
					break;
				case "Bow":
					item = new Bow(itemLvl / 20 + bonus);
					break;
				case "Plate":
					item = new Plate(itemLvl / 20 + bonus);
					break;
				case "Mail":
					item = new Mail(itemLvl / 20 + bonus);
					break;
				case "Light":
					item = new Light(itemLvl / 15 + bonus);
					break;
			}
		}
		return item;
	}

	public Item createItem(String itemName, int itemStat) {
		switch (itemName) {
			case "Sword":
				return new Sword(itemStat);
			case "Bow":
				return new Bow(itemStat);
			case "Plate":
				return new Plate(itemStat);
			case "Mail":
				return new Mail(itemStat);
			case "Light":
				return new Light(itemStat);
			default:
				return null;
		}
	}
}
