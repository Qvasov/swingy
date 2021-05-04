package model.characters.heroes;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Warrior extends Hero {
	@Getter
	private static final Map<String, Integer> defaultStats = new HashMap<String, Integer>()
	{{
		put("level", 0);
		put("experience", 0);
		put("attack", 35);
		put("defence", 20);
		put("hp", 100);
	}};

	public Warrior(String name) {
		super(name, defaultStats.get("attack"), defaultStats.get("defence") , defaultStats.get("hp"));
	}

	@Override
	public void levelUp() {
		this.level++;
		increaseStats(2, 2, 10);
	}
}
