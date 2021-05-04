package model.characters.enemies;

public class Bandit extends Enemy {
	private static final int defExp = 1000;
	private static final int defAtk = 20;
	private static final int defDef = 20;
	private static final int defHp = 100;

	public Bandit(int level) {
		super(Bandit.class.getSimpleName(), defAtk + level * 2, defDef + level * 2, defHp + level * 7, defExp + level * 20);
	}
}