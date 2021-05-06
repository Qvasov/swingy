package view.console;

import controller.GameController;

import java.util.Scanner;

import static view.console.Menu.*;

public class HeroPickConsoleView {
	private GameController controller;
	private Scanner scanner = new Scanner(System.in);
	private String input;
	private String name;
	private Menu menu = MAIN;

	public HeroPickConsoleView(GameController controller) {
		this.controller = controller;
		mainMenu();
		while (scanner.hasNext()) {
			input = scanner.next();
			switch (menu) {
				case MAIN:
					if (input.equals("1")) {
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
						load();
					} else if (input.equals("3")) {
						mainMenu();
					}
					break;
				case NAME:
					if (input.matches("[a-zA-Z]")) {
						name = input;
					} else if (input.equals("!back")) {
						create();
					}
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
				"---------------------\n" +
				"1. Name\n" +
				"2. Class");
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
	}
}
