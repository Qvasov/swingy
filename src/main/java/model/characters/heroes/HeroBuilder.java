package model.characters.heroes;

import lombok.Getter;
import model.DataBase;

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

	public void loadHero(String heroName) {
		DataBase.getInstance().getHero(heroName);
	}

	public Warrior createWarrior(String name) {
		return new Warrior(name);
	}

	public Ranger createRanger(String name) {
		return new Ranger(name);
	}
}
