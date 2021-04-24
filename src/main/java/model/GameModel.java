package model;

import lombok.Getter;
import lombok.Setter;
import model.characters.enemies.Enemy;
import model.characters.heroes.Hero;
import model.characters.heroes.HeroStorage;

import java.util.Random;

public class GameModel {
	private final Random random;
	@Getter
	private Map map;
	@Setter
	private Hero hero;
	private Unit unit;

	public GameModel() {
		this.random = new Random();
	}

	public void downloadMap() {
		this.map = new Map(hero);
		this.hero.getPosition().setX(map.getSize() / 2);
		this.hero.getPosition().setY(map.getSize() / 2);
		this.map.downloadEnemies();
	}

	public void moveUp() {
		move(0, -1);
	}

	public void moveRight() {
		move(1, 0);
	}

	public void moveDown() {
		move(0, 1);
	}

	public void moveLeft() {
		move(-1, 0);
	}

	private void move(int destX, int destY) {
		int srcX = hero.getPosition().getX();
		int srcY = hero.getPosition().getY();
		destX += srcX;
		destY += srcY;
		//проверка на границу карты
		if (destY < 0 || destY > map.getSize() - 1 || destX < 0 || destX > map.getSize() - 1) {
			//вывод сообщения, что это граница карты в textArea
		} else {
			unit = map.getUnits()[destX].put(destY, hero);
			map.getUnits()[srcX].remove(srcY);
			hero.getPosition().setX(destX);
			hero.getPosition().setY(destY);
		}
	}

	public boolean isBattle() {
		return unit != null && unit instanceof Enemy;
	}

	public void run() {
		if (random.nextInt(2) == 0) {
			hero.getPosition().setPrevX();
			hero.getPosition().setPrevY();
			map.getUnits()[hero.getPosition().getX()].put(hero.getPosition().getY(), hero);
			map.getUnits()[unit.getPosition().getX()].put(unit.getPosition().getY(), unit);
			unit = null;
		} else {
			fight();
		}
	}

	public void fight() {
		// Эмуляция боя с unit
		// Пре
		// итог боя герой
		// переходит на клетку противника
		unit = null;
	}
}
