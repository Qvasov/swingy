package view.console;

import lombok.Getter;
import lombok.Setter;
import model.characters.heroes.HeroBuilder;

import java.util.Map;

import static view.console.MenuState.*;

@Getter
public class HeroPickConsoleView {
	@Setter
	private String heroName;
	@Setter
	private String heroClass;
	private MenuState state;

	public HeroPickConsoleView() {
		this.heroName = "";
		this.heroClass = "";
		mainMenu();
	}

	protected void mainMenu() {
		System.out.println("---------------------\n" +
				"|     Hero Pick     |\n" +
				"---------------------");
		System.out.println("1. Create Hero\n" +
				"2. Load Hero\n" +
				"3. Exit");
		state = MAIN;
	}

	protected void create() {
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
		state = CREATE;
	}

	protected void load() {
		state = LOAD;
	}

	protected void name() {
		System.out.println("---------------------\n" +
				"|    Create Hero    |\n" +
				"---------------------\n" +
				"Enter Name...");
		state = NAME;
	}

	protected void chooseClass() {
		System.out.println("---------------------\n" +
				"|    Create Hero    |\n" +
				"---------------------");
		int i = 1;
		for (String hero : HeroBuilder.getInstance().getHeroes()) {
			System.out.println(i + ". " + hero);
			i++;
		}
		state = CLASS;
	}
}
