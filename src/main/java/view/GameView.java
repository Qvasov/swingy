package view;

import controller.GameController;
import lombok.Getter;
import lombok.Setter;
import model.State;
import view.GUI.GraphicView;

public class GameView {
	@Setter
	private GameController controller;
	@Getter
//	private GraphicView graphicView;
//	private ConsoleView consoleView;
	private View view;

	public GameView() {
	}

	public void newGameMap() {
		this.view = new GraphicView(controller);
	}

	public void updateView() {
		view.updateView();
	}
}
