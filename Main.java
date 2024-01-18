package main;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {

		JFrame window = new JFrame("Shane Sudoku");
		GamePanel gp = new GamePanel();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		
		window.add(gp);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}
