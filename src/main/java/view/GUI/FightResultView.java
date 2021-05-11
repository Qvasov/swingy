package view.GUI;

import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FightResultView extends JDialog {
	private GameController controller;
	private JFrame parent;
	private JButton ok = new JButton("OK");
	private JButton keep = new JButton("Keep");
	private JTextArea log = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(log);
	private JLabel itemNewIcon = new JLabel();
	private JLabel itemNewDescription = new JLabel();

	public FightResultView(GameController controller, JFrame parent) {
		this.controller = controller;
		this.parent = parent;
		parent.setEnabled(false);
		initUI();
	}

	private void initUI() {
		setTitle("Result");
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		log.setEditable(false);
		log.setRows(8);
		log.setText(controller.getModel().getBattleLog());

		if (controller.getModel().getItem() != null) {
			itemNewIcon.setIcon(controller.getModel().getItem().getIcon());
			itemNewDescription.setText(controller.getModel().getItem().getStats());
			keep.setToolTipText("Keep item");
			ok.setText("Leave");
			ok.setToolTipText("Leave item");
		} else if (controller.getModel().getHero().isDead()) {
			itemNewDescription.setText("GAME OVER");
			keep.setEnabled(false);
			keep.setVisible(false);
		} else {
			keep.setEnabled(false);
			keep.setVisible(false);
		}

		keep.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setEnabled(true);
				dispose();
				controller.equipItem();
			}
		});

		if (controller.getModel().getHero().isDead()) {
			ok.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					parent.dispose();
					dispose();
					controller.launchGame();
				}
			});
		} else {
			ok.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					parent.setEnabled(true);
					dispose();
					controller.ok();
				}
			});
		}

		initLayout();
		pack();
		setLocationRelativeTo(parent);
		setVisible(true);
	}

	private void initLayout() {
		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.linkSize(keep, ok);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);

		gl.setHorizontalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(scrollPane)
				.addComponent(itemNewIcon)
				.addComponent(itemNewDescription)
				.addGroup(gl.createSequentialGroup()
						.addComponent(keep)
						.addComponent(ok)
				)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
				.addComponent(scrollPane)
				.addComponent(itemNewIcon)
				.addComponent(itemNewDescription)
				.addGroup(gl.createParallelGroup()
						.addComponent(keep)
						.addComponent(ok)
				)
		);
	}
}
