package view.console;

import controller.GameController;
import model.characters.heroes.HeroBuilder;

import java.util.Map;
import java.util.Scanner;

import static view.console.Menu.*;

public class HeroPickConsoleView {
	private final GameController controller;
	private final Scanner scanner = new Scanner(System.in);
	private String input;
	private String heroName;
	private String heroClass;
	private Menu menu = MAIN;

	public HeroPickConsoleView(GameController controller) {
		this.controller = controller;
		this.heroName = "";
		this.heroClass = "";
		mainMenu();
		while (scanner.hasNext()) {
			input = scanner.next();
			switch (menu) {
				case MAIN:
					if (input.equals("1")) {
						heroName = "";
						heroClass = "";
						create();
					} else if (input.equals("2")) {
						load();
					} else if (input.equals("3")) {
						System.exit(0);
					}
					break;
				case CREATE:
					if (input.equals("1")) {
						name();
					} else if (input.equals("2")) {
						chooseClass();
					} else if (input.equals("3")) {
						if (!heroClass.isEmpty() && !heroName.isEmpty()) {
							controller.startGame(HeroBuilder.getInstance().createHero(heroClass, heroName));
						} else {
							create();
						}
					} else if (input.equals("4")) {
						mainMenu();
					}
					break;
				case NAME:
					//TODO ограничение на символы
					if (input.matches("[a-zA-Z]*")) {
						heroName = input;
					}
					create();
					break;
				case CLASS:
					if (input.equals("1")) {
						heroClass = "Warrior";
					} else if (input.equals("2")) {
						heroClass = "Rogue";
					}
					create();
					break;
			}
		}
	}

	private void mainMenu() {
		System.out.println("---------------------\n" +
				"|     Hero Pick     |\n" +
				"---------------------");
		System.out.println("1. Create Hero\n" +
				"2. Load Hero\n" +
				"3. Exit");
		menu = MAIN;
	}

	private void create() {
		System.out.println("---------------------\n" +
				"|    Create Hero    |\n" +
				"---------------------");
		if (heroName.isEmpty()) {
			System.out.println("You need enter Name of your Hero");
		} else {
			System.out.println("Name: " + heroName);
		}
		if (heroClass.isEmpty()) {
			System.out.println("You need to choose Class");
		} else {
			Map<String, Integer> stats = HeroBuilder.getInstance().getStats(heroClass);
			System.out.println("Class: " + heroClass);
			System.out.printf("Level: %s\n" +
							"Experience: %s\n" +
							"Attack: %s\n" +
							"Defence: %s\n" +
							"Hit points: %s\n",
					stats.get("level"), stats.get("experience"), stats.get("attack"), stats.get("defence"), stats.get("hp"));
		}
		System.out.println("1. Enter Name\n" +
				"2. Choose Class\n" +
				"3. Start\n" +
				"4. Back");
		menu = CREATE;
	}

	private void load() {
		menu = LOAD;
	}

	private void name() {
		System.out.println("---------------------\n" +
				"|    Create Hero    |\n" +
				"---------------------\n" +
				"Enter Name...");
		menu = NAME;
	}

	private void chooseClass() {
		System.out.println("---------------------\n" +
				"|    Create Hero    |\n" +
				"---------------------");
		int i = 1;
		for (String hero : HeroBuilder.getInstance().getHeroes()) {
			System.out.println(i + ". " + hero);
			i++;
		}
		menu = CLASS;
	}
}
