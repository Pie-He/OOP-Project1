package type;

import java.util.*;
import java.util.stream.Collectors;

import place.House;
import util.Tools;

public class Player extends Removable {
	private String name;
	private int cash;// 玩家现金
	private int deposit;// 玩家存款
	private int coupon;// 玩家点券
	private String hsSymbol;
	// private int step;
	private int direction;
	private ArrayList<Prop> props;
	private PriorityQueue<House> houses;
	private EnumMap<Stock, Integer> stocks = new EnumMap<Stock, Integer>(
			Stock.class);

	public Player() {
		this.cash = 5000;
		this.deposit = 0;
		this.coupon = 0;
		this.props = new ArrayList<Prop>(20);
		this.direction = 1;
		this.setPoi(0);
		houses = new PriorityQueue<House>(20);
		// mov=new Removable();
	}

	public Player(String name, String symbol, String hsSymbol) {
		this();
		this.name = name;
		super.setSymbol(symbol);
		this.hsSymbol = hsSymbol;

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

	public void setCash(int cash) {
		this.cash = cash;
	}

	public boolean addCash(int cash) {
		if (this.cash + cash < 0)
			return false;
		return (this.cash += cash) >= 0;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int plDeposit) {
		this.deposit = plDeposit;
	}

	public boolean addDeposit(int deposit) {
		if (this.deposit + deposit < 0)
			return false;
		return (this.deposit += deposit) >= 0;
	}

	public int getCoupon() {
		return coupon;
	}

	public void setCoupon(int plCoupon) {
		this.coupon = plCoupon;
	}

	public boolean addCoupon(int coupon) {
		if (this.coupon + coupon < 0)
			return false;
		return (this.coupon += coupon) >= 0;
	}

	public String getHsSymbol() {
		return hsSymbol;
	}

	public boolean addProp(Prop p) {
		return this.props.add(p);
	}

	public Prop removeProp(int index) {
		return this.props.remove(index);
	}

	public void useProp(int index) {
		Prop prop = props.get(index);
		if (prop.use(this))
			props.remove(index);

	}

	public ArrayList<String> propToText() {
		// StringBuffer str = new StringBuffer();
		ArrayList<String> strs = new ArrayList<String>(20);
		for (int i = 0; i < this.props.size(); i++) {
			strs.add(props.get(i).toText());
		}
		// return str.toString();
		return strs;
	}

	public int getpropNum() {
		return this.props.size();
	}

	public String getMessage() {
		String str = Tools.stringCover(16, this.name, this.coupon + "",
				this.cash + "", this.deposit + "",
				this.getHouseProperty() + "",
				(this.getCash() + this.getDeposit() + this.getHouseProperty())
						+ "");
		/*
		 * String str = this.getName() + "\t\t" + this.getCoupon() + "\t\t" +
		 * this.getCash() + "\t\t" + this.getDeposit() + "\t\t" +
		 * this.getHouseProperty() + "\t\t" + (this.getCash() +
		 * this.getDeposit() + this.getHouseProperty());
		 */
		return str;
	}

	public int walk() {
		this.poi = (this.poi + this.direction +Map.getInstance().mapLength)
				%Map.getInstance().mapLength;
		return this.poi;
	}

	public int getDirection() {
		return direction;
	}

	public void reverse() {
		this.direction = -this.direction;
	}

	public int getPrePoi(int dis) {
		return (this.poi + this.direction * dis + Map.getInstance().mapLength)
				% Map.getInstance().mapLength;
	}

	public int getHouseAmount() {
		return this.houses.size();
	}

	public void addHouse(House house) {
		this.houses.add(house);
	}

	public int getHouseProperty() {
		return houses.stream().mapToInt(i -> i.getPrice()).sum();
	}

	public Collection<House> getStreet(String str) {
		return houses.stream().filter(i -> i.getStreet().equals(str))
				.collect(Collectors.toList());
	}

	public House sellHouse() {
		return this.houses.poll();
	}

	public boolean isInView(Player p, int range) {
		if (Math.abs(this.poi - p.poi) <= range
				|| Math.abs(this.poi - p.poi) >= (Map.getInstance().mapLength - range))
			return true;
		return false;
	}

	public void addStock(Stock stock, int num) {
		int l = this.stocks.containsKey(stock) ? this.stocks.get(stock) : 0;
		this.stocks.put(stock, l + num);
	}

	public boolean removeStock(Stock stock, int num) {
		int l = this.stocks.containsKey(stock) ? this.stocks.get(stock) : 0;
		if (num > l)
			return false;
		this.stocks.put(stock, l - num);
		if (stocks.get(stock) == 0)
			stocks.remove(stock);
		return true;
	}

	public int getStockAmount(Stock s) {
		return this.stocks.containsKey(s) ? this.stocks.get(s) : 0;
	}

	public void fail() {
		House house;
		while ((house = this.houses.poll()) != null) {
			house.destroy();
		}
		this.cash = 0;
		this.coupon = 0;
		this.deposit = 0;
		this.props.clear();
		this.name += "(failed)";
	}
	/*
	 * private class MyArray extends ArrayList<Prop> {
	 * 
	 * private static final long serialVersionUID = -2525821341292125610L;
	 * private int capacity;
	 * 
	 * MyArray(int capacity) { super(capacity); this.capacity = capacity; }
	 * 
	 * boolean isFull() { return this.size() == capacity; }
	 * 
	 * @Override public boolean add(Prop p) { return isFull() ? false :
	 * super.add(p); }
	 * 
	 * }
	 */
}
