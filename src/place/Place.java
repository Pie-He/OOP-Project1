package place;

import java.util.*;

import type.item;

public abstract class Place {
	private int x;
	private int y;
	private String symbol;
	private Collection<item> items=new LinkedList<item>();
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

	public int getY() {
		return y;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
