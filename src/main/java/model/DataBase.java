package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBase {
	private static DataBase dataBase = new DataBase();
//	private Connection connection;

	private DataBase() {}

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
		} catch (IOException e) {
			System.exit(-1);
		}
		return result;
	}

	public void getHero(String name) {

	}
}
