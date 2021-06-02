package model;

import model.characters.heroes.Hero;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataBase {
	private static DataBase dataBase = new DataBase();
	private Connection connection;
	private static final String URL = "jdbc:hsqldb:file:Heroes/swingyDB;shutdown=true";
	private static final String USER = "SA";
	private static final String PASSWORD = "";

	private DataBase() {
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			connection.prepareStatement("CREATE TABLE IF NOT EXISTS t_heroes " +
					"(f_name VARCHAR(20) NOT NULL primary key, " +
					"f_class VARCHAR(255) NOT NULL, " +
					"f_level INT NOT NULL, " +
					"f_experience INT NOT NULL, " +
					"f_hp INT NOT NULL, " +
					"f_min_atk INT NOT NULL, " +
					"f_max_atk INT NOT NULL, " +
					"f_defence INT NOT NULL, " +
					"f_weapon VARCHAR(255), " +
					"f_w_stat INT, " +
					"f_armor VARCHAR(255), " +
					"f_a_stat INT, " +
					"f_helm VARCHAR(255), " +
					"f_h_stat INT);").execute();
			connection.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	public static DataBase getInstance() {
		return dataBase;
	}

	public ArrayList<String> getHeroNames() {
		ArrayList<String> result = new ArrayList<>();
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			String query = "SELECT f_name FROM t_heroes;";
			ResultSet resultSet = connection.prepareStatement(query).executeQuery();
			while (resultSet.next()) {
				result.add(resultSet.getString("f_name"));
			}
			connection.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return result;
	}

	public Map<String, String> getHeroStats(String name) {
		if (name != null) {
			try {
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
				String query = "SELECT * FROM t_heroes WHERE f_name='" + name + "';";
				ResultSet resultSet = connection.prepareStatement(query).executeQuery();
				resultSet.next();
				Map<String, String> result = new HashMap<>();
				result.put("class", resultSet.getString("f_class"));
				result.put("level", resultSet.getString("f_level"));
				result.put("experience", resultSet.getString("f_experience"));
				result.put("hp", resultSet.getString("f_hp"));
				result.put("minAttack", resultSet.getString("f_min_atk"));
				result.put("maxAttack", resultSet.getString("f_max_atk"));
				result.put("defence", resultSet.getString("f_defence"));
				result.put("weapon", resultSet.getString("f_weapon"));
				result.put("weaponStat", resultSet.getString("f_w_stat"));
				result.put("armor", resultSet.getString("f_armor"));
				result.put("armorStat", resultSet.getString("f_a_stat"));
				result.put("helm", resultSet.getString("f_helm"));
				result.put("helmStat", resultSet.getString("f_h_stat"));
				connection.close();
				return result;
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		return null;
	}

	public void saveHero(Hero hero) {
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			String query = String.format("SELECT f_name FROM t_heroes WHERE f_name='%s'", hero.getName());
			if (connection.prepareStatement(query).executeQuery().next()) {
				query = String.format("UPDATE t_heroes " +
								"SET f_name='%s', f_class='%s', f_level=%d, f_experience=%d, f_hp=%d, " +
								"f_min_atk=%d, f_max_atk=%d, f_defence=%d, f_weapon='%s', f_w_stat=%d, " +
								"f_armor='%s', f_a_stat=%d, f_helm='%s', f_h_stat=%d " +
								"WHERE f_name='%s';",
						hero.getName(),
						hero.getClass().getSimpleName(),
						hero.getLevel(),
						hero.getExp(),
						hero.getHp(),
						hero.getMinAttack(),
						hero.getMaxAttack(),
						hero.getDefence(),
						hero.getWeapon(),
						hero.getWeapon() == null ? 0 : hero.getWeapon().getAttack(),
						hero.getArmor(),
						hero.getArmor() == null ? 0 : hero.getArmor().getDefence(),
						hero.getHelm(),
						hero.getHelm() == null ? 0 : hero.getHelm().getHp(),
						hero.getName());
			} else {
				query = String.format("INSERT INTO t_heroes " +
								"(f_name, f_class, f_level, f_experience, f_hp, f_min_atk, f_max_atk, f_defence, " +
								"f_weapon, f_w_stat, f_armor, f_a_stat, f_helm, f_h_stat) " +
								"VALUES ('%s', '%s', %d, %d, %d, %d, %d, %d, '%s', '%d', '%s', %d, '%s', %d);",
						hero.getName(),
						hero.getClass().getSimpleName(),
						hero.getLevel(),
						hero.getExp(),
						hero.getHp(),
						hero.getMinAttack(),
						hero.getMaxAttack(),
						hero.getDefence(),
						hero.getWeapon(),
						hero.getWeapon() == null ? 0 : hero.getWeapon().getAttack(),
						hero.getArmor(),
						hero.getArmor() == null ? 0 : hero.getArmor().getDefence(),
						hero.getHelm(),
						hero.getHelm() == null ? 0 : hero.getHelm().getHp());
			}
			connection.prepareStatement(query).execute();
			connection.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	public void deleteHero(String name) {
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			String query = String.format("DELETE FROM t_heroes WHERE f_name='%s'", name);
			connection.prepareStatement(query).execute();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
