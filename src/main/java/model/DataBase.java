package model;

import model.characters.heroes.Hero;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataBase {
	private static DataBase dataBase = new DataBase();
	private static String baseFilePath = "src/main/resources/Heroes";

	private DataBase() {
	}

	public static DataBase getInstance() {
		return dataBase;
	}

	public ArrayList<String> getHeroNames() {
		BufferedReader bf;
		String line;
		String[] str;
		ArrayList<String> result = new ArrayList<>();
		try {
			bf = new BufferedReader(new FileReader("src/main/resources/Heroes"));
			while ((line = bf.readLine()) != null) {
				str = line.split(" ");
				result.add(str[0]);
			}
			bf.close();
		} catch (IOException e) {
			System.exit(-1);
		}
		return result;
	}

	public Map<String, String> getHeroStats(String name) {
		BufferedReader bf;
		String line;
		String[] str;
		try {
			bf = new BufferedReader(new FileReader("src/main/resources/Heroes"));
			Map<String, String> result = new HashMap<>();
			while ((line = bf.readLine()) != null) {
				str = line.split(" ");
				if (str[0].equals(name)) {
					result.put("class", str[1]);
					result.put("level", str[2]);
					result.put("experience", str[3]);
					result.put("hp", str[4]);
					result.put("minAttack", str[5]);
					result.put("maxAttack", str[6]);
					result.put("defence", str[7]);
					result.put("weapon", str[8]);
					result.put("weaponStat", str[9]);
					result.put("armor", str[10]);
					result.put("armorStat", str[11]);
					result.put("helm", str[12]);
					result.put("helmStat", str[13]);
					return result;
				}
			}
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return null;
	}

	public void saveHero(Hero hero) {
		try {
			Writer fw = new FileWriter(baseFilePath, true);
			String heroStr = hero.getName() + " " +
					hero.getClass().getSimpleName() + " " +
					hero.getLevel() + " " +
					hero.getExp() + " " +
					hero.getHp() + " " +
					hero.getMinAttack() + " " +
					hero.getMaxAttack() + " " +
					hero.getDefence();
			heroStr += hero.getWeapon() == null ? " " + "null" + " " + "0"
					: " " + hero.getWeapon().getClass().getSimpleName() + " " + hero.getWeapon().getAttack();
			heroStr += hero.getArmor() == null ? " " + "null" + " " + "0"
					: " " + hero.getArmor().getClass().getSimpleName() + " " + hero.getArmor().getDefence();
			heroStr += hero.getHelm() == null ? " " + "null" + " " + "0"
					: " " + hero.getHelm().getClass().getSimpleName() + " " + hero.getHelm().getHp();
			heroStr += "\n";
			fw.write(heroStr);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
