package model.artifacts.abstarct;

import lombok.Getter;
import model.artifacts.Item;

public abstract class Armor implements Item {
	@Getter
	private int defence;
}
