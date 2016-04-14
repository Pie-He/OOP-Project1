package place;

import java.util.*;

import type.*;

public abstract class Place {
	private int x;
	private int y;
	private String symbol;
	private PriorityQueue<Removable> items = new PriorityQueue<Removable>();
	Place() {

	}

	Place(int x, int y, String symbol) {
		this.x = x;
		this.y = y;
		this.setSymbol(symbol);
	}

	public abstract void event(Player p);
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

	public String toText() {
		/*
		 * if(items.isEmpty()||(items.size()==1&&items.getFirst() instanceof
		 * RoadBlock)) return this.symbol; Item it=items.getFirst(); if(it
		 * instanceof RoadBlock) it=items.get(1);
		 */
		// Item i = items.stream().filter(item -> (item instanceof RoadBlock))
		// .findFirst().orElse(null);
		// return items.stream().filter(item -> !(item instanceof RoadBlock))
		// .map(item -> item.getSymbol()).findFirst().orElse(this.symbol);
		return items.stream().map(item -> item.getSymbol())
				.filter(item -> item != null).findFirst().orElse(this.symbol);
		// return symbol;
	}
	
	public Removable remove(){
		return this.items.poll();
	}
	
	public void put(Removable item){
		this.items.add(item);
	}
	
	public boolean isBlock(){
		return this.items.peek() instanceof RoadBlock;
	}
}
