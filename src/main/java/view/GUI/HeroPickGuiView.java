package view.GUI;

import controller.GameController;
import model.DataBase;
import model.characters.heroes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

public class HeroPickGuiView extends JFrame {
	private final GameController controller;
	private final JFrame cur;
	private final JRadioButton create = new JRadioButton("Create Hero", true);
	private final JRadioButton load = new JRadioButton("Load Hero", false);
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final JLabel classLabel = new JLabel("Class:");
	private final DefaultComboBoxModel<String> heroClassModel = new DefaultComboBoxModel<>();
	private final JComboBox<String> heroClass = new JComboBox<>();
	private final JLabel nameLabel = new JLabel("Name:");
	private final DefaultComboBoxModel<String> heroNameModel = new DefaultComboBoxModel<>();
	private final JComboBox<String> heroName = new JComboBox<>();
	private final JLabel stats = new JLabel("Stats");
	private final JLabel levelLbl = new JLabel("Level:");
	private final JLabel expLbl = new JLabel("Experience:");
	private final JLabel attackLbl = new JLabel("Attack:");
	private final JLabel defenceLbl = new JLabel("Defence:");
	private final JLabel hpLbl = new JLabel("Hit points:");
	private final JLabel level = new JLabel();
	private final JLabel exp = new JLabel();
	private final JLabel attack = new JLabel();
	private final JLabel defence = new JLabel();
	private final JLabel hp = new JLabel();
	private final JButton start = new JButton("Start");
	private final JButton exit = new JButton("Exit");

	public HeroPickGuiView(GameController controller) {
		this.controller = controller;
		this.cur = this;
		initGUI();
	}

	private void initGUI() {
		setTitle("Main menu");
		int size = 350;
		setSize(size, size);
		setPreferredSize(new Dimension(size, size - 140));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container container = getContentPane();
		final GroupLayout gl = new GroupLayout(container);
		container.setLayout(gl);
		initLayout(gl);

		buttonGroup.add(create);
		buttonGroup.add(load);
		for (String hero : HeroBuilder.getInstance().getHeroes()) {
			heroClassModel.addElement(hero);
		}
		heroClass.setModel(heroClassModel);
		heroClass.setSelectedItem(null);
		for (String name : DataBase.getInstance().getHeroNames()) {
			heroNameModel.addElement(name);
		}
		heroName.setEditable(true);
		heroName.setPreferredSize(new Dimension(size / 3, 0));

		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearStats();
				heroClass.setModel(heroClassModel);
				heroClass.setEnabled(true);
				heroClass.setSelectedItem(null);
				heroName.setModel(new DefaultComboBoxModel<String>());
				heroName.setEditable(true);
				heroName.setSelectedItem(null);
			}
		});

		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearStats();
				heroClass.setEnabled(false);
				heroClass.setSelectedItem(null);
				heroName.setModel(heroNameModel);
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
					if (heroName.getSelectedItem() != null) {
						Map<String, String> stats = DataBase.getInstance().getHeroStats((String) heroName.getSelectedItem());
						level.setText(stats.get("level"));
						exp.setText(stats.get("experience"));
						attack.setText(stats.get("minAttack") + " - " + stats.get("maxAttack"));
						defence.setText(stats.get("defence"));
						hp.setText(stats.get("hp"));
						heroClass.setSelectedItem(stats.get("class"));
						if (!stats.get("weapon").equals("null")) {
							attack.setText(attack.getText() + " + " + stats.get("weaponStat"));
						}
						if (!stats.get("armor").equals("null")) {
							defence.setText(defence.getText() + " + " + stats.get("armorStat"));
						}
						if (!stats.get("helm").equals("null")) {
							hp.setText(hp.getText() + " + " + stats.get("helmStat"));
						}
					}
				}
			}
		});

		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (create.isSelected()) {
					if (DataBase.getInstance().getHeroNames().contains((String) heroName.getSelectedItem())) {
						new ErrorView("This Name is already taken", cur);
					} else {
						try {
							controller.startGame(HeroBuilder.getInstance().createHero(
									(String) heroClass.getSelectedItem(),
									(String) heroName.getSelectedItem()));
						} catch (IllegalArgumentException exception) {
							new ErrorView(exception.getMessage(), cur);
						}
					}
				} else if (load.isSelected()) {
					try {
						controller.startGame(HeroBuilder.getInstance().loadHero(
								(String) heroName.getSelectedItem()));
					} catch (IllegalArgumentException exception) {
						new ErrorView(exception.getMessage(), cur);
					}
				}
			}
		});

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		pack();
		setVisible(true);
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
										.addComponent(classLabel)
										.addComponent(heroClass)
								)
								.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(nameLabel)
										.addComponent(heroName)
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
