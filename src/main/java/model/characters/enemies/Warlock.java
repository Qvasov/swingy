package model.characters.enemies;

public class Warlock extends Enemy {
	private static final int defExp = 2000;
	private static final int defMinAtk = 35;
	private static final int defMaxAtk = 40;
	private static final int defDef = 15;
	private static final int defHp = 150;

	public Warlock(int level) {
		super(Warlock.class.getSimpleName(), defMinAtk + level * 3, defMaxAtk + level * 3, defDef + level * 3, defHp + level * 10, defExp + level * 20);
	}

	@Override
	public String getSymbol() {
		return "W";
	}
}
