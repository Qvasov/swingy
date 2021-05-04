package model.characters.enemies;

import java.util.Random;

public class EnemyBuilder {
	private static final EnemyBuilder builder = new EnemyBuilder();
	private Random random = new Random();

	private EnemyBuilder() {
	}

	public static EnemyBuilder getInstance() {
		return builder;
	}

	public Enemy createBandit(int level) {
		Enemy enemy = new Bandit(level);
		enemy.setItem(null);
		return enemy;
	}
}
