package controller;

import lombok.Getter;
import lombok.NonNull;
import model.GameModel;
import model.characters.heroes.Hero;
import view.GameView;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;

public class GameController implements KeyListener {
	@Getter
	private final GameModel model;
	private final GameView view;
	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	public GameController(GameModel model, GameView view) {
		this.model = model;
		this.view = view;
		view.setController(this);
	}

	public void launchGame() {
		view.heroPick();
	}

	public void startGame(@NonNull Hero hero) {
		Set<ConstraintViolation<Hero>> errors = validator.validate(hero);
		if (errors.size() > 0) {
			StringBuilder message = new StringBuilder();
			for (ConstraintViolation<Hero> e : errors) {
				message.append("Property: ").append(e.getPropertyPath()).append(", ")
						.append("value: ").append(e.getInvalidValue()).append(", ")
						.append("message: ").append(e.getMessage()).append(".\n");
			}
			view.error(message.deleteCharAt(message.length() - 1).toString());
		} else {
			model.downloadMap(hero);
			view.newGameMap();
		}
	}

	public void fight() {
		model.fight();
		view.updateView();
	}

	public void run() {
		if (model.run()) {
			view.updateView();
		} else {
			fight();
		}
	}

	public void equipItem() {
		model.equipItem();
		view.updateView();
	}

	public void ok() {
		model.ok();
		view.updateView();
	}

	public void saveHeroes() {
		//TODO сделать сохранение
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				model.moveUp();
				break;
			case KeyEvent.VK_RIGHT:
				model.moveRight();
				break;
			case KeyEvent.VK_DOWN:
				model.moveDown();
				break;
			case KeyEvent.VK_LEFT:
				model.moveLeft();
				break;
		}
		view.updateView();
	}

	public static void main(String[] args) {
		String usage = "usage: java -jar swingy.jar (console | gui)";
		if (args.length != 1) {
			System.out.println(usage);
			return;
		}

		int mode;
		if (args[0].equals("console")) {
			mode = GameView.CONSOLE;
		} else if (args[0].equals("gui")) {
			mode = GameView.GUI;
		} else {
			System.out.println(usage);
			return;
		}

		new GameController(new GameModel(), new GameView(mode)).launchGame();
	}
}
