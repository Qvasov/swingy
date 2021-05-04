package view.GUI;

import controller.GameController;
import model.DataBase;
import model.characters.heroes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

public class HeroPickView extends JFrame {
	private GameController controller;
	private JRadioButton create = new JRadioButton("Create Hero", true);
	private JRadioButton load = new JRadioButton("Load Hero", false);
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel classLabel = new JLabel("Class:");
	private JComboBox<String> heroClassCb = new JComboBox<>();
	private JLabel nameLabel = new JLabel("Name:");
	private JTextField heroName = new JTextField();
	private JComboBox<String> heroNameCb = new JComboBox<>();
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
	private JButton ok = new JButton("OK");

	public HeroPickView(GameController controller) {
		this.controller = controller;
		initGUI();
	}

	private void initGUI() {
		setTitle("Hero pick");
		int size = 350;
		setSize(size, size);
		setPreferredSize(new Dimension(size, size - 100));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container container = getContentPane();
		final GroupLayout gl = new GroupLayout(container);
		container.setLayout(gl);
		initLayout(gl);

		heroClassCb.setMaximumSize(new Dimension(size / 3, 0));
		heroName.setMaximumSize(new Dimension(size / 3 ,0));
		heroNameCb.setMaximumSize(new Dimension(size / 3 ,0));

		buttonGroup.add(create);
		buttonGroup.add(load);
		downloadClasses();

		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				downloadClasses();
				heroClassCb.setEnabled(true);
				heroName.setEnabled(true);
			}
		});

		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearStats();
				for (String name : DataBase.getInstance().getHeroNames()) {
					heroNameCb.addItem(name);
				}
				gl.replace(heroName, heroNameCb);
				heroClassCb.setEnabled(false);
				heroName.setEnabled(false);
				heroName.setVisible(false);
				heroNameCb.setVisible(true);
				heroNameCb.setSelectedItem(null);
			}
		});


		heroClassCb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (create.isSelected()) {
					if (heroClassCb.getSelectedItem() != null) {
						Map<String, Integer> stats = HeroBuilder.getInstance().getStats(String.valueOf(heroClassCb.getSelectedItem()));
						level.setText(stats.get("level").toString());
						exp.setText(stats.get("experience").toString());
						attack.setText(stats.get("attack").toString());
						defence.setText(stats.get("defence").toString());
						hp.setText(stats.get("hp").toString());
					}
				}
			}
		});

		heroNameCb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (load.isSelected()) {
					if (heroClassCb.getSelectedItem() != null) {

					}
				}
			}
		});

		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (create.isSelected()) {
					controller.startGame(HeroBuilder.getInstance().createHero(
							String.valueOf(heroClassCb.getSelectedItem()),
							heroName.getText()));
				} else if (load.isSelected()) {
//					controller.startGame(HeroBuilder.getInstance().loadHero(
//							String.valueOf(heroNameCb.getSelectedItem())));
				}
				setVisible(false);
			}
		});

		pack();
	}

	private void downloadClasses() {
		heroClassCb.removeAllItems();
		for (String hero : HeroBuilder.getInstance().getHeroes()) {
			heroClassCb.addItem(hero);
		}
		heroClassCb.setSelectedItem(null);
	}

	private void clearStats() {
		String text = null;
		level.setText(text);
		exp.setText(text);
		attack.setText(text);
		defence.setText(text);
		hp.setText(text);
	}

	private void initLayout(GroupLayout gl) {
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
										.addComponent(heroClassCb)
										.addComponent(heroName)
								)
						)
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
						.addGroup(gl.createSequentialGroup())
						.addComponent(ok)
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
										.addComponent(heroClassCb)
								)
								.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(nameLabel)
										.addComponent(heroName)
								)
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
				.addComponent(ok)
		);
	}
}
