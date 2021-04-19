package view.GUI;

import controller.GameController;
import model.characters.heroes.HeroBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HeroPickView extends JFrame {
	private GameController controller;
	private JButton createHero= new JButton("Create Hero");
	private JButton loadHero = new JButton("Load Hero");
	private JButton startGame = new JButton("Start");
	private JTextField heroName = new JTextField("Name");

	public HeroPickView(GameController controller) {
		this.controller = controller;
		initGUI();
	}

	private void initGUI() {
		setTitle("Hero pick");
		setSize(300, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//TODO переделать выбор героя из базы данных через DB manager
		createHero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.getModelFacade().setHero(HeroBuilder.getInstance().createHero());
			}
		});

		startGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.startGame();
				setVisible(false);
			}
		});

		Container container = getContentPane();
		GroupLayout gl = new GroupLayout(container);
		container.setLayout(gl);
		gl.setHorizontalGroup(gl.createSequentialGroup()
				.addComponent(createHero)
				.addComponent(startGame)
		);
		gl.setVerticalGroup(gl.createParallelGroup()
				.addComponent(createHero)
				.addComponent(startGame));
	}
}
