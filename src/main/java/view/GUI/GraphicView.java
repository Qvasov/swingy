package view.GUI;

import controller.GameController;
import model.*;
import model.characters.Empty;
import model.characters.heroes.Hero;
import view.View;

import javax.swing.*;
import java.awt.*;

public class GraphicView extends JFrame implements View {
	private GameController controller;
	private JPanel heroInfo;
	private JScrollPane scrollPane;
	private JPanel scrollContent;
	private int mapSize;
	private JLabel[][] mapIcons;
	private JLabel nameLbl;
	private JLabel name;
	private JLabel expLbl;
	private JLabel exp;
	private JLabel levelLbl;
	private JLabel level;

	public GraphicView(GameController controller) {
		this.controller = controller;
		this.mapSize = controller.getModel().getMap().getSize();
		this.mapIcons = new JLabel[mapSize][mapSize];
		for (int x = 0; x < mapSize; x++) {
			for (int y = 0; y < mapSize; y++) {
				this.mapIcons[x][y] = new JLabel();
			}
		}

		initHeroInfo();
		initScrollContent();
		initUI();
		downloadTextures();
		updateView();
		pack();

		JScrollBar bar = scrollPane.getVerticalScrollBar();
		scrollPane.getVerticalScrollBar().setValue(bar.getMaximum() / 2 - bar.getVisibleAmount() / 2);
		bar = scrollPane.getHorizontalScrollBar();
		scrollPane.getHorizontalScrollBar().setValue(bar.getMaximum() / 2 - bar.getVisibleAmount() / 2);

		setVisible(true);
	}

	@Override
	public void updateView() {
		Hero hero = controller.getModel().getHero();
		Map map = controller.getModel().getMap();
		Icon icon;

		mapIcons[hero.getPosition().getX()][hero.getPosition().getY()].setIcon(hero.getIcon());
		if (map.getUnits()[hero.getPosition().getPrevX()] == null ||
				map.getUnits()[hero.getPosition().getPrevX()].get(hero.getPosition().getPrevY()) == null) {
			icon = IconStorage.downloadImage(Empty.class.getSimpleName());
		} else {
			icon = map.getUnits()[hero.getPosition().getPrevX()].get(hero.getPosition().getPrevY()).getIcon();
		}
		mapIcons[hero.getPosition().getPrevX()][hero.getPosition().getPrevY()].setIcon(icon);

		this.level.setText(String.valueOf(controller.getModel().getHero().getLevel()));
		this.exp.setText(controller.getModel().getHero().getExp() + " / " + controller.getModel().getHero().getExpToNextLvl());

		if (controller.getModel().getState() == State.ATTACK) {
			new AttackView(controller);
		} else if (controller.getModel().getState() == State.FIGHT_LOG) {
			new FightResultView(controller);
		} else if (controller.getModel().getState() == State.GAME_OVER) {
			new GameOverView(controller);
		}
	}

	private void initUI() {
		setTitle("Swingy");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		int sideSize = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;
		setSize(sideSize, sideSize);
		setMaximumSize(new Dimension(sideSize, sideSize));
		setLocationRelativeTo(null);

		Container pane = getContentPane();
		GroupLayout layout = new GroupLayout(pane);
		pane.setLayout(layout);

		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(heroInfo)
				.addComponent(scrollPane));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(heroInfo)
				.addComponent(scrollPane));

		addKeyListener(controller);
	}

	private void initHeroInfo() {
		this.heroInfo = new JPanel();
		GroupLayout layout = new GroupLayout(heroInfo);
		heroInfo.setLayout(layout);

		this.nameLbl = new JLabel("Name:");
		this.name = new JLabel(controller.getModel().getHero().getName());
		this.expLbl = new JLabel("Experience:");
		this.exp = new JLabel();
		this.levelLbl = new JLabel("Level:");
		this.level = new JLabel();

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(nameLbl)
						.addComponent(levelLbl)
						.addComponent(expLbl)
				)
				.addGroup(layout.createParallelGroup()
						.addComponent(name)
						.addComponent(level)
						.addComponent(exp)
				)
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(nameLbl)
						.addComponent(name)
				)
				.addGroup(layout.createParallelGroup()
						.addComponent(levelLbl)
						.addComponent(level)
				)
				.addGroup(layout.createParallelGroup()
						.addComponent(expLbl)
						.addComponent(exp)
				)
		);
	}

	private void initScrollContent() {
		this.scrollContent = new JPanel();
		this.scrollPane = new JScrollPane(scrollContent);

		int sideSize = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;
		scrollPane.setMaximumSize(new Dimension(sideSize, sideSize));
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(15);

		GroupLayout layout = new GroupLayout(scrollContent);
		scrollContent.setLayout(layout);

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
	}

	private void downloadTextures() {
		Map map = controller.getModel().getMap();
		Icon icon;

		for (int x = 0; x < map.getSize(); x++) {
			for (int y = 0; y < map.getSize(); y++) {
				if (map.getUnits()[x] == null || map.getUnits()[x].get(y) == null) {
					icon = IconStorage.downloadImage(Empty.class.getSimpleName());
				} else {
					icon = map.getUnits()[x].get(y).getIcon();
				}
				mapIcons[x][y].setIcon(icon);
			}
		}
	}
}
