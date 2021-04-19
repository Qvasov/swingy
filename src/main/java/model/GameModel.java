package model;

import controller.GameController;
import lombok.Getter;
import lombok.Setter;
import model.characters.enemies.Enemy;
import model.characters.heroes.Hero;
import model.characters.heroes.HeroStorage;

public class GameModel {
	@Setter
	GameController controller;
	@Getter
	Map map;
	@Setter
	Hero hero;

	public void downloadMap() {
		map = new Map(hero);
		hero.setPosition(new Point(map.getSize() / 2 + 1, map.getSize() / 2 + 1));
		map.downloadEnemies();
	}

	public void moveUp() {
		move(hero.getPosition().getX(), hero.getPosition().getY() - 1);
	}

	public void moveRight() {
		move(hero.getPosition().getX() + 1, hero.getPosition().getY());
	}

	public void moveDown() {
		move(hero.getPosition().getX(), hero.getPosition().getY() + 1);
	}

	public void moveLeft() {
		move(hero.getPosition().getX() - 1, hero.getPosition().getY());
	}

	private void move(int destX, int destY) {
		int srcX = hero.getPosition().getX() - 1;
		int srcY = hero.getPosition().getY() - 1;
		//проверка на границу карты
		if (destY < 1 || destY > map.getSize() || destX < 1 || destX > map.getSize()) {
			//вывод сообщения, что это граница карты в textArea
		} else {
			//битва
			if (map.getUnits()[destX - 1].get(destY - 1) != null &&
					map.getUnits()[destX - 1].get(destY - 1) instanceof Enemy) {
				controller.battle((Enemy) map.getUnits()[destX - 1].get(destY - 1));
			} else {
				map.getUnits()[srcX].remove(srcY);

//			    map.getUnits()[destX].remove(destY);
				map.getUnits()[destX - 1].put(destY - 1, hero);

				hero.getPosition().setX(destX);
				hero.getPosition().setY(destY);
			}
		}
	}



	public void downloadCharacters() {
		HeroStorage.getInstance().download();
	}

}
