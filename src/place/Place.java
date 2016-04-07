package place;

import java.util.*;

import type.Item;
import type.RoadBlock;

public abstract class Place {
	private int x;
	private int y;
	private String symbol;
	private LinkedList<Item> items = new LinkedList<Item>();

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

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String toTextual() {
		/*
		 * if(items.isEmpty()||(items.size()==1&&items.getFirst() instanceof
		 * RoadBlock)) return this.symbol; Item it=items.getFirst(); if(it
		 * instanceof RoadBlock) it=items.get(1);
		 */
		//Item i = items.stream().filter(item -> (item instanceof RoadBlock))
			//	.findFirst().orElse(null);
		return items.stream()
				.filter(item -> !(item instanceof RoadBlock))
				.map(item -> item.getSymbol()).findFirst().orElse(this.symbol);
		//return symbol;
	}

}
