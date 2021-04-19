package model.characters.heroes;

import model.Storage;

public class HeroStorage extends Storage<Hero> {
	private static final HeroStorage storage = new HeroStorage();

	private HeroStorage() {
		super();
	}

	public static HeroStorage getInstance()
	{
		return storage;
	}

	@Override
	public void download() {
		data.add(new Warrior());
		data.add(new Archer());
	}
}
