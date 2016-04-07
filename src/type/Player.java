package type;

import java.util.*;

public class Player extends Removable {
	private String name;
	private int cash;// 玩家现金
	private int deposit;// 玩家存款
	private int coupon;// 玩家点券
	private String hsSymbol;
	private LinkedList prop;

	public Player() {
		this.cash = 5000;
		this.deposit = 0;
		this.coupon = 0;
		this.prop = new LinkedList();
		// mov=new Removable();
	}

	public Player(String name, String symbol, String hsSymbol) {
		this();
		this.name = name;
		super.setSymbol(symbol);
		this.hsSymbol = hsSymbol;

	}

	public String getDescription() {
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int plCash) {
		this.cash = plCash;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int plDeposit) {
		this.deposit = plDeposit;
	}

	public int getCoupon() {
		return coupon;
	}

	public void setCoupon(int plCoupon) {
		this.coupon = plCoupon;
	}
}
