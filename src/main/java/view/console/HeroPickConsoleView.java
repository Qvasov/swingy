package view.console;

import controller.GameController;
import model.characters.heroes.HeroBuilder;

import java.util.Scanner;
import java.util.Set;

import static view.console.Menu.*;

public class HeroPickConsoleView {
	private GameController controller;
	private Scanner scanner = new Scanner(System.in);
	private String input;
	private String heroName;
	private String heroClass;
	private Menu menu = MAIN;

	public HeroPickConsoleView(GameController controller) {
		this.controller = controller;
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
				"---------------------\n" +
				"1. Create Hero\n" +
				"2. Load Hero\n" +
				"3. Exit");
		menu = MAIN;
	}

	private void create() {
		System.out.println("---------------------\n" +
				"|    Create Hero    |\n" +
				"---------------------");
		if (!heroName.isEmpty()) {
			System.out.println("Name: " + heroName);
		}
		if (!heroClass.isEmpty()) {
			System.out.println("Class: " + heroClass);
			//TODO статы клсса героя
		}
		System.out.println("1. Enter Name\n" +
				"2. Choose Class\n" +
				"3. Back");
		menu = CREATE;
	}

	private void load() {
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
