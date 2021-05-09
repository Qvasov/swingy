package model.characters.enemies;

public class Bandit extends Enemy {
	private static final int defExp = 800;
	private static final int defMinAtk = 25;
	private static final int defMaxAtk = 28;
	private static final int defDef = 8;
	private static final int defHp = 85;

	public Bandit(int level) {
		super(Bandit.class.getSimpleName(), defMinAtk + level * 2, defMaxAtk + level * 2, defDef + level * 2, defHp + level * 7, defExp + level * 20);
	}

	@Override
	public String getSymbol() {
		return "B";
	}
}
