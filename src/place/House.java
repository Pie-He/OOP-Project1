package place;

import type.Player;

public class House extends Place implements Comparable<House> {
	// private int price;
	private int initialPrice;
	private int level;
	private String name;
	private Player owner;

	public House() {
		this.level = 1;
	}

	public House(int initialPrice) {
		this();
		this.initialPrice = initialPrice;
	}

	public House(int x, int y, String symbol, int initialPrice, String name) {
		super(x, y, symbol, "房产");
		this.name = name;
		this.initialPrice = initialPrice;
		this.level = 1;
	}

	@Override
	public int compareTo(House arg0) {
		// TODO Auto-generated method stub
		return this.getPrice() - arg0.getPrice();
	}

	public int getInitialPrice() {
		return this.initialPrice;
	}

	public void setInitialPrice(int initialPrice) {
		if (this.initialPrice != 0)
			return;
		this.initialPrice = initialPrice;
	}

	public int getPrice() {
		return this.initialPrice * this.level;
	}

	public int upgradePrice() {
		return this.initialPrice / 2;
	}

	public int getLevel() {
		return this.level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (this.name != null)
			return;
		this.name = name;
	}

	@Override
	public void event(Player p) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "类型" + this.getType() + "\n" + 
				"名称" + name + "\n" + 
				"初始价格"+ this.initialPrice + "元" + "\n"+
				"当前等级"+ this.level+"级"+"\n"+
				"拥有者"+this.owner==null?"(可供出售状态)":this.owner.getName()+"\n";
	}
}
