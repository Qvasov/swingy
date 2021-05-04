package model;

import lombok.Getter;
import model.characters.enemies.Enemy;
import model.characters.enemies.EnemyBuilder;
import model.characters.heroes.Hero;

import java.util.HashMap;
import java.util.Random;

public class Map {
	private static final float ENEMIES_COUNT_MODIFIER = 0.3f;
	private final Random random;
	@Getter
	private final int size;
	@Getter
	private HashMap<Integer, Unit>[] units;

	public Map(Hero hero) {
		this.size = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);
		this.units = new HashMap[size];
		this.random = new Random();

		for (int i = 0; i < units.length; i++) {
			units[i] = new HashMap<>();
		}

		units[this.size / 2].put(size / 2, hero);
	}

	public void downloadEnemies(int level) {
		int enemiesCount = (int) (ENEMIES_COUNT_MODIFIER * size * size);
		Enemy enemy;

		for (int i = 0; i < enemiesCount; i++) {
			enemy = EnemyBuilder.getInstance().createBandit(level);
			setEnemiesOnMap(enemy);
		}
	}

	private void setEnemiesOnMap(Enemy enemy) {
		int x;
		int y;
		HashMap<Integer, Unit> column;
		while (true) {
			x = generateCoordinate();
			column = units[x];
			y = generateCoordinate();
			if (column.get(y) == null) {
				enemy.getPosition().setX(x);
				enemy.getPosition().setY(y);
				column.put(y, enemy);
				break;
			}
		}
	}

	private int generateCoordinate() {
		int i = random.nextInt(size);
		while (i == (size / 2)) {
			i = random.nextInt(size);
		}
		return i;
	}
}
