package view.GUI;

import controller.GameController;
import model.characters.enemies.Enemy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttackView extends JDialog {
	private final GameController controller;
	private final JFrame parent;
	private final JButton fight = new JButton("Fight");
	private final JButton run = new JButton("Run");
	private final JLabel enemyIcon = new JLabel();
	private final JPanel enemyStats = new JPanel();

	public AttackView(GameController controller, JFrame parent) {
		this.controller = controller;
		this.parent = parent;
		parent.setEnabled(false);
		initUI();
	}

	private void initUI() {
		setTitle("Battle");
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		enemyIcon.setIcon(controller.getModel().getEnemy().getIcon());
		initEnemyStats();
		fight.setToolTipText("Fight");
		run.setToolTipText("50% chance to avoid the fight");

		fight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setEnabled(true);
				dispose();
				controller.fight();
			}
		});

		run.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setEnabled(true);
				dispose();
				controller.run();
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

	private void initEnemyStats() {
		Enemy enemy = controller.getModel().getEnemy();
		GridLayout gl = new GridLayout(0, 2);
		enemyStats.setLayout(gl);
		enemyStats.add(new JLabel("Name: "));
		enemyStats.add(new JLabel(enemy.getName()));
		enemyStats.add(new JLabel("Exp: "));
		enemyStats.add(new JLabel(String.valueOf(enemy.getExp())));
		enemyStats.add(new JLabel("HP: "));
		enemyStats.add(new JLabel(String.valueOf(enemy.getHp())));
		enemyStats.add(new JLabel("Attack: "));
		enemyStats.add(new JLabel(enemy.getMinAttack() + " - " + enemy.getMaxAttack()));
		enemyStats.add(new JLabel("Defence: "));
		enemyStats.add(new JLabel(String.valueOf(enemy.getDefence())));
	}

	private void initLayout() {
		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.linkSize(fight, run);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
		gl.setHorizontalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(enemyIcon)
				.addComponent(enemyStats)
				.addGroup(gl.createSequentialGroup()
						.addComponent(fight)
						.addComponent(run)
				)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
				.addComponent(enemyIcon)
				.addComponent(enemyStats)
				.addGroup(gl.createParallelGroup()
						.addComponent(fight)
						.addComponent(run)
				)
		);
	}
}
