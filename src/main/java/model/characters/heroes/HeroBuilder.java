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
		return null; //TODO Exception
	}

	public Hero createHero(String heroClass, String heroName) {
		switch (heroClass) {
			case "Warrior":
				return createWarrior(heroName);
			case "Ranger":
				return createRanger(heroName);
		}
		return null; //TODO Exception
	}

	public void loadHero(String heroName) {
		DataBase.getInstance().getHero(heroName);
	}

	public Warrior createWarrior(String name) {
		Warrior warrior = new Warrior(name);
		return warrior;
	}

	public Ranger createRanger(String name) {
		Ranger ranger = new Ranger(name);
		return ranger;
	}
}
