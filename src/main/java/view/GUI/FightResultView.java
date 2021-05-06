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
		setSize(190, 120);
		setLocationRelativeTo(parent);
		setUndecorated(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		initLayout();

		log.setEditable(false);
		log.setRows(5);
		log.setText(controller.getModel().getBattleLog());

		if (controller.getModel().getItem() != null) {
			itemNewIcon.setIcon(controller.getModel().getItem().getIcon());
			itemNewDescription.setText(controller.getModel().getItem().getStats());
			keep.setToolTipText("Keep item");
			ok.setText("Leave");
			ok.setToolTipText("Leave item");
		} else {
			keep.setEnabled(false);
			keep.setVisible(false);
		}

		keep.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.equipItem();
				dispose();
				parent.setEnabled(true);
			}
		});

		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.ok();
				dispose();
				parent.setEnabled(true);
			}
		});

		pack();
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
