package view.GUI;

import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleView extends JDialog {
	private GameController controller;
	private JButton fight = new JButton("Fight");
	private JButton run = new JButton("Run");

	public BattleView(GameController controller) {
		this.controller = controller;
		initUI();
	}

	private void initUI() {
		//TODO сделать блокирование основного окна
		setTitle("Battle");
		setResizable(false);
		setLocationRelativeTo(null);
		setSize(300, 300);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(fight).addComponent(run));
		gl.setVerticalGroup(gl.createParallelGroup().addComponent(fight).addComponent(run));

		fight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.fight();
				dispose();
			}
		});

		run.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.run();
				dispose();
			}
		});

		pack();
		setVisible(true);
	}
}
