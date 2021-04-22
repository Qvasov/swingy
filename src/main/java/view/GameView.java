package view;

import controller.GameController;
import lombok.Getter;
import lombok.Setter;
import view.GUI.BattleView;
import view.GUI.GraphicView;

public class GameView {
	@Setter
	private GameController controller;
	@Getter
//	private GraphicView graphicView;
//	private ConsoleView consoleView;
	private View view;

	public GameView() {}

	public void updateView() {
		if (this.view == null) {
			this.view = new GraphicView(controller);
		}

		if (controller.getModel().isBattle()) {
			if (view instanceof GraphicView) {
				new BattleView(controller);
			}
		} else {
			view.updateView();
		}
	}
}
