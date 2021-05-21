package view.console;

import controller.GameController;
import model.DataBase;
import model.characters.heroes.HeroBuilder;
import view.View;

import java.util.Scanner;

public class ConsoleUI implements View {
	private ConsoleView consoleView;
	private Scanner scanner = new Scanner(System.in);
	private String input;

	@Override
	public void heroPick(GameController controller) {
		HeroPickConsoleView heroPickConsoleView = new HeroPickConsoleView();
		while (scanner.hasNext()) {
			input = scanner.next();
			switch (heroPickConsoleView.getState()) {
				case MAIN:
					if (input.equals("1")) {
						heroPickConsoleView.setHeroName(null);
						heroPickConsoleView.setHeroClass(null);
						heroPickConsoleView.create();
					} else if (input.equals("2")) {
						heroPickConsoleView.load();
					} else if (input.equals("3")) {
						System.exit(0);
					}
					break;
				case CREATE:
					if (input.equals("1")) {
						heroPickConsoleView.name();
					} else if (input.equals("2")) {
						heroPickConsoleView.chooseClass();
					} else if (input.equals("3")) {
						try {
							controller.startGame(HeroBuilder.getInstance().createHero(
									heroPickConsoleView.getHeroClass(), heroPickConsoleView.getHeroName()));
						} catch (IllegalArgumentException exception) {
							error(exception.getMessage());
						}
					} else if (input.equals("4")) {
						heroPickConsoleView.mainMenu();
					}
					break;
				case LOAD:
					if (input.equals("1")) {
						heroPickConsoleView.chooseHero();
					} else if (input.equals("2")) {
						try {
							controller.startGame(HeroBuilder.getInstance().loadHero(
									heroPickConsoleView.getHeroName()));
						} catch (IllegalArgumentException exception) {
							error(exception.getMessage());
						}
					} else if (input.equals("3")) {
						heroPickConsoleView.mainMenu();
					}
					break;
				case NAME:
					if (input.equals("!back")) {
						heroPickConsoleView.create();
					} else {
						if (DataBase.getInstance().getHeroNames().contains(input)) {
							error("This Name is already taken");
						} else {
							heroPickConsoleView.setHeroName(input);
							heroPickConsoleView.create();
						}
					}
					break;
				case CLASS:
					if (input.equals("1")) {
						heroPickConsoleView.setHeroClass("Warrior");
					} else if (input.equals("2")) {
						heroPickConsoleView.setHeroClass("Ranger");
					}
					heroPickConsoleView.create();
					break;
				case HERO:
					if (input.equals("!back")) {
						heroPickConsoleView.load();
					} else {
						if (DataBase.getInstance().getHeroNames().contains(input)) {
							heroPickConsoleView.setHeroName(input);
							heroPickConsoleView.load();
						} else {
							error("Unknown hero name");
						}
					}
					break;
			}
		}
	}

	@Override
	public void newGameMap(GameController controller) {
		this.consoleView = new ConsoleView(controller);
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
					} else if (input.equals("!exit")) {
						controller.saveHero();
						controller.launchGame();
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
					} else if (controller.getModel().getHero().isDead()) {
						if (input.equals("1")) {
							controller.launchGame();
						}
					} else {
						if (input.equals("1")) {
							controller.ok();
						}
					}
					updateView();
					break;
				}
				case NEXT: {
					if (input.equals("1")) {
						controller.saveHero();
						controller.startGame(controller.getModel().getHero());
					} else if (input.equals("2")) {
						controller.ok();
					} else if (input.equals("3")) {
						controller.saveHero();
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
		consoleView.updateView();
	}

	@Override
	public void error(String message) {
		System.err.println("ERROR: " + message);
	}
}
