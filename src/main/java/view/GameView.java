package view;

import controller.GameController;
import lombok.Getter;
import lombok.Setter;
import view.GUI.BattleView;
import view.GUI.GraphicView;

public class GameView implements View {
	@Setter
	private GameController controller;
	@Getter
	private GraphicView graphicView;
//	private CUI cui;
	private BattleView battleView;

	public GameView() {}

	public void updateView() {
		if (graphicView == null) {
			this.graphicView = new GraphicView(controller);
		}
		graphicView.setVisible(true);

		graphicView.updateView();
	}

	public void battleView() {
		if (battleView == null) {
			this.battleView = new BattleView(controller);
		}
		battleView.setVisible(true);
	}
}
