package view;

import controller.GameController;
import lombok.Getter;
import lombok.Setter;
import model.State;
import view.GUI.GraphicUI;
import view.console.ConsoleUI;

public class GameView {
	public static final int CONSOLE = 0;
	public static final int GUI = 1;
	@Setter
	private int mode;
	@Setter
	private GameController controller;
	@Getter
	private View view;

	public GameView(int mode) {
		this.mode = mode;
		initUI();
	}

	private void initUI() {
		switch (mode) {
			case GUI:
				view = new GraphicUI();
				break;
			case CONSOLE:
				view = new ConsoleUI();
				break;
		}
	}

	public void heroPick() {
		view.heroPick(controller);
	}

	public void newGameMap() {
		view.newGameMap(controller);
	}

	public void gameUpdate() {
		view.updateView(controller);
	}

	public void error(String message) {
		view.error(message);
	}

	public void switchMode() {
		mode = (mode == CONSOLE) ? GUI : CONSOLE;
		initUI();

		State state = controller.getModel().getState();
		switch (state) {
			case PICK_HERO:
				view.heroPick(controller);
				break;
			case MOVEMENT:
			case ATTACK:
			case FIGHT_LOG:
			case NEXT:
			case EXIT:
				view.updateView(controller);
				break;
		}
	}
}
