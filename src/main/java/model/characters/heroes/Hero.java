package model.characters.heroes;

import lombok.Getter;
import lombok.Setter;
import model.Unit;
import model.artifacts.abstarct.Armor;
import model.artifacts.abstarct.Helm;
import model.artifacts.abstarct.Weapon;


public class Hero extends Unit {
	@Getter
	int level;
	@Setter
	@Getter
	int exp;
	@Getter
	int expToNextLvl;

	@Setter
	private Helm helm;
	@Setter
	private Armor armor;
	@Setter
	private Weapon weapon;

	public Hero() {
		super();
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
			this.level += 1;
			setExpToNextLvl();
		}
	}

	@Override
	public int getHp() {
		return super.getHp() + helm.getHp();
	}

	@Override
	public int dealDamage() {
		return getAttack() + weapon.getAttack();
	}

	@Override
	public int receiveDamage(int damage) {
		damage -= getDefence();
		setHp(getHp() - damage);
		return damage;
	}
}
