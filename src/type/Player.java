package type;

import java.util.*;

public class Player extends Removable {
	private String name;
	private int cash;// 玩家现金
	private int deposit;// 玩家存款
	private int coupon;// 玩家点券
	private String hsSymbol;

	private MyArray prop;

	public Player() {
		this.cash = 5000;
		this.deposit = 0;
		this.coupon = 0;
		this.prop = new MyArray(20);
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

	public String getHsSymbol() {
		return hsSymbol;
	}

	public boolean addProp(Prop p){
		return this.prop.add(p);
	}
	
	public void removeProp(int index){
		this.prop.remove(index);
	}

	public String propToText(){
		StringBuffer str=new StringBuffer();
		for(int i=0;i<this.prop.size();i++){
			str.append(i+"-");
			str.append(prop.get(i).toText());
			str.append("  ");
		}
		return str.toString();
	}
	private class MyArray extends ArrayList<Prop> {
		/**
		 * 
		 */
		private static final long serialVersionUID = -2525821341292125610L;
		private int capacity;

		MyArray(int capacity) {
			this.capacity = capacity;
		}

		boolean isFull() {
			return this.size() == capacity;
		}

		@Override
		public boolean add(Prop p) {
			return isFull() ? false : super.add(p);
		}
		
		
	}
}
