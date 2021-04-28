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

	public void updateView() {
		if (this.view == null) {
			this.view = new GraphicView(controller);
		} else {
			view.updateView();
		}
	}
}
