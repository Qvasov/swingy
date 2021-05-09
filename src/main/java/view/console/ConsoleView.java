package view.console;

import controller.GameController;
import model.Map;

public class ConsoleView {
	private final GameController controller;

	public ConsoleView(GameController controller) {
		this.controller = controller;
		updateView();
	}

	public void updateView() {
		Map map = controller.getModel().getMap();
		StringBuilder result = new StringBuilder("");

		System.out.println("--------------------\n" +
				"|      SWINGY      |\n" +
				"--------------------\n");

		for (int y = 0; y < map.getSize(); y++) {
			for (int x = 0; x < map.getSize(); x++) {
				if (map.getUnits()[x] == null || map.getUnits()[x].get(y) == null) {
					result.append("_");
				} else {
					result.append(map.getUnits()[x].get(y).getSymbol());
				}
			}
			result.append("\n");
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
				"Name: " + controller.getModel().getEnemy().getName() + "\n" +
				"HP: " + controller.getModel().getEnemy().getName() + "\n" +
				"Attack: " + controller.getModel().getEnemy().getName() + "\n" +
				"Defence: " + controller.getModel().getEnemy().getName() + "\n" +
				"1. Fight\n" +
				"2. Run (50% chance to avoid the fight)\n");
	}

	protected void fightResult() {
		StringBuilder result = new StringBuilder();
		result.append("--------------------\n")
				.append("|      BATTLE      |\n")
				.append("--------------------\n")
				.append(controller.getModel().getBattleLog());
		if (controller.getModel().getItem() != null) {
			result.append("Drop: ").append(controller.getModel().getItem().getClass().getSimpleName()).append("\n")
					.append(controller.getModel().getItem().getStats()).append("\n")
					.append("1. Keep (Keep item)\n")
					.append("2. Leave (Leave item)\n");
		} else {
			result.append("1. OK\n");
		}
		System.out.print(result);
	}

	protected void gameOver() {
		System.out.print("---------------------\n" +
				"|     GAME OVER     |\n" +
				"---------------------\n" +
				"1. Next\n" +
				"2. Exit\n");
	}
}
