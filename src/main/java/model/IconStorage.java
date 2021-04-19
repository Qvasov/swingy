package model;

import model.characters.enemies.Enemy;
import model.characters.heroes.Hero;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class IconStorage {
	private static final HashMap<Object, Icon> storage = new HashMap<Object, Icon>()
	{{
		put(EmptyIcon.class, downloadImage("src/main/resources/images/Empty.jpg"));
		put(Enemy.class, downloadImage("src/main/resources/images/Enemy.png"));
		put(Hero.class, downloadImage("src/main/resources/images/Hero.png"));
	}};

	private IconStorage () {}

	public static HashMap<Object, Icon> getInstance() {
		return storage;
	}

	private static Icon downloadImage(String path) {
		return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(48,48, Image.SCALE_SMOOTH));
	}
}
