package controller;

import lombok.Getter;
import model.GameModel;
import model.GameModelFacade;
import view.GUI.HeroPickView;
import view.GameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener {
	@Getter
	private final GameModelFacade modelFacade;
	private final GameView view;

	public GameController(GameModelFacade modelFacade, GameView view) {
		this.modelFacade = modelFacade;
		this.view = view;
		view.setController(this);
	}

	public void launchGame() {
		new HeroPickView(this).setVisible(true);
	}

	public void startGame() {
		modelFacade.downloadMap();
		view.updateView();
	}

	public void fight() {
		modelFacade.fight();
	}

	public void run() {
		modelFacade.run();
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
				modelFacade.moveUp();
				break;
			case KeyEvent.VK_RIGHT:
				modelFacade.moveRight();
				break;
			case KeyEvent.VK_DOWN:
				modelFacade.moveDown();
				break;
			case KeyEvent.VK_LEFT:
				modelFacade.moveLeft();
				break;
		}
		view.updateView();
		checkBattle();
	}

	public void checkBattle() {
		if (modelFacade.checkBattle()) {
			view.battleView();
		}
	}
}
