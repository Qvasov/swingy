package controller;

import lombok.Getter;
import model.GameModel;
import model.characters.heroes.Hero;
import view.GameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener {
	@Getter
	private final GameModel model;
	private final GameView view;

	public GameController(GameModel model, GameView view) {
		this.model = model;
		this.view = view;
		view.setController(this);
	}

	public void launchGame() {
		view.heroPick();
	}

	public void startGame(Hero hero) {
		model.downloadMap(hero);
		view.newGameMap();
	}

	public void fight() {
		model.fight();
		view.updateView();
	}

	public void run() {
		if (model.run()) {
			view.updateView();
		} else {
			fight();
		}
	}

	public void equipItem() {
		model.equipItem();
		view.updateView();
	}

	public void ok() {
		model.ok();
		view.updateView();
	}

	public void saveHeroes() {
		//TODO сделать сохранение
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE:
				break;
			case KeyEvent.VK_UP:
				model.moveUp();
				break;
			case KeyEvent.VK_RIGHT:
				model.moveRight();
				break;
			case KeyEvent.VK_DOWN:
				model.moveDown();
				break;
			case KeyEvent.VK_LEFT:
				model.moveLeft();
				break;
		}
		view.updateView();
	}

	public static void main(String[] args) {
		/*if (args.length != 1) {
			System.out.println("usage: java -jar swingy.jar (console | gui)");
			return;
		}*/

		//Обработка флагов

		new GameController(new GameModel(), new GameView(GameView.CONSOLE)).launchGame();
	}
}
