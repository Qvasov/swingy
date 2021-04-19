package controller;

import lombok.Getter;
import model.GameModel;
import model.characters.enemies.Enemy;
import view.GUI.HeroPickView;
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
		model.setController(this);
	}

	public void launchGame() {
		new HeroPickView(this).setVisible(true);
	}

	public void startGame() {
		model.downloadMap();
		view.updateView();
	}

	public void fight() {
		model.fight();
	}

	public void run() {
		model.run();
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
				System.exit(0);
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
		checkBattle();
	}

	public void checkBattle() {
		if (model.checkBattle()) {
			view.battleView();
		}
	}
}
