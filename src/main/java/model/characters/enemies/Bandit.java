package model.characters.enemies;

public class Bandit extends Enemy {
	private static final int defExp = 1000;
	private static final int defAtk = 30;
	private static final int defDef = 15;
	private static final int defHp = 80;

	public Bandit(int level) {
		super(Bandit.class.getSimpleName(), defAtk + level * 2, defDef + level * 2, defHp + level * 7, defExp + level * 20);
	}
}
