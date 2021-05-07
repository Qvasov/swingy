package view;

import com.sun.xml.internal.bind.v2.TODO;
import controller.GameController;
import lombok.Getter;
import lombok.Setter;
import view.GUI.GraphicView;
import view.GUI.HeroPickGuiView;
import view.console.ConsoleView;
import view.console.HeroPickConsoleView;

public class GameView {
	public static final int CONSOLE = 0;
	public static final int GUI = 1;
	private int mode;
	@Setter
	private GameController controller;
	@Getter
//	private GraphicView graphicView;
//	private ConsoleView consoleView;
	private View view;


	//TODO Продвмать интерфейсы
	public GameView(int mode) {
		this.mode = mode;
	}

	public void heroPick() {
		switch (mode) {
			case GUI:
				new HeroPickGuiView(controller);
				break;
			case CONSOLE:
				new HeroPickConsoleView(controller);
				break;
		}
	}

	public void newGameMap() {
		switch (mode) {
			case GUI:
				this.view = new GraphicView(controller);
				break;
			case CONSOLE:
				this.view = new ConsoleView(controller);
				break;
		}
	}

	public void updateView() {
		view.updateView();
	}
}
