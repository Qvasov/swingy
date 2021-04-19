package controller;

import model.GameModel;
import view.GameView;

public class Main {
	public static void main(String[] args) {
		GameController controller;

		/*if (args.length != 1) {
			System.out.println("usage: java -jar swingy.jar (console | gui)");
			return;
		}*/

		//Обработка фалгов

		controller = new GameController(new GameModel(), new GameView());

		controller.launchGame();
	}
}
