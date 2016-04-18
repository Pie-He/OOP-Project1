package place;

import java.util.*;

import type.*;

public abstract class Place {
	private int x;
	private int y;
	private String symbol;
	private LinkedList<Removable> items = new LinkedList<Removable>();
	private String type;

	Place() {

	}

	Place(int x, int y, String symbol, String type) {
		this.x = x;
		this.y = y;
		this.setSymbol(symbol);
		this.type = type;
	}

	public abstract void event(Player p);

	public abstract String getDescription();

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
				.filter(item -> item != null).findAny().orElse(this.symbol);
		// return symbol;
	}

	public void put(Removable item) {
		this.items.addFirst(item);
	}

	public boolean isBlock() {
		return this.items.stream().filter(item -> item instanceof RoadBlock)
				.findAny().map(item -> true).orElse(false);
	}

	public boolean remove(Player p) {
		return this.items.remove(p);
	}

	public boolean removeBlock() {
		Removable it = this.items.stream()
				.filter(item -> item instanceof RoadBlock).findAny()
				.orElse(null);
		return items.remove(it);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if (this.type != null)
			return;
		this.type = type;
	}
}
