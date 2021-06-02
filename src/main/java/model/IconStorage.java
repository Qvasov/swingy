package model;

import javax.swing.*;
import java.awt.*;

public class IconStorage {
	private static final IconStorage iconStorage = new IconStorage();
	private static final String imagesPath = "/images/";

	private IconStorage () {}

	public static IconStorage getInstance() {
		return iconStorage;
	}

	public Icon downloadImage(String unitClass) {
		return new ImageIcon(new ImageIcon(getClass().getResource(imagesPath + unitClass + ".png"))
				.getImage().getScaledInstance(48,48, Image.SCALE_SMOOTH));
	}
}
