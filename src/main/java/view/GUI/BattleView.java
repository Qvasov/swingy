package view.GUI;

import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleView extends JFrame {
	private GameController controller;
	private JButton fight = new JButton("Fight");
	private JButton run = new JButton("Run");

	public BattleView(GameController controller) {
		this.controller = controller;
		initUI();
	}

	private void initUI() {
		setTitle("Battle");
		setResizable(false);
		setLocationRelativeTo(null);
		setSize(300, 300);

		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(fight).addComponent(run));
		gl.setVerticalGroup(gl.createParallelGroup().addComponent(fight).addComponent(run));

		fight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.fight();
				setVisible(false);
			}
		});

		run.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.run();
				setVisible(false);
			}
		});
	}
}
