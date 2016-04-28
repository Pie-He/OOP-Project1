package place;

import type.Manager;
import type.Player;
import util.IO;

public class House extends Place implements Comparable<House> {
	// private int price;
	private static final int MAXLEVEL = 5;
	private int initialPrice;
	private int level;
	private String name;
	private Player owner;

	// private Street street;

	public House() {
		this.level = 1;
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

	public String getStreet() {
		return this.name.substring(0, name.length() - 1);
	}

	public String getSymbol() {
		return (owner == null ? super.getSymbol() : owner.getHsSymbol());
	}

	@Override
	public String getDescription() {
		return super.getDescription() + "\n" + "名称:" + name + "\n" + "初始价格:"
				+ this.initialPrice + "元" + "\n" + "当前等级:" + this.level + "级"
				+ "\n" + "拥有者:"
				+ (this.owner == null ? "(可供出售状态)" : this.owner.getName())
				+ "\n";
	}

	@Override
	public boolean event(Player p) {
		super.event(p);
		if (this.owner == null) {
			this.sell(p);
		} else if (this.owner == p) {
			this.levelUp();
		} else {
			return this.charge(p);
		}
		return true;
	}

	public void destroy() {
		this.owner = null;
	}

	private void sell(Player p) {
		if (!IO.getYesOrNo("是否需要购买？"))
			return;
		if (!p.addCash(-this.getPrice())) {
			IO.printString("现金不足！！！");
			return;
		}
		p.addHouse(this);
		this.owner = p;
		IO.printString("购地成功！！！");
	}

	private void levelUp() {
		if (this.level >= MAXLEVEL) {
			IO.printString("已最高级！！！");
			return;
		}
		if (!IO.getYesOrNo("是否需要升级？"))
			return;
		if (!owner.addCash(-this.initialPrice / 2)) {
			IO.printString("现金不足！！！");
			return;
		}
		this.level++;
		IO.printString("升级成功！！！");
	}

	private boolean charge(Player p) {
		int fee = this.getFee();
		IO.printString("缴交过路费" + fee + "元给" + owner.getName());
		owner.addCash(fee);
		if (p.addCash(-fee))
			return true;
		IO.printString("现金不足，银行存款抵扣！");
		fee -= p.getCash();
		p.setCash(0);
		if (p.addDeposit(-fee))
			return true;
		IO.printString("银行存款不足，变卖土地抵扣！");
		fee -= p.getDeposit();
		p.setDeposit(0);
		while (fee > 0) {
			House house = p.sellHouse();
			if (house == null) {
				owner.addCash(-fee);
				Manager.getInstance().fail(p);
				return false;
			}
			house.owner = null;
			if ((fee -= house.getPrice()) <= 0) {
				p.addCash(-fee);
				break;
			}
		}
		return true;
	}

	private int getFee() {
		return owner.getStreet(this.getStreet()).stream()
				.mapToInt(i -> i.getPrice()).sum()
				/ 10 + this.getPrice() / 5;
	}
}
