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
		mainMenu();
	}

	protected void mainMenu() {
		System.out.println("---------------------\n" +
				"|     Main menu     |\n" +
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
		if (heroName == null) {
			System.out.print("Name:\tYou need enter Name of your Hero\n");
		} else {
			System.out.printf("Name:\t%s\n", heroName);
		}
		if (heroClass == null) {
			System.out.print("Stats:\tYou need to choose Class\n");
		} else {
			Map<String, Integer> stats = HeroBuilder.getInstance().getStats(heroClass);
			System.out.printf("Stats:\tClass: %s\n", heroClass);
			System.out.printf("\t\tLevel: %s\n" +
							"\t\tExperience: %s\n" +
							"\t\tAttack: %s - %s\n" +
							"\t\tDefence: %s\n" +
							"\t\tHit points: %s\n",
					stats.get("level"), stats.get("experience"), stats.get("minAttack"),
					stats.get("maxAttack"), stats.get("defence"), stats.get("hp"));
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
				"Name must be not null and its length must be from 1 to 20 symbols\n" +
				"Enter Name...");
		state = NAME;
	}

	protected void chooseClass() {
		System.out.println("---------------------\n" +
				"|    Create Hero    |\n" +
				"---------------------\n" +
				"Choose class number or enter something to go back");
		int i = 1;
		for (String hero : HeroBuilder.getInstance().getHeroes()) {
			System.out.println(i + ". " + hero);
			i++;
		}
		state = CLASS;
	}
}
