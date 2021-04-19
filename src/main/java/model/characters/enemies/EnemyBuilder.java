package model.characters.enemies;

public class EnemyBuilder {
	private static final EnemyBuilder builder = new EnemyBuilder();

	private EnemyBuilder() {
	}

	public static EnemyBuilder getInstance() {
		return builder;
	}


	public Enemy createEnemy() {
		return new Enemy();
	}
}
