package model.characters.heroes;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Ranger extends Hero {
	@Getter
	private static final Map<String, Integer> defaultStats = new HashMap<String, Integer>()
	{{
		put("level", 0);
		put("experience", 0);
		put("minAttack", 22);
		put("maxAttack", 24);
		put("defence", 18);
		put("hp", 100);
	}};

	public Ranger(String name) {
		super(defaultStats.get("level"), defaultStats.get("experience"), name, defaultStats.get("minAttack"),
				defaultStats.get("maxAttack"), defaultStats.get("defence") , defaultStats.get("hp"));
	}

	@Override
	public void levelUp() {
		this.level++;
		increaseStats(2, 2, 10);
	}
}
