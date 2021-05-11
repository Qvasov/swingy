package view.GUI;

import controller.GameController;
import view.View;

public class GraphicUI implements View {
	private GraphicView graphicView;
	private HeroPickGuiView heroPickGuiView;

	@Override
	public void heroPick(GameController controller) {
		if (graphicView != null) graphicView.dispose();
		graphicView = null;
		this.heroPickGuiView = new HeroPickGuiView(controller);
	}

	@Override
	public void newGameMap(GameController controller) {
		if (heroPickGuiView != null) heroPickGuiView.dispose();
		heroPickGuiView = null;
		this.graphicView = new GraphicView(controller);
	}

	@Override
	public void updateView() {
		graphicView.updateView();
	}

	@Override
	public void error(String message) {
		if (heroPickGuiView != null) {
			new ErrorView(message, heroPickGuiView);
		} else {
			new ErrorView(message, graphicView);
		}
	}
}
