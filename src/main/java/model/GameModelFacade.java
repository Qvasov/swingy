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

	public void notifyAllView() {}

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
		model.downloadMap();
	}

	public void moveUp() {
		model.move(0, -1);
	}

	public void moveRight() {
		model.move(1, 0);
	}

	public void moveDown() {
		model.move(0, 1);
	}

	public void moveLeft() {
		model.move(-1, 0);
	}
}
