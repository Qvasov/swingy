package model;

import model.characters.heroes.Hero;
import view.View;

import java.util.LinkedList;
import java.util.List;

public class GameModelFacade {
	private final GameModel model;
	private final List<View> views;

	public GameModelFacade () {
		this.model = new GameModel();
		this.views = new LinkedList<>();
	}

	public void notify() {}

	public void addView(View view) {
		views.add(view);
	}

	public void removeView(View view) {
		views.remove(view);
	}

	public void setHero(Hero hero) {
		model.setHero(hero);
	}

	public void downloadMap() {
		model.map = new Map(hero);
		hero.setPosition(new Point(map.getSize() / 2 + 1, map.getSize() / 2 + 1));
		map.downloadEnemies();
	}

}
