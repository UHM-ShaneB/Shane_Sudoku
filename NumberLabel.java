package main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class NumberLabel{
	GamePanel gp;
	
	
	private int width;
	private int height;
	private int x;
	private int y;
	private int z;
	private int i;
	private int j;
	private int count1;
	private int count2;
	private int index;
	private Color color1 = new Color(220, 230, 190);
	private Color color2 = new Color(200, 210, 170);
	private Color color3 = new Color(200, 240, 250);
	private Color color4 = new Color(180, 220, 230);
	
	ObjectLabel[] labels = new ObjectLabel[81];

	
	public NumberLabel(GamePanel gp) {
		this.gp = gp;
		
		width = 50;
		height = 50;
		x = 150;
		y = 50;
		count1 = 0;
		count2 = 0;
		z = 0;
		index = 0;
		
		for (i = 0; i < 9; i++) {
			for (j = 0; j < 9; j++) {
				labels[index] = new ObjectLabel(0);
				labels[index].setHorizontalAlignment(SwingConstants.CENTER);
				labels[index].setBounds(x, y, width, height);
				labels[index].setFont(new Font("Arial", Font.BOLD, 30));
				labels[i].setForeground(Color.BLACK);
				labels[index].addMouseListener(gp);
				labels[index].setOpaque(true);
				
				if (count1 < 2) {
					changeColor(((i + j) % 2) + z);
					count1++;
				}
				else {
					changeColor(((i + j) % 2) + z);
					count1 = 0;
					
					switch(z) {
					case 0:
						z = 2;
						break;
						
					case 2:
						z = 0;
						break;
					}
				}
				gp.add(labels[index]);	
				index++;
				x += 50;
			}
			if (count2 < 2) {
				switch(z) {
				case 0:
					z = 2;
					break;
					
				case 2:
					z = 0;
					break;
				}
				count2++;
			}
			
			else {
				count2 = 0;
			}
			x = 150;
			y += 50;
		}
	}


	public void changeColor(int num) {
		switch(num) {
		case 0:
			labels[index].setBackground(color1);
			break;
			
		case 1:
			labels[index].setBackground(color2);
			break;
		
		case 2:
			labels[index].setBackground(color3);
			break;
			
		case 3:
			labels[index].setBackground(color4);
			break;
			
		}	
	}
}
