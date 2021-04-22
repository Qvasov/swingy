package view.GUI;

import controller.GameController;
import model.EmptyIcon;
import model.IconStorage;
import model.Map;
import view.View;

import javax.swing.*;
import java.awt.*;

public class GraphicView extends JFrame {
	private GameController controller;
	private JLabel[][] mapIcons;

	public GraphicView(GameController controller) {
		this.controller = controller;
		initUI();
	}

	private void initUI() {
		setTitle("Game");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		int mapSize = controller.getModelFacade().getMap().getSize();
		this.mapIcons = new JLabel[mapSize][mapSize];
		for (int x = 0; x < mapSize; x++) {
			for (int y = 0; y < mapSize; y++) {
				this.mapIcons[x][y] = new JLabel();
			}
		}

		Container pane = getContentPane();
//		JPanel panel = new JavaPanel();
//		JScrollPane scrollPane = new JScrollPane(pane);
//		add(scrollPane);
		addKeyListener(controller);

		GroupLayout layout = new GroupLayout(pane);
		pane.setLayout(layout);
//		scrollPane.addKeyListener(controller);
//		panel.addKeyListener(controller);
//		scrollPane.addKeyListener(controller);

		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

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

		pack();
	}

	public void updateView() {
		Map map = controller.getModelFacade().getMap();
		Icon icon;

		for (int x = 0; x < map.getSize(); x++) {
			for (int y = 0; y < map.getSize(); y++) {
				if (map.getUnits()[x] == null || map.getUnits()[x].get(y) == null) {
					icon = IconStorage.getInstance().get(EmptyIcon.class);
				} else {
					icon = IconStorage.getInstance().get(map.getUnits()[x].get(y).getClass());
				}
				mapIcons[x][y].setIcon(icon);
			}
		}
		pack();
	}
}
