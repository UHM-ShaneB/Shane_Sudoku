package main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class GameButton {

	GamePanel gp;
	
	private int width;
	private int height;
	private int x;
	private int y;
	
	JButton[] buttons = new JButton[10];
	JPanel panelDiffButton;
	
	public GameButton(GamePanel gp) {
		this.gp = gp;
		
		panelDiffButton = new JPanel();
		panelDiffButton.setBounds(275, 75, 200, 400);
		panelDiffButton.setLayout(null);
		panelDiffButton.setBackground(Color.BLACK);
		panelDiffButton.setOpaque(true);
		panelDiffButton.setVisible(false);
		gp.add(panelDiffButton);
		
		buttons[9] = new JButton("Erase");
		buttons[9].setBounds(600, 550, 100, 50);
		buttons[9].setFont(new Font("Arial", Font.BOLD, 20));
		buttons[9].setFocusable(false);
		buttons[9].addActionListener(gp);
		gp.add(buttons[9]);
		
		width = 150;
		height = 50;
		x = 40;
		y = 650;
		
		for (int i = 0; i < 4; i++) {
			buttons[i] = new JButton();
			buttons[i].setBounds(x, y, width, height);
			buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(gp);
			gp.add(buttons[i]);
			
			x += 175;
		}
		
		width = 150;
		height = 50;
		x = 25;
		y = 25;
		
		for (int i = 4; i < 9; i++) {
			buttons[i] = new JButton();
			buttons[i].setBounds(x, y, width, height);
			buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(gp);
			panelDiffButton.add(buttons[i]);
			
			y += 75;
		}
		
		buttons[0].setText("Exit");
		buttons[1].setText("Difficulty");
		buttons[2].setText("New Game");
		buttons[3].setText("Submit");
		buttons[4].setText("Easy");
		buttons[5].setText("Medium");
		buttons[6].setText("Hard");
		buttons[7].setText("Expert");
		buttons[8].setText("Cancel");
	}
}
