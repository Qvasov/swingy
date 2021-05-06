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
		items.add("Mail");
		items.add("Light");
	}

	public static ItemFactory getInstance() {
		return itemFactory;
	}

	public Item generateItem(Enemy enemy) {
		Item item = null;
		if (random.nextInt(4) == 0) {
			int itemLvl = enemy.getAttack() + enemy.getDefence() + enemy.getHp();
			String itemName = items.get(random.nextInt(items.size()));
			switch (itemName) {
				case "Sword":
					item = new Sword(itemLvl / 10);
					break;
				case "Mail":
					item = new Mail(itemLvl / 10);
					break;
				case "Light":
					item = new Light(itemLvl / 5);
					break;
			}
		}
		return item;
	}
}
