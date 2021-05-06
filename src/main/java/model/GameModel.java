package model;

import lombok.Getter;
import model.characters.enemies.Enemy;
import model.characters.heroes.Hero;
import model.items.ItemFactory;
import model.items.abstarct.Item;

import java.util.Random;

@Getter
public class GameModel {
	private final Random random;
	private Map map;
	private Hero hero;
	private Enemy enemy;
	private Item item;
	private String battleLog;
	private State state;

	public GameModel() {
		this.random = new Random();
		this.state = State.PICK_HERO;
	}

	public void downloadMap(Hero hero) {
		this.hero = hero;
		this.map = new Map(this.hero);
		this.hero.getPosition().setX(map.getSize() / 2);
		this.hero.getPosition().setY(map.getSize() / 2);
		this.map.downloadEnemies(hero.getLevel());
		this.state = State.MOVEMENT;
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
		if (destY < 0 || destY > map.getSize() - 1 || destX < 0 || destX > map.getSize() - 1) {
			state = State.GAME_OVER;
		} else {
			enemy = (Enemy) map.getUnits()[destX].put(destY, map.getUnits()[srcX].remove(srcY));
			hero.getPosition().setX(destX);
			hero.getPosition().setY(destY);
			state = (enemy != null) ? State.ATTACK : State.MOVEMENT;
		}
	}

	public boolean run() {
		if (random.nextInt(2) == 0) {
			hero.getPosition().setX(hero.getPosition().getPrevX());
			hero.getPosition().setY(hero.getPosition().getPrevY());
			map.getUnits()[hero.getPosition().getX()].put(hero.getPosition().getY(), hero);
			map.getUnits()[enemy.getPosition().getX()].put(enemy.getPosition().getY(), enemy);
			enemy = null;
			state = State.MOVEMENT;
			return true;
		}
		return false;
	}

	public void fight() {
		battleLog = "";
		while (true) {
			attack(this.hero, this.enemy);
			if (enemy.isDead()) {
				battleLog += String.format("%s has won the fight!\n", this.hero.getName());
				hero.receiveExp(enemy.getExp());
				hero.recoveryHp(hero.getFullHp());
				item = ItemFactory.getInstance().generateItem(enemy);
				enemy = null;
				state = State.FIGHT_LOG;
				return;
			}
			attack(this.enemy, this.hero);
			if (hero.isDead()) {
				battleLog += String.format("%s has died!\n It's Game Over", this.hero.getName());
				map.getUnits()[enemy.getPosition().getX()].put(enemy.getPosition().getY(), enemy);
				state = State.GAME_OVER;
				return;
			}
		}
	}

	private void attack(Unit attacker, Unit defender) {
		int damage = defender.receiveDamage(attacker.dealDamage());
		battleLog += String.format("%s deals %d damage to %s\n", attacker.getName(), damage, defender.getName());
	}

	public void equipItem() {
		item.equip(hero);
		item = null;
		state = State.MOVEMENT;
	}

	public void ok() {
		state = State.MOVEMENT;
	}
}
