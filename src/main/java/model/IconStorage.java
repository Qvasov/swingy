package model;

import javax.swing.*;
import java.awt.*;

public class IconStorage {
	private static final String imagesPath = "src/main/resources/images/";

	private IconStorage () {}

	public static Icon downloadImage(String unitClass) {
		return new ImageIcon(new ImageIcon(imagesPath + unitClass + ".png")
				.getImage().getScaledInstance(48,48, Image.SCALE_SMOOTH));
	}
}
