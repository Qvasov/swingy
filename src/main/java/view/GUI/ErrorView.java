package view.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ErrorView extends JDialog {
	private JFrame parent;
	private JLabel message;
	private JButton ok = new JButton("OK");

	public ErrorView(String message, JFrame parent) {
		this.parent = parent;
		this.message = new JLabel("<html>" + message.replace("\n", "<br>") + "</html>");
		parent.setEnabled(false);
		initUI();
	}

	public void initUI() {
		setTitle("Error");
		setResizable(false);
		initLayout();
		pack();
		setLocationRelativeTo(parent);
		setVisible(true);

		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setEnabled(true);
				dispose();
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				parent.setEnabled(true);
				dispose();
			}
		});
	}

	private void initLayout() {
		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
		gl.setHorizontalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(message)
				.addComponent(ok)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
				.addComponent(message)
				.addComponent(ok)
		);
	}
}
