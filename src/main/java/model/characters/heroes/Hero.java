package model.characters.heroes;

import lombok.Getter;
import lombok.Setter;
import model.Unit;
import model.items.abstarct.Armor;
import model.items.abstarct.Helm;
import model.items.abstarct.Weapon;

import javax.validation.Valid;

@Valid
@Getter
public abstract class Hero extends Unit {
	protected int level;
	private int exp;
	private int expToNextLvl;

	@Setter
	private Helm helm;
	@Setter
	private Armor armor;
	@Setter
	private Weapon weapon;

	protected Hero(String name, int level, int exp, int minAttack, int maxAttack, int defence, int hp) {
		super(name, minAttack, maxAttack, defence, hp);
		this.level = level;
		this.exp = exp;
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

	public int getFullHp() {
		return super.getHp() + ((helm != null) ? helm.getHp() : 0);
	}

	@Override
	public int dealDamage() {
		return super.dealDamage() + ((weapon != null) ? weapon.getAttack() : 0);
	}

	@Override
	public int receiveDamage(int damage) {
		return super.receiveDamage(damage - ((armor != null) ? armor.getDefence() : 0));
	}

	public abstract void levelUp();

	@Override
	public String getSymbol() {
		return "H";
	}
}
