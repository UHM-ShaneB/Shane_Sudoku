package main;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class GameSettings {

	GamePanel gp;
	
	JLabel labelDiff;
	int  row, col, boxStart, boxEnd, randNum, startNum, endNum, returnNum, returnNum2, rowNum, colNum, tempNum, colIndex, rowIndex, num, rowIndex2, count, mistakeNum, currentNum; 
	ArrayList<Integer> numbers = new ArrayList<Integer>();
	ArrayList<Integer> enabledIndex = new ArrayList<Integer>(); 
	
	public GameSettings(GamePanel gp) {
		this.gp = gp;
		
		labelDiff = new JLabel("Easy", SwingConstants.CENTER);
		labelDiff.setFont(new Font("Arial", Font.BOLD, 20));
		labelDiff.setBounds(25, 25, 100, 50);
		gp.add(labelDiff);
	}
	
	/* Fills each label with a number and text corresponding to its set number.
	 * 
	 * @param	none
	 * @return	void
	 */
	public void setUp() {
		clear();
		
		for (int i = 0; i < 81; i++) {
			gp.nl.labels[i].setForeground(Color.BLACK);
			gp.nl.labels[i].setEnabled(false);
		}
		
		//Fills in the first six rows
		rowNum = 0;
		while (rowNum < 6) {
			fillNumbers();
			rowIndex = rowNum * 9;
			
			switch(rowNum){
				case 0: //Fills the First Row
					addNumbers();
					break;
				
				case 1: //Fills the Second Row
					addNumbers();
					rearrangeBox();
					break;
					
				case 2: //Fills the Third Row
					addRemainingBoxNumbers();
					break;
					
				case 3: // Fills the Fourth Row
					addNumbers();
					rearrangeCol1();
					break;
					
				case 4: // Fills the Fifth Row
					addNumbers();
					rearrangeBox();
					rearrangeCol2();
					break;	
					
				case 5: // Fills the Sixth Row
					addRemainingBoxNumbers();
					rearrangeCol2();
					break;
			}
			rowNum++;
		}
		
		//Fills in the missing numbers for the first six columns
		colNum = 0;
		while (colNum < 6) {
			fillNumbers();
			colIndex = colNum + 54;
			addRemainingColNumbers();
			rearrangeRow();
			colNum++;
		}

		//Fills the box in the bottom right corner
		boxStart = 60;
		boxEnd = 80;
		for (int i = boxStart; i < boxStart + 3; i++) {
			for (int j = 1; j < 10; j++) {
				if (checkRow(j, i) == 0 && checkCol(j, i) == 0) {
					gp.nl.labels[i].setNum(j);
					gp.nl.labels[i].setText(String.valueOf(j));
				}
			}
			if(gp.nl.labels[i].getNum() == 0) {
				setUp();
			}
			
			else if (i == boxStart + 2 && i < boxEnd) {
				boxStart += 9;
				i = boxStart - 1;
			}
		}
	}
	
	/* Removes the number in the index.
	 * The amount of indexes removed are determined based on difficulty
	 * 
	 * @param 	int newRemoveNum - The amount of indexes to be removed
	 * @return	void
	 */
	public void removeNumbers(int newRemoveNum) {
		currentNum = 0;
		enabledIndex.clear();
		while (currentNum < newRemoveNum) {
			int i = (int) (Math.random() * 81);
			if(gp.nl.labels[i].getNum() != 0) {
				gp.nl.labels[i].setNum(0);
				gp.nl.labels[i].setText("");
				gp.nl.labels[i].setEnabled(true);
				enabledIndex.add(i);
				currentNum++;
			}
		}
	}
	
	/* Does a final check when user chooses to submit their solution
	 * 
	 * @param	none
	 * @return	void
	 */
	public void finalCheck() {
		returnNum = 0;
		for (int i = 0; i< 81; i++) {
			returnNum += checkRow(gp.nl.labels[i].getNum(), i);
			returnNum += checkCol(gp.nl.labels[i].getNum(), i);
			returnNum += checkBox(gp.nl.labels[i].getNum(), i);
		}
		
		if (returnNum == 0) {
			System.out.println("Congratulations!");
		}
		
		else {
			System.out.println("There are some mistakes");
		}
	}
	
	
	/* Resets the puzzle by setting the number of each label to and text of each label to be blank
	 * 
	 * @param	none
	 * @return 	void
	 */
	public void clear() {
		for (int i = 0; i < 81; i++) {
			gp.nl.labels[i].setNum(0);
			gp.nl.labels[i].setText("");
		}
	}
	
	public void fillNumbers() {
		numbers.clear();
		for (int i = 1 ; i < 10; i++) {
			numbers.add(i);
		}
	}
	
	public void addNumbers() {
		for (int i = rowIndex; i < rowIndex + 9; i++) {
			returnNum = 0;
			
			randNum = (int) (Math.random() * numbers.size()); 
			gp.nl.labels[i].setNum(numbers.get(randNum));
			gp.nl.labels[i].setText(String.valueOf(numbers.get(randNum)));
			//System.out.print(gp.nl.labels[i].getNum());
			numbers.remove(randNum);

		}
		//System.out.println("");
	}
	
	public void addRemainingBoxNumbers() {
		for (int i = rowIndex; i < rowIndex + 9; i++) {
			for (int j = 0; j < numbers.size(); j++) {
				if (checkBox(numbers.get(j), i) == 0) {
					gp.nl.labels[i].setNum(numbers.get(j));
					gp.nl.labels[i].setText(String.valueOf(numbers.get(j)));
					numbers.remove(j);
					j += 10;
				}
			}
		}
	}
	
	public void addRemainingColNumbers() {
		for (int i = colIndex; i < colIndex + 27; i +=9 ) {
			for (int j = 0; j < numbers.size(); j++) {
				if (checkCol(numbers.get(j), i) == 0) {
					gp.nl.labels[i].setNum(numbers.get(j));
					gp.nl.labels[i].setText(String.valueOf(numbers.get(j)));
					numbers.remove(j);
					j += 10;
				}
			}
		}
	}
	
	public void rearrangeBox() {
		for (int i = rowIndex; i < rowIndex + 9; i++) {
			returnNum += checkBox(gp.nl.labels[i].getNum(), i);
			
			if (returnNum > 0) {
				for (int j = rowIndex; j < rowIndex + 9; j++) {
					returnNum = 0;
					
					returnNum += checkBox(gp.nl.labels[i].getNum(), j);
					returnNum += checkBox(gp.nl.labels[j].getNum(), i);
					
					
					if (returnNum < 1) {
						tempNum = gp.nl.labels[i].getNum();
						gp.nl.labels[i].setNum(gp.nl.labels[j].getNum());
						gp.nl.labels[j].setNum(tempNum);
						
						gp.nl.labels[i].setText(String.valueOf(gp.nl.labels[i].getNum()));
						gp.nl.labels[j].setText(String.valueOf(gp.nl.labels[j].getNum()));
						j += 10;
					}
				}
			}
		}
	}
	
	public void rearrangeRow(){
		for (int i = colIndex; i < colIndex + 27; i +=9 ) {
			returnNum += checkRow(gp.nl.labels[i].getNum(), i);
			
			if (returnNum > 0) {
				for (int j = colIndex; j < colIndex + 27; j += 9) {
					returnNum = 0;
					
					returnNum += checkRow(gp.nl.labels[i].getNum(), j);
					returnNum += checkRow(gp.nl.labels[j].getNum(), i);
					
					
					if (returnNum < 1) {
						tempNum = gp.nl.labels[i].getNum();
						gp.nl.labels[i].setNum(gp.nl.labels[j].getNum());
						gp.nl.labels[j].setNum(tempNum);
						
						gp.nl.labels[i].setText(String.valueOf(gp.nl.labels[i].getNum()));
						gp.nl.labels[j].setText(String.valueOf(gp.nl.labels[j].getNum()));
						j += 10;
					}
				}
			}
		}
	}
	
	public void rearrangeCol1() {
		for (int i = rowIndex; i < rowIndex + 9; i++) {
			returnNum += checkCol(gp.nl.labels[i].getNum(), i);
			
			if (returnNum > 0) {
				for (int j = rowIndex; j < rowIndex + 9; j++) {
					returnNum = 0;
					
					returnNum += checkCol(gp.nl.labels[i].getNum(), j);
					returnNum += checkCol(gp.nl.labels[j].getNum(), i);
					
					
					if (returnNum < 1 && returnNum2 < 1) {
						tempNum = gp.nl.labels[i].getNum();
						gp.nl.labels[i].setNum(gp.nl.labels[j].getNum());
						gp.nl.labels[j].setNum(tempNum);
						
						gp.nl.labels[i].setText(String.valueOf(gp.nl.labels[i].getNum()));
						gp.nl.labels[j].setText(String.valueOf(gp.nl.labels[j].getNum()));
						j += 10;
					}
				}
			}
		}
	}
	
	public void rearrangeCol2() {
		for (int i = rowIndex; i < rowIndex + 9; i++) {
			returnNum += checkCol(gp.nl.labels[i].getNum(), i);
			
			if (returnNum > 0) {
				rowIndex2 = (i / 3) * 3;
				for (int j = rowIndex2; j < rowIndex2 + 3; j++) {
					returnNum = 0;
					
					returnNum += checkCol(gp.nl.labels[i].getNum(), j);
					returnNum += checkCol(gp.nl.labels[j].getNum(), i);
					
					
					if (returnNum < 1) {
						tempNum = gp.nl.labels[i].getNum();
						gp.nl.labels[i].setNum(gp.nl.labels[j].getNum());
						gp.nl.labels[j].setNum(tempNum);
						
						gp.nl.labels[i].setText(String.valueOf(gp.nl.labels[i].getNum()));
						gp.nl.labels[j].setText(String.valueOf(gp.nl.labels[j].getNum()));
						j += 10;
					}
				}
			}
		}
	}
	
	public int checkRow(int num, int index){
		row = (index / 9) * 9;
		//System.out.println(row + "\t" + index);
		
		for (int i = row; i < row + 9; i++) {
			//System.out.println(i + "\t" + index);
			if (gp.nl.labels[i].getNum() == num && i != index) {
				return 1;
			}
		}
		
		return 0;
	}
	
	public int checkCol(int num, int index) {
		col = index % 9;
		
		while (col < 81) {
			if (gp.nl.labels[col].getNum() == num && col != index) {
				
				return 1;
			}
			//System.out.println(col + "\t" + index);
			
			col += 9;
		}
		return 0;
	}
	
	public int checkBox(int num, int index) {
		boxStart = (((index % 9) / 3) * 3) + ((index / 27) * 27);
		boxEnd = boxStart + 20;
		
		for (int i = boxStart; i < boxStart + 3; i++) {
			
			if (gp.nl.labels[i].getNum() == num && i != index) {
				return 1;
			}
			
			if (i == boxStart + 2 && i < boxEnd) {
				boxStart += 9;
				i = boxStart - 1;
			}
		}
		
		return 0;
	}
}
