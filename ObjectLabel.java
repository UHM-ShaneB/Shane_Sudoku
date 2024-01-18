package main;

import javax.swing.JLabel;

public class ObjectLabel extends JLabel {

	private int num;
	boolean given;
	
	public ObjectLabel(int num) {
		this.setNum(num);
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public int getNum() {
		return this.num;
	}
	
	public void setGiven(boolean given) {
		this.given = given;
	}
	
	public boolean getGiven() {
		return this.given;
	}
	
	public int compareTo(Object o) {
		ObjectLabel l2 = (ObjectLabel) o;
		
		if (this.getNum() == l2.getNum()) {
			return 1;
		}
		return 0;
	}
}
