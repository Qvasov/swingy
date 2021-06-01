package view.GUI;

import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextView extends JDialog {
	private GameController controller;
	private JFrame parent;
	private JButton next = new JButton("Next Level");
	private JButton cancel = new JButton("Cancel");
	private JButton exit = new JButton("Save & Exit");
	private JLabel message = new JLabel("Do you want leave this level?");

	public NextView(GameController controller, JFrame parent) {
		this.controller = controller;
		this.parent = parent;
		parent.setEnabled(false);
		initUI();
	}

	private void initUI() {
		setTitle("Level complete");
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.saveHero();
				parent.dispose();
				dispose();
				controller.startGame(controller.getModel().getHero());
			}
		});
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setEnabled(true);
				dispose();
				controller.ok();
			}
		});
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.saveHero();
				parent.dispose();
				dispose();
				controller.launchGame();
			}
		});

		Action switchMode = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.dispose();
				dispose();
				controller.switchMode();
			}
		};
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F4"), "switch");
		getRootPane().getActionMap().put("switch", switchMode);

		initLayout();
		pack();
		setLocationRelativeTo(parent);
		setVisible(true);
	}

	private void initLayout() {
		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.linkSize(next, cancel, exit);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
		gl.setHorizontalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(message)
				.addGroup(gl.createSequentialGroup()
						.addComponent(next)
						.addComponent(cancel)
						.addComponent(exit)
				)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
				.addComponent(message)
				.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(next)
						.addComponent(cancel)
						.addComponent(exit)
				)
		);
	}
}
