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
		this.hero.getPosition().setX(map.getSize() / 2 + 1);
		this.hero.getPosition().setY(map.getSize() / 2 + 1);
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
		int srcX = hero.getPosition().getX() - 1;
		int srcY = hero.getPosition().getY() - 1;
		destX += srcX;
		destY += srcY;
		//проверка на границу карты
		if (destY < 1 || destY > map.getSize() || destX < 1 || destX > map.getSize()) {
			//вывод сообщения, что это граница карты в textArea
		} else {
			unit = map.getUnits()[destX].get(destY);
			map.getUnits()[srcX].remove(srcY);
		    map.getUnits()[destX].remove(destY - 1);
			map.getUnits()[destX].put(destY, hero);
			hero.getPosition().setX(destX);
			hero.getPosition().setY(destY);
		}
	}

	public boolean isBattle() {
		return unit != null && unit instanceof Enemy;
	}

	public void run() {
		if (random.nextInt(2) == 1) {
			map.getUnits()[hero.getPosition().getOldX() - 1].put(hero.getPosition().getOldY() - 1, hero);
			map.getUnits()[unit.getPosition().getX() - 1].put(unit.getPosition().getY() - 1, unit);
		} else {
			fight();
		}
	}

	public void fight() {
		//Эмуляция боя с unit
		// итог боя герой переходит на клетку противника
		unit = null;
	}

	public void downloadCharacters() {
		HeroStorage.getInstance().download();
	}

}
