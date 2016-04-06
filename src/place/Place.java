package place;

import java.util.*;

import type.Item;

public abstract class Place {
	private int x;
	private int y;
	private String symbol;
	private LinkedList<Item> items=new LinkedList<Item>();
	Place() {

	}

	Place(int x, int y, String symbol) {
		this.x = x;
		this.y = y;
		this.setSymbol(symbol);
	}

	public int getX() {
		return x;
	}
	public void setX(int x){
		this.x=x;
	}
	public int getY() {
		return y;
	}
	
	public void setY(int y){
		this.y=y;
	}
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String toTextual() {
		// TODO Auto-generated method stub
		return items.size()==0?this.symbol:items.getLast().getSymbol();
	}

}
