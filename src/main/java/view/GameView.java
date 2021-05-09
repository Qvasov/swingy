package view;

import controller.GameController;
import lombok.Getter;
import lombok.Setter;
import view.GUI.GraphicUI;
import view.console.ConsoleUI;

public class GameView {
	public static final int CONSOLE = 0;
	public static final int GUI = 1;
	private int mode;
	@Setter
	private GameController controller;
	@Getter
	private View view;

	public GameView(int mode) {
		this.mode = mode;
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

	public void updateView() {
		view.updateView();
	}
}
