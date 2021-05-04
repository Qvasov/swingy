package model.characters.heroes;

import lombok.Getter;
import lombok.Setter;
import model.Unit;
import model.artifacts.abstarct.Armor;
import model.artifacts.abstarct.Helm;
import model.artifacts.abstarct.Weapon;

public abstract class Hero extends Unit {
	@Getter
	protected int level;
	@Getter
	private int exp;
	@Getter
	private int expToNextLvl;

	@Setter
	private Helm helm;
	@Setter
	private Armor armor;
	@Setter
	private Weapon weapon;

	public Hero(String name, int attack, int defence, int hp) {
		super(name, attack, defence, hp);
		setExpToNextLvl();
	}

	private void setExpToNextLvl() {
		int nextLvl = this.level + 1;
		this.expToNextLvl = nextLvl * 1000 + (nextLvl * nextLvl - 2 * nextLvl + 1) * 450;
	}

	public void receiveExp(int exp) {
		this.exp += exp;
		if (this.exp >= this.expToNextLvl) {
			this.exp -= this.expToNextLvl;
			levelUp();
			setExpToNextLvl();
		}
	}

	@Override
	public int getHp() {
		return super.getHp() + ((helm != null) ? helm.getHp() : 0);
	}

	@Override
	public int dealDamage() {
		return getAttack() + ((weapon != null) ? weapon.getAttack() : 0);
	}

	@Override
	public int receiveDamage(int damage) {
		return super.receiveDamage(damage - ((armor != null) ? armor.getDefence() : 0));
	}

	public abstract void levelUp();
}
