package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements MouseListener, ActionListener  {
	
	final int SCREEN_WIDTH = 750;
	final int SCREEN_HEIGHT = 750;
	
	
	NumberButton nb = new NumberButton(this);
	GameButton gb = new GameButton(this);
	GameSettings gs = new GameSettings(this);
	NumberLabel nl = new NumberLabel(this);
	
	String userString;
	int userNum, removeNum, returnNum;

	public GamePanel() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setLayout(null);
		
		removeNum = 41;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}


	@Override
	public void mousePressed(MouseEvent e) {
		returnNum = 0;
		//Sets the label to the number that the user chooses
		for (int i = 0; i< 81; i++) { 
			if(e.getSource() == nl.labels[i] && nl.labels[i].isEnabled() == true) {
				returnNum += gs.checkRow(userNum, i);
				returnNum += gs.checkCol(userNum, i);
				returnNum += gs.checkBox(userNum, i);
				
				if (returnNum > 0) {
					nl.labels[i].setForeground(Color.RED);
				}
				else {
					nl.labels[i].setForeground(Color.BLUE);
				}
				
				nl.labels[i].setText(userString);
				nl.labels[i].setNum(userNum);
			}
		}
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 9; i++) {
			if (e.getSource() == nb.buttons[i]) { //Buttons for Numbers 1 - 9
				for (int j = 0; j < 9; j++) {
					nb.buttons[j].setEnabled(true);
				}
				gb.buttons[9].setEnabled(true);
				userString =  String.valueOf(i + 1);
				userNum = i + 1;
				nb.buttons[i].setEnabled(false);
			}
		}
		
		if (e.getSource() == gb.buttons[9]) { //Erase
			for(int i = 0; i < 9; i++) {
				nb.buttons[i].setEnabled(true);
				gb.buttons[9].setEnabled(false);
				userString = "";
				userNum = 0;
			}
		}
		
		if (e.getSource() == gb.buttons[0]) { //Exit
			System.exit(0);
		}
		
		if (e.getSource() == gb.buttons[1]) { //Difficulty
			gb.panelDiffButton.setVisible(true);
			userNum = 0;
			userString = "";
		
			for (int i = 0; i < 81; i++) {
				nl.labels[i].setEnabled(false);
			}
			
			for (int i = 0; i < 9; i++) {
				nb.buttons[i].setEnabled(false);
			}
			
			for (int i = 0; i < 4; i++) {
				gb.buttons[i].setEnabled(false);
			}
			gb.buttons[9].setEnabled(false);
		}
		
		if (e.getSource() == gb.buttons[2]) { //New Game
			
			gs.setUp();
			gs.removeNumbers(removeNum);
			
		}
		
		if (e.getSource() == gb.buttons[3]) { //Submit
			gs.finalCheck();
		}
		
		for (int i = 4; i < 9; i++) {
		
			if (e.getSource() == gb.buttons[4]) {
				gs.labelDiff.setText("Easy");
				removeNum = 41;
				gs.setUp();
				gs.removeNumbers(removeNum);
			}
			
			if (e.getSource() == gb.buttons[5]) {
				gs.labelDiff.setText("Medium");
				removeNum = 46;
				gs.setUp();
				gs.removeNumbers(removeNum);
			}
			
			if (e.getSource() == gb.buttons[6]) {
				gs.labelDiff.setText("Hard");
				removeNum = 51;
				gs.setUp();
				gs.removeNumbers(removeNum);
			}
			
			if (e.getSource() == gb.buttons[7]) {
				gs.labelDiff.setText("Expert");
				removeNum = 56;
				gs.setUp();
				gs.removeNumbers(removeNum);
			}
			
			if (e.getSource() == gb.buttons[i]) { //Cancel
				gb.panelDiffButton.setVisible(false);
				
				for (int j = 0; j < gs.enabledIndex.size(); j++) {
					nl.labels[gs.enabledIndex.get(j)].setEnabled(true);
				}
			
				for (int j = 0; j < 9; j++) {
				nb.buttons[j].setEnabled(true);
				}
			
				for (int j = 0; j < 4; j++) {
					gb.buttons[j].setEnabled(true);
				}
				gb.buttons[9].setEnabled(true);
			}
		}
	}
}
