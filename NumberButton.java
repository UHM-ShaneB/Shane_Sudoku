package main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class NumberButton {

	GamePanel gp;
	
	private int width;
	private int height;
	private int x;
	private int y;
	
	JButton[] buttons = new JButton[9];
	
	public NumberButton(GamePanel gp) {
		this.gp = gp;
		
		width = 50;
		height = 50;
		x = 100;
		y = 550;
		
		for(int i = 0; i < 9; i++) {
			buttons[i] = new JButton(String.valueOf(i + 1));
			buttons[i].setBounds(x, y, width, height);
			buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(gp);
			gp.add(buttons[i]);
			x += 50;
		}
	}
}
