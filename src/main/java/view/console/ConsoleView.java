package view.console;

import controller.GameController;
import model.Map;
import model.characters.heroes.Hero;

public class ConsoleView {
	private final GameController controller;

	public ConsoleView(GameController controller) {
		this.controller = controller;
		updateView();
	}

	public void updateView() {
		Map map = controller.getModel().getMap();
		Hero hero = controller.getModel().getHero();
		StringBuilder result = new StringBuilder();

		System.out.println("--------------------\n" +
				"|      SWINGY      |\n" +
				"--------------------");

		result.append("Name: ").append(hero.getName()).append("\n")
				.append("Level: ").append(hero.getLevel()).append("\n")
				.append("Experience: ").append(hero.getExp()).append(" / ").append(hero.getExpToNextLvl()).append("\n")
				.append("HP: ").append(hero.getCurHp()).append("\n")
				.append("Attack: ").append(hero.getMinAttack()).append(" - ").append(hero.getMaxAttack()).append("\n")
				.append("Defence: ").append(hero.getDefence()).append("\n")
				.append("--------------------\n")
				.append("Steps:\n")
				.append("n, u: to north\n")
				.append("s, d: to south\n")
				.append("w, l: to west\n")
				.append("e, r: to east\n")
				.append("!exit: save and exit game\n")
				.append("--------------------\n");

		int overview = 5;
		int viewport = overview * 2 + 1;
		int startX = 0;
		int startY = 0;
		int endX = map.getSize() - 1;
		int endY = map.getSize() - 1;


		boolean up = false;
		boolean down = false;
		boolean left = false;
		boolean right = false;

		if (map.getSize() >= viewport) {
			startX = Math.max(hero.getPosition().getX() - overview, 0);
			startY = Math.max(hero.getPosition().getY() - overview, 0);
			endX = Math.min(hero.getPosition().getX() + overview, map.getSize() - 1);
			endY = Math.min(hero.getPosition().getY() + overview, map.getSize() - 1);
			if (endX - startX + 1 < viewport) {
				startX = (hero.getPosition().getX() < map.getSize() / 2) ? 0 : map.getSize() - viewport + 1;
				endX = (hero.getPosition().getX() < map.getSize() / 2) ? viewport - 2 : map.getSize() - 1;
				if (startX == 0) left = true;
				if (endX == map.getSize() - 1) right = true;
			}
			if (endY - startY + 1 < viewport) {
				startY = (hero.getPosition().getY() < map.getSize() / 2) ? 0 : map.getSize() - viewport + 1;
				endY = (hero.getPosition().getY() < map.getSize() / 2) ? viewport - 2 : map.getSize() - 1;
				if (startY == 0) up = true;
				if (endY == map.getSize() - 1) down = true;
			}
		} else {
			up = true;
			down = true;
			left = true;
			right = true;
		}

		for (int y = startY; y <= endY; y++) {
			if (y == 0 && up) {
				if (left) result.append("#");
				for (int i = 0; i <= endX - startX; i++) {
					result.append("#");
				}
				if (right) result.append("#");
				result.append("\n");
			}

			for (int x = startX; x <= endX; x++) {
				if (x == 0 && left) result.append("#");

				if (map.getUnits()[x] == null || map.getUnits()[x].get(y) == null) {
					result.append("_");
				} else {
					result.append(map.getUnits()[x].get(y).getSymbol());
				}

				if (x == map.getSize() - 1 && right) result.append("#");
			}
			result.append("\n");

			if (y == map.getSize() - 1 && down) {
				if (left) result.append("#");
				for (int i = 0; i <= endX - startX; i++) {
					result.append("#");
				}
				if (right) result.append("#");
				result.append("\n");
			}
		}

		System.out.print(result);

		switch (controller.getModel().getState()) {
			case ATTACK:
				attack();
				break;
			case FIGHT_LOG:
				fightResult();
				break;
			case NEXT:
				gameOver();
				break;
		}
	}

	protected void attack() {
		System.out.print("--------------------\n" +
				"|      BATTLE      |\n" +
				"--------------------\n" +
				"Name:\t" + controller.getModel().getEnemy().getName() + "\n" +
				"Stats:\tHP: " + controller.getModel().getEnemy().getCurHp() + "\n" +
				"\t\tAttack: " + controller.getModel().getEnemy().getMinAttack() +
				" - " + controller.getModel().getEnemy().getMaxAttack() + "\n" +
				"\t\tDefence: " + controller.getModel().getEnemy().getDefence() + "\n" +
				"1. Fight\n" +
				"2. Run (50% chance to avoid the fight)\n");
	}

	protected void fightResult() {
		StringBuilder result = new StringBuilder();
		result.append("--------------------\n")
				.append("|      BATTLE      |\n")
				.append("--------------------\n")
				.append(controller.getModel().getBattleLog())
				.append("\n");
		if (controller.getModel().getItem() != null) {
			result.append("Drop:\t").append(controller.getModel().getItem().getClass().getSimpleName()).append("\n")
					.append("Stats:\t").append(controller.getModel().getItem().getStats()).append("\n")
					.append("1. Keep (Keep item)\n")
					.append("2. Leave (Leave item)\n");
		} else {
			result.append("1. OK\n");
		}
		System.out.print(result);
	}

	protected void gameOver() {
		System.out.print("----------------------\n" +
				"|   LEVEL COMPLETE   |\n" +
				"----------------------\n" +
				"Do you want leave this level?\n" +
				"1. Next level\n" +
				"2. Cancel\n" +
				"3. Save & Exit\n");
	}
}
