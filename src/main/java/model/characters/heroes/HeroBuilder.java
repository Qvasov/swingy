package model.characters.heroes;

import lombok.Getter;
import model.DataBase;
import model.items.ItemBuilder;
import model.items.abstarct.Armor;
import model.items.abstarct.Helm;
import model.items.abstarct.Weapon;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HeroBuilder {
	private static final HeroBuilder heroBuilder = new HeroBuilder();
	@Getter
	private final Set<String> heroes = new HashSet<String>() {{
		add(Warrior.class.getSimpleName());
		add(Ranger.class.getSimpleName());
	}};

	private HeroBuilder() {
	}

	public static HeroBuilder getInstance() {
		return heroBuilder;
	}

	public Map<String, Integer> getStats(String heroClass) {
		switch (heroClass) {
			case "Warrior":
				return Warrior.getDefaultStats();
			case "Ranger":
				return Ranger.getDefaultStats();
		}
		return null;
	}

	public Hero createHero(String heroClass, String heroName) {
		if (heroClass == null) {
			throw new IllegalArgumentException("Unknown hero class");
		}
		switch (heroClass) {
			case "Warrior":
				return createWarrior(heroName);
			case "Ranger":
				return createRanger(heroName);
		}
		throw new IllegalArgumentException("Unknown hero class");
	}

	public Hero loadHero(String heroName) {
		Map<String, String> stats = DataBase.getInstance().getHeroStats(heroName);
		if (stats == null) {
			throw new IllegalArgumentException("Unknown hero name");
		}
		int level = Integer.parseInt(stats.get("level"));
		int exp = Integer.parseInt(stats.get("experience"));
		int minAttack = Integer.parseInt(stats.get("minAttack"));
		int maxAttack = Integer.parseInt(stats.get("maxAttack"));
		int defence = Integer.parseInt(stats.get("defence"));
		int hp = Integer.parseInt(stats.get("hp"));
		Hero hero;
		switch (stats.get("class")) {
			case "Warrior":
				hero =  createWarrior(heroName, level, exp, minAttack, maxAttack, defence, hp);
				break;
			case "Ranger":
				hero = createRanger(heroName, level, exp, minAttack, maxAttack, defence, hp);
				break;
			default:
				throw new IllegalArgumentException("Unknown hero class");
		}

		if (!stats.get("weapon").equals("null")) {
			hero.setWeapon((Weapon) ItemBuilder.getInstance().createItem(stats.get("weapon"),
					Integer.parseInt(stats.get("weaponStat"))));
		}
		if (!stats.get("armor").equals("null")) {
			hero.setArmor((Armor) ItemBuilder.getInstance().createItem(stats.get("armor"),
					Integer.parseInt(stats.get("armorStat"))));
		}
		if (!stats.get("helm").equals("null")) {
			hero.setHelm((Helm) ItemBuilder.getInstance().createItem(stats.get("helm"),
					Integer.parseInt(stats.get("helmStat"))));
		}

		return hero;
	}

	public Warrior createWarrior(String name) {
		return new Warrior(name);
	}

	public Warrior createWarrior(String name, int level, int exp, int minAttack, int maxAttack, int defence, int hp) {
		return new Warrior(name, level, exp, minAttack, maxAttack, defence, hp);
	}

	public Ranger createRanger(String name) {
		return new Ranger(name);
	}

	public Ranger createRanger(String name, int level, int exp, int minAttack, int maxAttack, int defence, int hp) {
		return new Ranger(name, level, exp, minAttack, maxAttack, defence, hp);
	}
}
