package view.console;

import controller.GameController;
import model.Map;
import view.View;

import java.util.Scanner;

public class ConsoleView implements View {
	private final GameController controller;
	private Scanner scanner = new Scanner(System.in);
	private String input;

	public ConsoleView(GameController controller) {
		this.controller = controller;
		updateView();
		while (scanner.hasNext()) {
			input = scanner.next();
			switch (controller.getModel().getState()) {
				case MOVEMENT:
					if (input.equalsIgnoreCase("N") || input.equalsIgnoreCase("U")) {
						controller.getModel().moveUp();
					} else if (input.equalsIgnoreCase("S") || input.equalsIgnoreCase("D")) {
						controller.getModel().moveDown();
					} else if (input.equalsIgnoreCase("W") || input.equalsIgnoreCase("L")) {
						controller.getModel().moveLeft();
					} else if (input.equalsIgnoreCase("E") || input.equalsIgnoreCase("R")) {
						controller.getModel().moveRight();
					}
					updateView();
					break;
				case ATTACK: {
					if (input.equals("1")) {
						controller.fight();
					} else if (input.equals("2")) {
						controller.run();
					}
					updateView();
					break;
				}
				case FIGHT_LOG: {
					if (controller.getModel().getItem() != null) {
						if (input.equals("1")) {
							controller.equipItem();
						} else if (input.equals("2")) {
							controller.ok();
						}
					} else {
						if (input.equals("1")) {
							controller.ok();
						}
					}
					updateView();
					break;
				}
				case GAME_OVER: {
					if (input.equals("1")) {
						controller.startGame(controller.getModel().getHero());
					} else if (input.equals("2")) {
						controller.launchGame();
					}
					updateView();
					break;
				}
			}
		}
	}

	@Override
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
			case GAME_OVER:
				gameOver();
				break;
		}
	}

	public void attack() {
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

	public void fightResult() {
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

	public void gameOver() {
		System.out.print("---------------------\n" +
				"|     GAME OVER     |\n" +
				"---------------------\n" +
				"1. Next\n" +
				"2. Exit\n");
	}
}
