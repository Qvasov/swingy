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
		put("minAttack", 22);
		put("maxAttack", 25);
		put("defence", 8);
		put("hp", 100);
	}};

	public Warrior(String name) {
		super(defaultStats.get("level"), defaultStats.get("experience"), name, defaultStats.get("minAttack"),
				defaultStats.get("maxAttack"), defaultStats.get("defence") , defaultStats.get("hp"));
	}

	@Override
	public void levelUp() {
		this.level++;
		increaseStats(2, 2, 5);
	}
}
