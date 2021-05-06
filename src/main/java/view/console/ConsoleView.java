package view.console;

import controller.GameController;
import view.View;

public class ConsoleView implements View {
	private final GameController controller;

	public ConsoleView(GameController controller) {
		this.controller = controller;
	}

	@Override
	public void updateView() {

	}
}
