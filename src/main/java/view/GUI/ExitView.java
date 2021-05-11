package view.GUI;

import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExitView extends JDialog {
	private GameController controller;
	private JFrame parent;
	private JLabel message = new JLabel("Save before leaving?");
	private JButton yes = new JButton("Yes");
	private JButton no = new JButton("Don't save");
	private JButton cancel = new JButton("Cancel");

	public ExitView(JFrame parent, GameController controller) {
		this.controller = controller;
		this.parent = parent;

		parent.setEnabled(false);
		initUI();
	}

	public void initUI() {
		setTitle("Exit to main menu");
		setResizable(false);
		initLayout();
		pack();
		setLocationRelativeTo(parent);
		setVisible(true);

		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.saveHeroes();
				parent.dispose();
				dispose();
				controller.launchGame();
			}
		});

		no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.dispose();
				dispose();
				controller.launchGame();
			}
		});

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setEnabled(true);
				dispose();
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				parent.setEnabled(true);
				dispose();
			}
		});
	}

	private void initLayout() {
		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.linkSize(yes, no, cancel);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
		gl.setHorizontalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(message)
				.addGroup(gl.createSequentialGroup()
						.addComponent(yes)
						.addComponent(no)
						.addComponent(cancel)
				)

		);
		gl.setVerticalGroup(gl.createSequentialGroup()
				.addComponent(message)
				.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(yes)
						.addComponent(no)
						.addComponent(cancel)
				)
		);
	}
}
