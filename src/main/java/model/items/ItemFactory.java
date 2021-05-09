package model.items;

import lombok.Getter;
import model.characters.enemies.Enemy;
import model.items.abstarct.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemFactory {
	private static ItemFactory itemFactory = new ItemFactory();
	private Random random = new Random();
	@Getter
	private List<String> items = new ArrayList<>();

	private ItemFactory() {
		items.add("Sword");
		items.add("Plate");
		items.add("Bow");
		items.add("Mail");
		items.add("Light");
	}

	public static ItemFactory getInstance() {
		return itemFactory;
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
}
