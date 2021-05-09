package view.GUI;

import controller.GameController;
import model.DataBase;
import model.characters.heroes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

public class HeroPickGuiView extends JFrame {
	private GameController controller;
	private JRadioButton create = new JRadioButton("Create Hero", true);
	private JRadioButton load = new JRadioButton("Load Hero", false);
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel classLabel = new JLabel("Class:");
	private JComboBox<String> heroClass = new JComboBox<>();
	private JLabel nameLabel = new JLabel("Name:");
	private JComboBox<String> heroName = new JComboBox<>();
	private JLabel stats = new JLabel("Stats");
	private JLabel levelLbl = new JLabel("Level:");
	private JLabel expLbl = new JLabel("Experience:");
	private JLabel attackLbl = new JLabel("Attack:");
	private JLabel defenceLbl = new JLabel("Defence:");
	private JLabel hpLbl = new JLabel("Hit points:");
	private JLabel level = new JLabel();
	private JLabel exp = new JLabel();
	private JLabel attack = new JLabel();
	private JLabel defence = new JLabel();
	private JLabel hp = new JLabel();
	private JButton start = new JButton("Start");
	private JButton exit = new JButton("Exit");

	public HeroPickGuiView(GameController controller) {
		this.controller = controller;
		initGUI();
	}

	private void initGUI() {
		setTitle("Hero pick");
		int size = 320;
		setSize(size, size);
		setPreferredSize(new Dimension(size, size - 100));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container container = getContentPane();
		final GroupLayout gl = new GroupLayout(container);
		container.setLayout(gl);
		initLayout(gl);

		buttonGroup.add(create);
		buttonGroup.add(load);
		downloadClasses();
		heroName.setEditable(true);
		heroName.setPreferredSize(new Dimension(size / 3 ,0));

		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				heroClass.setEnabled(true);
				heroClass.setSelectedItem(null);
				heroName.setEditable(true);
				heroName.setSelectedItem(null);
				heroName.removeAllItems();
			}
		});

		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearStats();
				for (String name : DataBase.getInstance().getHeroNames()) {
					heroName.addItem(name);
				}
				heroClass.setEnabled(false);
				heroClass.setSelectedItem(null);
				heroName.setEditable(false);
				heroName.setSelectedItem(null);
			}
		});

		heroClass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (create.isSelected()) {
					if (heroClass.getSelectedItem() != null) {
						Map<String, Integer> stats = HeroBuilder.getInstance().getStats(String.valueOf(heroClass.getSelectedItem()));
						level.setText(stats.get("level").toString());
						exp.setText(stats.get("experience").toString());
						attack.setText(stats.get("minAttack").toString() + " - " + stats.get("maxAttack").toString());
						defence.setText(stats.get("defence").toString());
						hp.setText(stats.get("hp").toString());
					}
				}
			}
		});

		heroName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (load.isSelected()) {
					if (heroClass.getSelectedItem() != null) {

					}
				}
			}
		});

		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (create.isSelected()) {
					controller.startGame(HeroBuilder.getInstance().createHero(
							String.valueOf(heroClass.getSelectedItem()),
							String.valueOf(heroName.getSelectedItem())));
				} else if (load.isSelected()) {
//					controller.startGame(HeroBuilder.getInstance().loadHero(
//							String.valueOf(heroNameCb.getSelectedItem())));
				}
				dispose();
			}
		});

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.saveHeroes();
				dispose();
			}
		});

		pack();
		setVisible(true);
	}

	private void downloadClasses() {
		heroClass.removeAllItems();
		for (String hero : HeroBuilder.getInstance().getHeroes()) {
			heroClass.addItem(hero);
		}
		heroClass.setSelectedItem(null);
	}

	private void clearStats() {
		level.setText(null);
		exp.setText(null);
		attack.setText(null);
		defence.setText(null);
		hp.setText(null);
	}

	private void initLayout(GroupLayout gl) {
		gl.linkSize(start, exit);
		gl.linkSize(heroClass, heroName);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
		gl.setHorizontalGroup(gl.createSequentialGroup()
				.addGroup(gl.createParallelGroup()
						.addComponent(create)
						.addGroup(gl.createSequentialGroup()
								.addGroup(gl.createParallelGroup()
										.addComponent(classLabel)
										.addComponent(nameLabel)
								)
								.addGroup(gl.createParallelGroup()
										.addComponent(heroName)
										.addComponent(heroClass)
								)
						)
						.addComponent(start)
						.addComponent(exit)
				)
				.addGroup(gl.createParallelGroup()
						.addComponent(load)
						.addGroup(gl.createParallelGroup()
								.addComponent(stats)
								.addGroup(gl.createSequentialGroup()
									.addGroup(gl.createParallelGroup()
											.addComponent(levelLbl)
											.addComponent(expLbl)
											.addComponent(attackLbl)
											.addComponent(defenceLbl)
											.addComponent(hpLbl)
									)
									.addGroup(gl.createParallelGroup(GroupLayout.Alignment.TRAILING)
											.addComponent(level)
											.addComponent(exp)
											.addComponent(attack)
											.addComponent(defence)
											.addComponent(hp)
									)
								)
						)
				)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
				.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(create)
						.addComponent(load)
				)
				.addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(gl.createSequentialGroup()
								.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(nameLabel)
										.addComponent(heroName)
								)
								.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(classLabel)
										.addComponent(heroClass)
								)
								.addComponent(start)
								.addComponent(exit)
						)
						.addGroup(gl.createSequentialGroup()
								.addComponent(stats)
								.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(levelLbl)
										.addComponent(level)
								)
								.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(expLbl)
										.addComponent(exp)
								)
								.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(attackLbl)
										.addComponent(attack)
								)
								.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(defenceLbl)
										.addComponent(defence)
								)
								.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(hpLbl)
										.addComponent(hp)
								)
						)
				)
		);
	}
}
