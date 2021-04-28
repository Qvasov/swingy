package model.artifacts.abstarct;

import lombok.Getter;
import model.artifacts.Item;

public abstract class Helm implements Item {
	@Getter
	private int hp;
}
