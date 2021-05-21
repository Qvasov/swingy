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
		super(name, defaultStats.get("level"), defaultStats.get("experience"), defaultStats.get("minAttack"),
				defaultStats.get("maxAttack"), defaultStats.get("defence") , defaultStats.get("hp"));
	}

	public Warrior(String name, int level, int exp, int minAttack, int maxAttack, int defence, int hp) {
		super(name, level, exp, minAttack, maxAttack, defence, hp);
	}

	@Override
	public void levelUp() {
		this.level++;
		increaseStats(2, 2, 5);
	}
}
