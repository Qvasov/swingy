package model.characters.heroes;

import model.artifacts.Light;
import model.artifacts.Plate;
import model.artifacts.Sword;

public class HeroBuilder {
	private static final HeroBuilder heroBuilder = new HeroBuilder();

	private HeroBuilder() {
	}

	public static HeroBuilder getInstance() {
		return heroBuilder;
	}

	public Hero createHero() {
		Hero hero = new Hero();
		hero.setHelm(new Light());
		hero.setArmor(new Plate());
		hero.setWeapon(new Sword());
		return hero;
	}
}
