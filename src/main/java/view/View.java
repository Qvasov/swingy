package view;

import controller.GameController;

public interface View {
	void heroPick(GameController controller);
	void newGameMap(GameController controller);
	void updateView(GameController controller);
	void error(String message);
}
