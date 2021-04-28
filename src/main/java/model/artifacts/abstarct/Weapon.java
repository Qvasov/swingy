package model.artifacts.abstarct;

import lombok.Getter;
import model.artifacts.Item;

public abstract class Weapon implements Item {
	@Getter
	private int attack;
}
