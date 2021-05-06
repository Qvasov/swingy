package view.GUI;

import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverView extends JDialog {
	private GameController controller;
	private JFrame parent;
	private JButton exit = new JButton("Exit");
	private JButton next = new JButton("Next Level");
	private JLabel message = new JLabel("Game Over");

	public GameOverView(GameController controller, JFrame parent) {
		this.controller = controller;
		this.parent = parent;
		parent.setEnabled(false);
		initUI();
	}

	private void initUI() {
		setTitle("Result");
		setResizable(false);
		setSize(216, 0);
		setLocationRelativeTo(parent);
		setUndecorated(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		initLayout();

		if (controller.getModel().getHero().isDead()) {
			next.setEnabled(false);
			next.setVisible(false);
			exit.setText("OK");
			setSize(88, 0);
			setLocationRelativeTo(parent);
		}

		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				parent.dispose();
				controller.startGame(controller.getModel().getHero());
			}
		});
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				parent.dispose();
				controller.launchGame();
			}
		});

		pack();
		setVisible(true);
	}

	private void initLayout() {
		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.linkSize(exit, next);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
		gl.setHorizontalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(message)
				.addGroup(gl.createSequentialGroup()
						.addComponent(next)
						.addComponent(exit)
				)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
				.addComponent(message)
				.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(next)
						.addComponent(exit)
				)
		);
	}
}
