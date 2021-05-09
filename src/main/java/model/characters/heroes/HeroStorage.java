package model.characters.heroes;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class HeroStorage {
	private static final HeroStorage storage = new HeroStorage();
	@Getter
	private Map<String, Class<? extends Hero>> heroClasses = new HashMap<>();

	private HeroStorage() {
		download();
	}

	public static HeroStorage getInstance() {
		return storage;
	}

	public void download() {
//		heroClasses.put(Warrior.class.getSimpleName(), Warrior.getDefaultStats());
//		heroClasses.put(Ranger.class.getSimpleName(), Ranger.getDefaultStats());
	}
}
