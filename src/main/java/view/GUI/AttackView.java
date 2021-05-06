package view.GUI;

import controller.GameController;
import model.characters.enemies.Enemy;
import view.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttackView extends JDialog {
	private GameController controller;
	private JFrame parent;
	private JButton fight = new JButton("Fight");
	private JButton run = new JButton("Run");
	private JLabel enemyIcon = new JLabel();
	private JPanel enemyStats = new JPanel();

	public AttackView(GameController controller, JFrame parent) {
		this.controller = controller;
		this.parent = parent;
		parent.setEnabled(false);
		initUI();
	}

	private void initUI() {
		setTitle("Battle");
		setResizable(false);
		setSize(160, 100);
		setLocationRelativeTo(parent);
		setUndecorated(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		enemyIcon.setIcon(controller.getModel().getEnemy().getIcon());
		initEnemyStats();
		fight.setToolTipText("Fight");
		run.setToolTipText("Try to avoid the fight (50% chance)");

		initLayout();

		fight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.fight();
				dispose();
				parent.setEnabled(true);
			}
		});

		run.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.run();
				dispose();
				parent.setEnabled(true);
			}
		});

		pack();
		setVisible(true);
	}

	private void initEnemyStats() {
		Enemy enemy = controller.getModel().getEnemy();
		GridLayout gl = new GridLayout(0, 2);
		enemyStats.setLayout(gl);
		enemyStats.add(new JLabel("Name: "));
		enemyStats.add(new JLabel(enemy.getName()));
		enemyStats.add(new JLabel("HP: "));
		enemyStats.add(new JLabel(String.valueOf(enemy.getHp())));
		enemyStats.add(new JLabel("Attack: "));
		enemyStats.add(new JLabel(String.valueOf(enemy.getAttack())));
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
