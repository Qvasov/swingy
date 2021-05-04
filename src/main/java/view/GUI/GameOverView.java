package view.GUI;

import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverView extends JDialog {
	private GameController controller;
	private JButton ok = new JButton("OK");
	private JLabel message = new JLabel("Game Over");

	public GameOverView(GameController controller) {
		this.controller = controller;
		initUI();
	}

	private void initUI() {
		//TODO сделать блокирование основного окна
		setTitle("Result");
		setResizable(false);
		setSize(300, 300);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
		gl.setHorizontalGroup(gl.createParallelGroup()
				.addGroup(gl.createSequentialGroup()
						.addComponent(message)
				)
				.addGroup(gl.createSequentialGroup()
						.addComponent(ok)
				)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
				.addGroup(gl.createParallelGroup()
						.addComponent(message)
				)
				.addGroup(gl.createParallelGroup()
						.addComponent(ok)
				)
		);

		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO доделать гейм овер
				dispose();
			}
		});

		pack();
		setVisible(true);
	}
}
