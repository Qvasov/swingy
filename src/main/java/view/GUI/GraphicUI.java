package view.GUI;

import controller.GameController;
import view.View;

public class GraphicUI implements View {
	private GraphicView graphicView;

	@Override
	public void heroPick(GameController controller) {
		new HeroPickGuiView(controller);
	}

	@Override
	public void newGameMap(GameController controller) {
		this.graphicView = new GraphicView(controller);
	}

	@Override
	public void updateView() {
		graphicView.updateView();
	}
}
