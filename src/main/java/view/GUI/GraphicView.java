package view.GUI;

import controller.GameController;
import model.IconStorage;
import model.Map;
import model.characters.Empty;
import model.characters.heroes.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GraphicView extends JFrame {
	private final GameController controller;
	private final JFrame cur;
	private JPanel heroInfo;
	private JScrollPane scrollPane;
	private JPanel scrollContent;
	private final int mapSize;
	private final JLabel[][] mapIcons;
	private JLabel name;
	private JLabel exp;
	private JLabel level;
	private JLabel attack;
	private JLabel defence;
	private JLabel hp;

	public GraphicView(final GameController controller) {
		this.controller = controller;
		this.cur = this;
		this.mapSize = controller.getModel().getMap().getSize();
		this.mapIcons = new JLabel[mapSize][mapSize];
		for (int x = 0; x < mapSize; x++) {
			for (int y = 0; y < mapSize; y++) {
				this.mapIcons[x][y] = new JLabel();
			}
		}

		Action switchMode = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				controller.switchMode();
			}
		};
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F4"), "switch");
		getRootPane().getActionMap().put("switch", switchMode);

		initHeroInfo();
		initScrollContent();
		initUI();
		downloadTextures();
		pack();
		setVisible(true);
		updateView();

		JScrollBar bar = scrollPane.getVerticalScrollBar();
		scrollPane.getVerticalScrollBar().setValue(bar.getMaximum() / 2 - bar.getVisibleAmount() / 2);
		bar = scrollPane.getHorizontalScrollBar();
		scrollPane.getHorizontalScrollBar().setValue(bar.getMaximum() / 2 - bar.getVisibleAmount() / 2);
	}

	public void updateView() {
		Hero hero = controller.getModel().getHero();
		Map map = controller.getModel().getMap();
		Icon icon;

		mapIcons[hero.getPosition().getX()][hero.getPosition().getY()].setIcon(hero.getIcon());
		if (map.getUnits()[hero.getPosition().getPrevX()] == null ||
				map.getUnits()[hero.getPosition().getPrevX()].get(hero.getPosition().getPrevY()) == null) {
			icon = IconStorage.getInstance().downloadImage(Empty.class.getSimpleName());
		} else {
			icon = map.getUnits()[hero.getPosition().getPrevX()].get(hero.getPosition().getPrevY()).getIcon();
		}
		mapIcons[hero.getPosition().getPrevX()][hero.getPosition().getPrevY()].setIcon(icon);

		this.level.setText(String.valueOf(controller.getModel().getHero().getLevel()));
		this.exp.setText(controller.getModel().getHero().getExp() + " / " + controller.getModel().getHero().getExpToNextLvl());

		String stat;
		stat = controller.getModel().getHero().getMinAttack() + " - " + controller.getModel().getHero().getMaxAttack();
		if (controller.getModel().getHero().getWeapon() != null) {
			stat += " + " + controller.getModel().getHero().getWeapon().getAttack();
		}
		this.attack.setText(stat);

		stat = String.valueOf(controller.getModel().getHero().getDefence());
		if (controller.getModel().getHero().getArmor() != null) {
			stat += " + " + controller.getModel().getHero().getArmor().getDefence();
		}
		this.defence.setText(stat);

		stat = String.valueOf(controller.getModel().getHero().getHp());
		if (controller.getModel().getHero().getHelm() != null) {
			stat += " + " + controller.getModel().getHero().getHelm().getHp();
		}
		this.hp.setText(stat);

		switch (controller.getModel().getState()) {
			case ATTACK:
				new AttackView(controller, this);
				break;
			case FIGHT_LOG:
				new FightResultView(controller, this);
				break;
			case NEXT:
				new NextView(controller, this);
				break;
			case EXIT:
				new ExitView(controller, this);
				break;
		}
	}

	private void initUI() {
		setTitle("Swingy");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		final int sideSize = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 + 15;
		setSize(sideSize, sideSize);
		setMaximumSize(new Dimension(sideSize, sideSize));
		setPreferredSize(new Dimension(sideSize, sideSize));
		setMinimumSize(new Dimension(sideSize, sideSize));
		setLocationRelativeTo(null);

		Container pane = getContentPane();
		GroupLayout layout = new GroupLayout(pane);
		pane.setLayout(layout);

		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(heroInfo)
				.addComponent(scrollPane));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(heroInfo)
				.addComponent(scrollPane));

		addKeyListener(controller);
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					new ExitView(controller, cur);
				}
			}
		});
	}

	private void initHeroInfo() {
		this.heroInfo = new JPanel();
		GroupLayout layout = new GroupLayout(heroInfo);
		heroInfo.setLayout(layout);

		this.name = new JLabel(controller.getModel().getHero().getName());
		this.exp = new JLabel();
		this.level = new JLabel();
		this.attack = new JLabel();
		this.defence = new JLabel();
		this.hp = new JLabel();
		JLabel nameLbl = new JLabel("Name: ");
		JLabel expLbl = new JLabel("Experience: ");
		JLabel levelLbl = new JLabel("Level: ");
		JLabel hpLbl = new JLabel("HP: ");
		JLabel attackLbl = new JLabel("Attack: ");
		JLabel defenceLbl = new JLabel("Defence: ");

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(nameLbl)
						.addComponent(expLbl)
						.addComponent(levelLbl)
				)
				.addGroup(layout.createParallelGroup()
						.addComponent(name)
						.addComponent(level)
						.addComponent(exp)
				)
				.addGap(20)
				.addGroup(layout.createParallelGroup()
						.addComponent(attackLbl)
						.addComponent(defenceLbl)
						.addComponent(hpLbl)
				)
				.addGroup(layout.createParallelGroup()
						.addComponent(hp)
						.addComponent(attack)
						.addComponent(defence)
				)
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(nameLbl)
						.addComponent(name)
						.addComponent(hpLbl)
						.addComponent(hp)
				)
				.addGroup(layout.createParallelGroup()
						.addComponent(levelLbl)
						.addComponent(level)
						.addComponent(attackLbl)
						.addComponent(attack)
				)
				.addGroup(layout.createParallelGroup()
						.addComponent(expLbl)
						.addComponent(exp)
						.addComponent(defenceLbl)
						.addComponent(defence)
				)
		);
	}

	private void initScrollContent() {
		this.scrollContent = new JPanel(new GridBagLayout());
		this.scrollPane = new JScrollPane(scrollContent);

		int sideSize = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;
		scrollPane.setMaximumSize(new Dimension(sideSize, sideSize));
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(15);

		JPanel map = new JPanel();
		GroupLayout layout = new GroupLayout(map);
		map.setLayout(layout);

		GroupLayout.SequentialGroup sg;
		GroupLayout.ParallelGroup pg;
		sg = layout.createSequentialGroup();
		for (int x = 0; x < mapSize; x++) {
			pg = layout.createParallelGroup();
			for (int y = 0; y < mapSize; y++) {
				pg.addComponent(mapIcons[x][y]);
			}
			sg.addGroup(pg);
		}
		layout.setHorizontalGroup(sg);

		pg = layout.createParallelGroup();
		for (int x = 0; x < mapSize; x++) {
			sg = layout.createSequentialGroup();
			for (int y = 0; y < mapSize; y++) {
				sg.addComponent(mapIcons[x][y]);
			}
			pg.addGroup(sg);
		}
		layout.setVerticalGroup(pg);

		scrollContent.add(map, new GridBagConstraints());
	}

	private void downloadTextures() {
		Map map = controller.getModel().getMap();
		Icon icon;

		for (int x = 0; x < map.getSize(); x++) {
			for (int y = 0; y < map.getSize(); y++) {
				if (map.getUnits()[x] == null || map.getUnits()[x].get(y) == null) {
					icon = IconStorage.getInstance().downloadImage(Empty.class.getSimpleName());
				} else {
					icon = map.getUnits()[x].get(y).getIcon();
				}
				mapIcons[x][y].setIcon(icon);
			}
		}
	}
}
