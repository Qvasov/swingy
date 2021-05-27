package view.console;

import lombok.Getter;
import lombok.Setter;
import model.DataBase;
import model.characters.heroes.HeroBuilder;

import java.util.ArrayList;
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
		heroName = null;
		heroClass = null;
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
		System.out.println("---------------------\n" +
				"|     Load Hero     |\n" +
				"---------------------");
		if (heroName == null) {
			System.out.print("Name:\tYou need to choose Hero\n");
		} else {
			System.out.printf("Name:\t%s\n", heroName);
			Map<String, String> stats = DataBase.getInstance().getHeroStats(heroName);
			System.out.printf("Stats:\tClass: %s\n", stats.get("class"));
			StringBuilder result = new StringBuilder();
			result.append("\t\tLevel: ").append(stats.get("level")).append("\n")
					.append("\t\tExperience: ").append(stats.get("experience")).append("\n")
					.append("\t\tAttack: ").append(stats.get("minAttack")).append(" - ").append(stats.get("maxAttack")).append("\n");
			if (!stats.get("weapon").equals("null")) {
				result.deleteCharAt(result.length() - 1).append(" + ").append(stats.get("weaponStat")).append("\n");
			}
			result.append("\t\tDefence: ").append(stats.get("defence")).append("\n");
			if (!stats.get("armor").equals("null")) {
				result.deleteCharAt(result.length() - 1).append(" + ").append(stats.get("armorStat")).append("\n");
			}
			result.append("\t\tHit points: ").append(stats.get("hp")).append("\n");
			if (!stats.get("helm").equals("null")) {
				result.deleteCharAt(result.length() - 1).append(" + ").append(stats.get("helmStat")).append("\n");
			}
			System.out.print(result);
		}
		System.out.println("1. Choose Hero\n" +
				"2. Start");
		if (heroName != null) {
			System.out.println("3. Delete\n" +
					"4. Back");
		} else {
			System.out.println("3. Back");
		}
		state = LOAD;
	}

	protected void name() {
		System.out.println("---------------------\n" +
				"|    Create Hero    |\n" +
				"---------------------\n" +
				"Name must be not null and its length must be from 1 to 20 symbols\n" +
				"Enter Name... (Type !back to return prev menu)");
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

	protected void chooseHero() {
		System.out.println("---------------------\n" +
				"|     Load Hero     |\n" +
				"---------------------\n" +
				"Enter Name... (Type !back to return prev menu)");
		ArrayList<String> names = DataBase.getInstance().getHeroNames();
		if (names.size() == 0) {
			System.out.println("No heroes");
		} else {
			for (String name : names) {
				System.out.println(name);
			}
		}
		state = HERO;
	}
}
