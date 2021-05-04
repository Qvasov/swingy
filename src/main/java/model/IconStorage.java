package model;

import javax.swing.*;
import java.awt.*;

public class IconStorage {
//	private static final HashMap<Object, Icon> storage = new HashMap<Object, Icon>()
//	{{
//		put(EmptyIcon.class, downloadImage("src/main/resources/images/Empty.png"));
//		put(Enemy.class, downloadImage("src/main/resources/images/Bandit.png"));
//		put(Hero.class, downloadImage("src/main/resources/images/Warrior.png"));
//	}};
	private static final String imagesPath = "src/main/resources/images/";

	private IconStorage () {}

//	public static HashMap<Object, Icon> getInstance() {
//		return storage;
//	}

	public static Icon downloadImage(String unitClass) {
		return new ImageIcon(new ImageIcon(imagesPath + unitClass + ".png")
				.getImage().getScaledInstance(48,48, Image.SCALE_SMOOTH));
	}
}
